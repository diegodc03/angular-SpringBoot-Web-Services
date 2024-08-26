import { Component } from '@angular/core';
import { Product } from '../model/product/product.module';
import  { InventaryService } from "../services/inventary/inventary.service";
import { Router } from '@angular/router';
import { ProductWithImageModule } from '../model/product-with-image/product-with-image.module';
import { ProductSoldDTO } from '../model/product-sold-dto/product-sold-dto.module';

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
              private router: Router
              ) { }



  // MIRAR POR QUE ESTO SE HACE DE MANERA CONCURRENTE Y NO HACE PRIMERO EL SUBSCRIBE Y LUEGO EL LOAD CUANDO ESTA FUERA DEL SUBSCRIBE
  ngOnInit(): void {

    // Todos los productos que haya se van a mostrar, cada uno puede que tenga o no una imagen, por lo que se inicializa un array de imágenes vacío
    this.inventaryService.getAllProducts().subscribe(inventary => {
      this.products = inventary;
      this.loadProductImages(this.products);
    });
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
/*

  // Función para añadir el producto con la talla seleccionada al carrito
  addToCartWithSize(product: Product) {
    if (this.selectedSize) {

      // Comprobar si el producto tiene tallas
      if(product.totalStock == 0){
        alert('Producto sin stock');
        return;
      }

      const selectedGarment = product.garments.find(g => g.size == this.selectedSize);
      if (selectedGarment && selectedGarment.stock == 0) {
        alert('Producto sin stock');
        return;
      }else{
        if (selectedGarment) {
          selectedGarment.stock = selectedGarment.stock - 1;
        }
      }
      this.productsSold.push({ product, size: this.selectedSize });
      console.log(this.productsSold);
      console.log('Producto añadido al carrito:', product, 'Talla:', this.selectedSize);
      //this.resetSelection(); // Reiniciar selección después de añadir al carrito
    } else {
      alert('Por favor selecciona una talla antes de añadir al carrito.');
    }
  }

  // Función para añadir el producto al carrito si no tiene tallas
  addToCart(product: Product) {

    // Comprobar si el producto tiene tallas
    if(product.totalStock == 0){
      alert('Producto sin stock');
      return;
    }else{
      product.totalStock = product.totalStock - 1;

      this.productsSold.push({ product });
      console.log(this.productsSold);
      console.log('Producto añadido al carrito:', product);
    }    
  }



  // Función para añadir el producto con la talla seleccionada al carrito
  addToCartWithSize(product: ProductWithImageModule) {
    if (this.selectedSize) {

      // Comprobar si el producto tiene tallas
      if(product.product.totalStock == 0){
        alert('Producto sin stock');
        return;
      }

      const selectedGarment = product.product.garments.find(g => g.size == this.selectedSize);
      if (selectedGarment && selectedGarment.stock == 0) {
        alert('Producto sin stock');
        return;
      }else{
        if (selectedGarment) {
          selectedGarment.stock = selectedGarment.stock - 1;
        }
      }
      this.productsSold.push({ product: product.product, size: this.selectedSize });
      console.log(this.productsSold);
      console.log('Producto añadido al carrito:', product.product, 'Talla:', this.selectedSize);
      //this.resetSelection(); // Reiniciar selección después de añadir al carrito
    } else {
      alert('Por favor selecciona una talla antes de añadir al carrito.');
    }
  }
*/

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
        this.router.navigate(['/dashboard/inventarySale/show-product-sizes'], { state: { sizes: product.product.garments } });

      }else{
        product.product.totalStock = product.product.totalStock - 1;

        // check if exist the product in the array
        const index = this.productsSold.findIndex(p => p.productId === product.product.publicId);
        if(index !== -1){
          this.productsSold[index].totalStockSold = this.productsSold[index].totalStockSold + 1;
          this.productsSold[index].totalPrice = this.productsSold[index].totalPrice + this.productsSold[index].unitaryPrice;
        }else{
          // Create new object Product with the stock sold
          const p = new ProductSoldDTO(product.product.publicId, 
                                        product.product.name, 
                                        product.product.price,
                                        product.product.price,
                                        product.product.isTshirt, 
                                        1, 
                                        product.product.imageName,
                                        product.product.garments);
        
          this.productsSold.push(p);
        
          console.log(this.productsSold);
          console.log('Producto añadido al carrito:', product.product);
        }    
      }
    }
  }


  shoppingcheck() {
    //Los valores llegan hasta aqui
    this.router.navigate(['/dashboard/inventarySale/shopping-basket'], { state: { products: this.productsSold } });
  }


}
