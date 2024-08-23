import { Component } from '@angular/core';
import { Product } from '../model/product/product.module';
import  { InventaryService } from "../services/inventary/inventary.service";
import { Router } from '@angular/router';


@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
  styleUrls: ['./sale.component.css']
})
export class SaleComponent {


  products: Product[] = [];

  constructor(private inventaryService: InventaryService) { }


  ngOnInit(): void {

    this.inventaryService.getAllProducts().subscribe(inventary => {
      this.products = inventary;
    });

  }

  productInfo() {
    throw new Error('Method not implemented.');
  }


  addToCart() {
    throw new Error('Method not implemented.');
  }


}
