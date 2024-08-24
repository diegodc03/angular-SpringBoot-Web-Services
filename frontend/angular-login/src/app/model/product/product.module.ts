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
  image?: File;
  garments: Garment[];


  constructor(@Inject(String) publicId: string, @Inject(String) name: string, @Inject(String) description: string, @Inject(Number) price: number, @Inject(Boolean) isTshirt: boolean, @Inject(Number) totalStock: number, @Inject(File) image: File, @Inject(Garment) garments: Garment[]) {
    this.publicId = publicId;
    this.name = name;
    this.description = description;
    this.price = price;
    this.isTshirt = isTshirt;
    this.totalStock = totalStock;
    this.image = image;
    this.garments = garments;
  }


}
