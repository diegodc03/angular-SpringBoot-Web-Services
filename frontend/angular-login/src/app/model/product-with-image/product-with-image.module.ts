import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from "../product/product.module";


@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class ProductWithImageModule { 

  product: Product;
  image: File | null;


  constructor(product: Product, image: File | null) {
    this.product = product;
    this.image = image;
  }

}
