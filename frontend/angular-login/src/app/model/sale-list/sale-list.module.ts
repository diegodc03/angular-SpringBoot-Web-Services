import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common'; import { Inject } from '@angular/core';
import { ProductSoldDTO } from '../product-sold-dto/product-sold-dto.module';




@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class SaleList {

  saleId: String;
  totalAmount: number;
  date: Date;
  productsSale: ProductSoldDTO[];

 

  
  constructor(saleId: String, @Inject('totalAmount') totalAmount: number, date: Date, @Inject('productsSold') productsSale: ProductSoldDTO[]) {
    this.saleId = saleId;
    this.totalAmount = totalAmount;
    this.date = date;
    this.productsSale = productsSale;
  }



 }
