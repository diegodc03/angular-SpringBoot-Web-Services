import { Component } from '@angular/core';
import { Product } from '../model/product/product.module';
import  { InventaryService } from "../services/inventary/inventary.service";

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


}
