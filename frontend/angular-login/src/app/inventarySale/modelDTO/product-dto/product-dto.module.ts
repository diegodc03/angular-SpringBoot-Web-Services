import { Inject, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Garment } from "../../model/garment/garment.module";


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
  isTshirt: boolean;
  totalStock: number;
  garments: Garment[];


  constructor( @Inject(String) name: string, @Inject(String) description: string, @Inject(Number) price: number, @Inject(Boolean) isTshirt: boolean, @Inject(Number) totalStock: number, @Inject(Garment) garments: Garment[]) {

    this.name = name;
    this.description = description;
    this.price = price;
    this.isTshirt = isTshirt;
    this.totalStock = totalStock;
    this.garments = garments;
  }



}
