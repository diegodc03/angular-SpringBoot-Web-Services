import { Inject, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { sizeElement } from "../garment/sizeElement.module";


@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class Product { 

  publicId: string;
  name: string;
  description: string;
  price: number;
  haveSize: boolean;
  totalStock: number;
  imageName: String;
  garments: sizeElement[];

  

  constructor(@Inject(String) publicId: string, 
              @Inject(String) name: string, 
              @Inject(String) description: string, 
              @Inject(Number) price: number, 
              @Inject(Boolean) haveSize: boolean, 
              @Inject(Number) totalStock: number, 
              @Inject(String) imageName: String,
              @Inject(sizeElement) garments: sizeElement[]) {
    this.publicId = publicId;
    this.name = name;
    this.description = description;
    this.price = price;
    this.haveSize = haveSize;
    this.totalStock = totalStock;
    this.imageName = imageName;
    this.garments = garments;
  }


}
