import { Inject, NgModule, Optional } from '@angular/core';
import { CommonModule } from '@angular/common';
import { sizeElement } from '../../model/garment/sizeElement.module';
import { GarmentSoldDtoModule } from '../garment-sold-dto/garment-sold-dto.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class ProductSoldDTO { 

  
  productId: String;
  public_id: String;
  name: string;
  unitaryPrice: number;
  totalPrice: number;
  existanceSizes: boolean;
  totalStockSold: number;
  imageName: String;
  garmentsSales: GarmentSoldDtoModule[];

  

  constructor(@Inject(String) productId: string, 
              @Inject(String) public_id: string,
              @Inject(String) name: string, 
              @Inject(Number) unitaryPrice: number,
              @Inject(Number) totalPrice: number, 
              @Inject(Boolean) existanceSizes: boolean, 
              @Inject(Number) totalStockSold: number, 
              @Inject(String) imageName: String,
              @Inject(sizeElement) garmentsSales: GarmentSoldDtoModule[]) {
    this.productId = productId;
    this.public_id = public_id;
    this.name = name;
    this.unitaryPrice = unitaryPrice
    this.totalPrice = totalPrice;
    this.existanceSizes = existanceSizes;
    this.totalStockSold = totalStockSold;
    this.imageName = imageName;
    this.garmentsSales = garmentsSales;
  }

}
