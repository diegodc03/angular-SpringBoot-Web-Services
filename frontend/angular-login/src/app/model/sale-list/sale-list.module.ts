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

  customerName: String;
  totalAmount: number;
  date: Date;
  productsSale: ProductSoldDTO[];

 

  
  constructor(customerName: String, @Inject('totalAmount') totalAmount: number, date: Date, @Inject('productsSold') productsSale: ProductSoldDTO[]) {
    this.customerName = customerName;
    this.totalAmount = totalAmount;
    this.date = date;
    this.productsSale = productsSale;
  }



 }
