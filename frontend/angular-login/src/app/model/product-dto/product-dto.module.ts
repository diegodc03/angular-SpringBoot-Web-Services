import { Inject, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Garment } from "../garment/garment.module";


@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class ProductDTO { 


  name: string;
  description: string;
  price: number;
  isShirt: boolean;
  totalStock: number;
  garments: Garment[];
  image?: File;


  constructor( @Inject(String) name: string, @Inject(String) description: string, @Inject(Number) price: number, @Inject(Boolean) isShirt: boolean, @Inject(Number) totalStock: number, @Inject(Garment) garments: Garment[], @Inject(File) image: File) {

    this.name = name;
    this.description = description;
    this.price = price;
    this.isShirt = isShirt;
    this.totalStock = totalStock;
    this.garments = garments;
    this.image = image;
  }



}
