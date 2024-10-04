import { Inject, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { sizeElement } from "../../model/garment/sizeElement.module";


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
  haveSize: boolean;
  totalStock: number;
  garments: sizeElement[];


  constructor( @Inject(String) name: string, @Inject(String) description: string, @Inject(Number) price: number, @Inject(Boolean) haveSize: boolean, @Inject(Number) totalStock: number, @Inject(sizeElement) garments: sizeElement[]) {

    this.name = name;
    this.description = description;
    this.price = price;
    this.haveSize = haveSize;
    this.totalStock = totalStock;
    this.garments = garments;
  }



}
