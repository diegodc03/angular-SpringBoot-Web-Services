import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductWithImageModule } from 'src/app/model/product-with-image/product-with-image.module';
import { Product } from 'src/app/model/product/product.module';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class ProductsToSaleService { 

  

  private productsToSale: Product[] = [];

  saveProductsToSale(products: Product[]) {
    this.productsToSale = products;
  }

  getProductsToSale(): Product[] {
    return this.productsToSale;
  }

  clearCart() {
    this.productsToSale = [];
  }





}
