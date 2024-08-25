import { Component } from '@angular/core';
import { Product } from '../model/product/product.module';
import  { InventaryService } from "../services/inventary/inventary.service";
import { Router } from '@angular/router';
import { ProductWithImageModule } from '../model/product-with-image/product-with-image.module';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
  styleUrls: ['./sale.component.css']
})
export class SaleComponent {
  


  productWithImages: ProductWithImageModule[] = [];
  
  // This array is used to store the products that the user has selected to buy
  productsSold: { product: Product, size?: String }[] = [];

  products: Product[] = [];
  productimages: File[] = [];
  selectedProduct: ProductWithImageModule | null = null;
  selectedSize: String = ''; // Para rastrear la talla seleccionada

  constructor(private inventaryService: InventaryService,
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
      console.log("cagon dios 0");
      this.inventaryService.getProductImage(product.publicId).subscribe(imageBlob => {
        const imageFile = new File([imageBlob], product.publicId, { type: imageBlob.type });
        this.productWithImages.push(new ProductWithImageModule(product, imageFile));
      }, () => {
        // Si no hay imagen, simplemente añádelo con image: null
        this.productWithImages.push(new ProductWithImageModule(product, null));
      });
    }
  }


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
      console.log('Producto añadido al carrito:', product);
    }

    
  }


}
