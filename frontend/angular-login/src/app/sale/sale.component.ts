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

  products: Product[] = [];
  productimages: File[] = [];

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

  

  addToCart() {
    throw new Error('Method not implemented.');
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



}
