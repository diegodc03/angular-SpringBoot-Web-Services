import { Component } from '@angular/core';
import { ProductSoldDTO } from 'src/app/model/product-sold-dto/product-sold-dto.module';
import { SaleList } from 'src/app/model/sale-list/sale-list.module';
import { SaleService } from 'src/app/services/sale/sale.service';

@Component({
  selector: 'app-sales-view',
  templateUrl: './history-sales-view.component.html',
  styleUrls: ['./history-sales-view.component.css']
})
export class HistorySalesViewComponent {

   saleList: SaleList[] = [];


  constructor(
    private saleService: SaleService
  ) { }


  // LLamamos al backend para que nos devuelva la lista de ventas
  ngOnInit(): void {

    this.saleService.getAllSales().subscribe((returningSaleList) => {
      this.saleList = returningSaleList;
      console.log('Sales loaded:', this.saleList);
    });


  }



}
