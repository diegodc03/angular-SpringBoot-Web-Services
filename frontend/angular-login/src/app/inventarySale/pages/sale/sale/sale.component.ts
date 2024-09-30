import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from '../../../model/product/product.module';
import  { InventaryService } from "../../../service/inventary/inventary.service";
import { Router } from '@angular/router';

import { ProductSoldDTO } from '../../../modelDTO/product-sold-dto/product-sold-dto.module';
import { CartService } from '../../../service/cartService/cart-service.service';
import { GarmentSoldDtoModule } from '../../../modelDTO/garment-sold-dto/garment-sold-dto.module';
import { Garment } from '../../../model/garment/garment.module';
import { ProductsToSaleService } from '../../../service/products-to-sale/products-to-sale.module';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
  styleUrls: ['./sale.component.css']
})
export class SaleComponent {

  
  
  // This array will store the products that the user has selected to buy
  // It will be sent to the backend when the user confirms the purchase
  // when the user tap the product, it will be added to this array
      // if have a size, it will be added with the size and if tap the same product but other size, it will be added with the new size and the other 
  productsSold: ProductSoldDTO[] = [];

  products: Product[] = [];
  productimages: File[] = [];

  selectedSize: String = ''; // Para rastrear la talla seleccionada
 


  constructor(private inventaryService: InventaryService,
                @Inject(Router) private router: any,
                private cartService: CartService,
                private productsToSaleService: ProductsToSaleService
                ) { }



  // MIRAR POR QUE ESTO SE HACE DE MANERA CONCURRENTE Y NO HACE PRIMERO EL SUBSCRIBE Y LUEGO EL LOAD CUANDO ESTA FUERA DEL SUBSCRIBE
  ngOnInit(): void {
    
    //this.products = this.productsToSaleService.getProductsToSale();
    if(this.productsToSaleService.getProductsToSale().length !== 0){
      this.products = this.productsToSaleService.getProductsToSale();
      console.log('Productos cargados:', this.products);
      //this.loadProductImages(this.products);

      this.productsSold = this.cartService.getProducts();
        
      // Ahora, procesamos las tallas seleccionadas si las hay
      const state = history.state;
      if (state && state.selectedSize && state.productId) {
        this.addSelectedSizesToProduct(state.selectedSize, state.productId);
      }

    }else{
      console.log('Productos no cargados por que no hay ninguno en el array');

      // Primero, cargamos los productos del inventario
      this.inventaryService.getAllProducts().subscribe(inventary => {
        this.products = inventary;
          console.log('Productos cargados, es la primera vez, los cogemos de la db:', this.products);
          //this.loadProductImages(this.products);
          
          // Una vez que los productos están cargados, cargamos el carrito de compras
          this.productsSold = this.cartService.getProducts();
        
          // Ahora, procesamos las tallas seleccionadas si las hay
          const state = history.state;
          if (state && state.selectedSize && state.productId) {
            this.addSelectedSizesToProduct(state.selectedSize, state.productId);
          }
        });
    }

    
}
  

/*
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
    
  }*/


  addToCart(product: Product) {

    // Comprobar si el producto tiene tallas
    if(product.totalStock == 0){
      alert('Producto sin stock');
      return;
    }else{

      

      if(product.isTshirt){
        alert('Producto con tallas');
        // This part the user will select the size
        //this.selectedProduct = product;
        this.productsToSaleService.saveProductsToSale(this.products);
        this.router.navigate(['/dashboard/inventarySale/show-product-sizes'], { state: { sizes: product.garments, productId: product.publicId} });

      }else{

        const productsIndex = this.products.findIndex(p => p.publicId === product.publicId);

        if(productsIndex !== -1){
          this.products[productsIndex].totalStock = this.products[productsIndex].totalStock - 1;
        }

        // check if exist the product in the array
        const index = this.productsSold.findIndex(p => p.productId === product.publicId);
        if(index !== -1){
          console.log('Producto ya existe en el carrito:', product);
          this.productsSold[index].totalStockSold = this.productsSold[index].totalStockSold + 1;
          this.productsSold[index].totalPrice = this.productsSold[index].totalPrice + this.productsSold[index].unitaryPrice;
          console.log("Producto en el array de productos:", this.productsSold[index]);  
          this.cartService.addProduct(this.productsSold[index]);
        
        }else{
          console.log('Producto no existe en el carrito:', product);
          // Create new object Product with the stock sold
          const p = new ProductSoldDTO(product.publicId, 
                                        '',
                                        product.name, 
                                        product.price,
                                        product.price,
                                        product.isTshirt, 
                                        1, 
                                        product.imageName,
                                        []);
            
       
          this.cartService.addProduct(p);
          //this.productsSold.push(p);

          console.log(this.productsSold);
          console.log('Producto añadido al carrito:', product);
        }  

        this.productsToSaleService.saveProductsToSale(this.products);  
      }
    }
  }


  
  addSelectedSizesToProduct(selectedSizes: GarmentSoldDtoModule[], productId: string) {
    console.log('Tallas seleccionadas:', selectedSizes);

    const productSold = this.productsSold.find(p => p.productId === productId);
    console.log('Producto encontrado en el carrito:', productSold);
    if (!productSold) {
      // Si no se encuentra el producto, se tendra  que crear, ya que es una nueva venta de un producto
      // I get the product of the inventary to delete a element of the stock
      const index = this.products.findIndex(p => p.publicId === productId);
      console.log('Index:', index);
      if(index === -1){
        alert('Producto sin tallas');
      }else{
        console.log('Producto no existe en el carrito:', productId);
        const productSold = new ProductSoldDTO(productId, 
                                              '',
                                              this.products[index].name, 
                                              this.products[index].price, 
                                              0, 
                                              this.products[index].isTshirt, 
                                              0, 
                                              this.products[index].imageName, 
                                              selectedSizes);
  
        for(let size of selectedSizes){
          
          const foundGarmentIndex = this.products[index].garments.findIndex(garment => garment.size === size.size);
          if (foundGarmentIndex !== -1) {
            this.products[index].garments[foundGarmentIndex].stock = this.products[index].garments[foundGarmentIndex].stock - size.stockSold;

          }

          productSold.totalPrice = productSold.totalPrice + (size.stockSold * productSold.unitaryPrice);
          productSold.totalStockSold = productSold.totalStockSold + size.stockSold;
          this.products[index].totalStock = this.products[index].totalStock - size.stockSold;

        }
        
        this.cartService.addProduct(productSold);
        
        console.log('Producto añadido al carrito:', this.productsSold);
      }
    }else{

      console.log('Producto ya existe en el carrito:', productSold);
      // Aqui el producto ya existe, por lo que lo tenemos que modificar, ya que ya se ha añadido al carrito
      
        const index = this.products.findIndex(p => p.publicId === productId);
        if(index === -1){
          alert('Producto sin tallas');
        }else{
          console.log("Tallas seleccionadas")
          for(let size of selectedSizes){

            let foundGarmentIndex = productSold.garmentsSales.findIndex(garment => garment.size === size.size);
            if (foundGarmentIndex == -1) {
              // Si no se encuentra la talla, se añade al array de tallas
              productSold.garmentsSales.push(size);
              foundGarmentIndex = productSold.garmentsSales.length - 1;
            }

              productSold.garmentsSales[foundGarmentIndex].stockSold = productSold.garmentsSales[foundGarmentIndex].stockSold + size.stockSold;

              productSold.totalStockSold = productSold.totalStockSold + size.stockSold;
              productSold.totalPrice = productSold.totalPrice + (size.stockSold * productSold.unitaryPrice);
              
              this.products[index].totalStock = this.products[index].totalStock - size.stockSold;
              this.products[index].garments[foundGarmentIndex].stock = this.products[index].garments[foundGarmentIndex].stock - size.stockSold;
              console.log('Producto en el array de productos:', this.products[index]);
            
            this.cartService.addProduct(productSold);
          
          
            console.log('Producto añadido al carrito:', this.productsSold);
        
          }	
        }
    

    this.productsToSaleService.saveProductsToSale(this.products);
  }

}


  shoppingcheck() {
    //Los valores llegan hasta aqui
    console.log('Productos a vender:', this.productsSold);
    this.productsToSaleService.saveProductsToSale(this.products);
    this.router.navigate(['/dashboard/inventarySale/shopping-basket'], { state: { products: this.productsSold } });
    
  }


}
