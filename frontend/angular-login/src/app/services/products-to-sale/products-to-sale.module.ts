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
    console.log('Productos guardados:', products);
    this.productsToSale = products;
    console.log('Productos en el array despues de ser guardados:', this.productsToSale);
  }

  getProductsToSale(): Product[] {
    console.log('Productos que van a ser devueltos:', this.productsToSale);

    return this.productsToSale;
  }

  clearCart() {
    console.log('Productos borrados:', this.productsToSale);
    this.productsToSale = [];
    console.log('Productos en el array despues de ser borrados:', this.productsToSale);
  }





}
