import { Inject, NgModule, Optional } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from '../product/product.module';
import { Garment } from '../garment/garment.module';
import { GarmentSoldDtoModule } from '../garment-sold-dto/garment-sold-dto.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class ProductSoldDTO { 

  
  productId: string;
  name: string;
  unitaryPrice: number;
  totalPrice: number;
  existanceSizes: boolean;
  totalStockSold: number;
  imageName: String;
  garmentsSales: GarmentSoldDtoModule[];

  

  constructor(@Inject(String) productId: string, 
              @Inject(String) name: string, 
              @Inject(Number) unitaryPrice: number,
              @Inject(Number) totalPrice: number, 
              @Inject(Boolean) existanceSizes: boolean, 
              @Inject(Number) totalStockSold: number, 
              @Inject(String) imageName: String,
              @Inject(Garment) garmentsSales: GarmentSoldDtoModule[]) {
    this.productId = productId;
    this.name = name;
    this.unitaryPrice = unitaryPrice
    this.totalPrice = totalPrice;
    this.existanceSizes = existanceSizes;
    this.totalStockSold = totalStockSold;
    this.imageName = imageName;
    this.garmentsSales = garmentsSales;
  }

}
