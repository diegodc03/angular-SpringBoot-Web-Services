import { Inject, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Garment } from "../garment/garment.module";


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
  isTshirt: boolean;
  totalStock: number;
  imageName: String;
  garments: Garment[];


  constructor(@Inject(String) publicId: string, 
              @Inject(String) name: string, 
              @Inject(String) description: string, 
              @Inject(Number) price: number, 
              @Inject(Boolean) isTshirt: boolean, 
              @Inject(Number) totalStock: number, 
              @Inject(String) imageName: String,
              @Inject(Garment) garments: Garment[]) {
    this.publicId = publicId;
    this.name = name;
    this.description = description;
    this.price = price;
    this.isTshirt = isTshirt;
    this.totalStock = totalStock;
    this.imageName = imageName;
    this.garments = garments;
  }


}
