import { Component } from '@angular/core';
import { Product } from '../model/product/product.module';
import  { InventaryService } from "../services/inventary/inventary.service";
import { Router } from '@angular/router';
import { ProductWithImageModule } from '../model/product-with-image/product-with-image.module';
import { ProductSoldDTO } from '../model/product-sold-dto/product-sold-dto.module';
import { CartService } from '../services/cartService/cart-service.service';
import { GarmentSoldDtoModule } from '../model/garment-sold-dto/garment-sold-dto.module';
import { Garment } from '../model/garment/garment.module';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
  styleUrls: ['./sale.component.css']
})
export class SaleComponent {

  productWithImages: ProductWithImageModule[] = [];
  
  // This array will store the products that the user has selected to buy
  // It will be sent to the backend when the user confirms the purchase
  // when the user tap the product, it will be added to this array
      // if have a size, it will be added with the size and if tap the same product but other size, it will be added with the new size and the other 
  productsSold: ProductSoldDTO[] = [];

  products: Product[] = [];
  productimages: File[] = [];
  selectedProduct: ProductWithImageModule | null = null;
  selectedSize: String = ''; // Para rastrear la talla seleccionada
 


  constructor(private inventaryService: InventaryService,
              private router: Router,
              private cartService: CartService
              ) { }



  // MIRAR POR QUE ESTO SE HACE DE MANERA CONCURRENTE Y NO HACE PRIMERO EL SUBSCRIBE Y LUEGO EL LOAD CUANDO ESTA FUERA DEL SUBSCRIBE
  ngOnInit(): void {
    this.inventaryService.getAllProducts().subscribe(inventary => {
      this.products = inventary;
      this.loadProductImages(this.products);
    });

    // Recupera el carrito de compras desde el servicio
    this.productsSold = this.cartService.getProducts();

    const state = history.state;
    if (state && state.selectedSizes && state.productId) {
      this.addSelectedSizesToProduct(state.selectedSizes, state.productId);
    }
  }
  


  loadProductImages(products: Product[]): void {
   
    for (let product of products) {
   
      this.inventaryService.getProductImage(product.publicId).subscribe(imageBlob => {
        const imageFile = new File([imageBlob], product.publicId, { type: imageBlob.type });
        this.productWithImages.push(new ProductWithImageModule(product, imageFile));
      }, () => {
        // Si no hay imagen, simplemente añádelo con image: null
        this.productWithImages.push(new ProductWithImageModule(product, null));
      });
    }
  }


  addToCart(product: ProductWithImageModule) {

    // Comprobar si el producto tiene tallas
    if(product.product.totalStock == 0){
      alert('Producto sin stock');
      return;
    }else{


      if(product.product.isTshirt){
        alert('Producto con tallas');
        // This part the user will select the size
        //this.selectedProduct = product;

        this.router.navigate(['/dashboard/inventarySale/show-product-sizes'], { state: { sizes: product.product.garments, productId: product.product.publicId} });

      }else{

        
        product.product.totalStock = product.product.totalStock - 1;

        // check if exist the product in the array
        const index = this.productsSold.findIndex(p => p.productId === product.product.publicId);
        if(index !== -1){
          this.productsSold[index].totalStockSold = this.productsSold[index].totalStockSold + 1;
          this.productsSold[index].totalPrice = this.productsSold[index].totalPrice + this.productsSold[index].unitaryPrice;

          this.cartService.addProduct(this.productsSold[index]);

        }else{
          // Create new object Product with the stock sold
          const p = new ProductSoldDTO(product.product.publicId, 
                                        product.product.name, 
                                        product.product.price,
                                        product.product.price,
                                        product.product.isTshirt, 
                                        1, 
                                        product.product.imageName,
                                        []);
            
          this.productsSold.push(p);
          this.cartService.addProduct(p);
        
          console.log(this.productsSold);
          console.log('Producto añadido al carrito:', product.product);
        }    
      }
    }
  }


  
  addSelectedSizesToProduct(selectedSizes: GarmentSoldDtoModule[], productId: string) {
    console.log('Tallas seleccionadas:', selectedSizes);
    // Implementa la lógica para actualizar el producto correcto con estas tallas

    const productSold = this.productsSold.find(p => p.productId === productId);
    if (!productSold) {
      // Si no se encuentra el producto, se tendra  que crear, ya que es una nueva venta de un producto
      // I get the product of the inventary to delete a element of the stock
      const index = this.products.findIndex(p => p.publicId === productId);
      if(index === -1){
        alert('Producto sin tallas');
      }else{
        const productSold = new ProductSoldDTO(productId, 
                                              this.products[index].name, 
                                              this.products[index].price, 
                                              0, 
                                              this.products[index].isTshirt, 
                                              0, 
                                              this.products[index].imageName, 
                                              selectedSizes);

        for(let size of selectedSizes){
          
          const foundGarment = this.products[index].garments.find(garment => garment.size === size.size);
          if (foundGarment) {
            foundGarment.stock = foundGarment.stock - size.stockSold;
            this.products[index].totalStock = this.products[index].totalStock - size.stockSold;
          }

          productSold.totalPrice = productSold.totalPrice + (size.stockSold * productSold.unitaryPrice);
          productSold.totalStockSold = productSold.totalStockSold + size.stockSold;
          
        }
        
        this.cartService.addProduct(productSold);
        this.productsSold.push(productSold);
      }
    }else{

      // Aqui el producto ya existe, por lo que lo tenemos que modificar, ya que ya se ha añadido al carrito
      for(let size of selectedSizes){
        
        const index = this.products.findIndex(p => p.publicId === productId);
        if(index === -1){
          alert('Producto sin tallas');
        }else{
          const foundGarment = productSold.garmentsSales.find(garment => garment.size === size.size);
          if (foundGarment) {
            foundGarment.stockSold = foundGarment.stockSold + size.stockSold;
            productSold.totalStockSold = productSold.totalStockSold + size.stockSold;
            productSold.totalPrice = productSold.totalPrice + (size.stockSold * productSold.unitaryPrice);
            
            this.products[index].totalStock = this.products[index].totalStock - size.stockSold;
          }
          this.cartService.addProduct(productSold);
        }
      }	
    }
  }

  shoppingcheck() {
    //Los valores llegan hasta aqui
    this.router.navigate(['/dashboard/inventarySale/shopping-basket'], { state: { products: this.productsSold } });
  }


}
