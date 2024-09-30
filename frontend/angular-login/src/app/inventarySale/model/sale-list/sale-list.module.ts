import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common'; import { Inject } from '@angular/core';
import { ProductSoldDTO } from '../../modelDTO/product-sold-dto/product-sold-dto.module';




@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class SaleList {

  saleId: String;
  totalAmount: number;
  saleDate: Date;
  productsSale: ProductSoldDTO[];

 

  
  constructor(saleId: String, @Inject('totalAmount') totalAmount: number, saleDate: Date, @Inject('productsSold') productsSale: ProductSoldDTO[]) {
    this.saleId = saleId;
    this.totalAmount = totalAmount;
    this.saleDate = saleDate;
    this.productsSale = productsSale;
  }



 }
