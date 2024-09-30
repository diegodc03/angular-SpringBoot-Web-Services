import { Inject, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class GarmentSoldDtoModule { 



  size: string;
  stockSold: number;

  constructor( @Inject(String) size: string, @Inject(Number) stockSold: number) {

      this.size = size;
      this.stockSold = stockSold;
    }



}
