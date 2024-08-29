import { Component } from '@angular/core';
import { ProductSoldDTO } from 'src/app/model/product-sold-dto/product-sold-dto.module';
import { SaleList } from 'src/app/model/sale-list/sale-list.module';
import { SaleService } from 'src/app/services/sale/sale.service';
import { Router } from '@angular/router';
import { state } from '@angular/animations';


@Component({
  selector: 'app-sales-view',
  templateUrl: './history-sales-view.component.html',
  styleUrls: ['./history-sales-view.component.css']
})
export class HistorySalesViewComponent {

   saleList: SaleList[] = [];


  constructor(
    private saleService: SaleService,
    private router: Router
  ) { }


  // LLamamos al backend para que nos devuelva la lista de ventas
  ngOnInit(): void {

    this.saleService.getAllSales().subscribe((returningSaleList) => {
      this.saleList = returningSaleList;
      console.log('Sales loaded:', this.saleList);
    });


  }

  redirectToInformation(SaleList: SaleList): void
  {
    console.log('Redirect to information');
    const products = {}; // Declare the 'products' variable
    this.router.navigate(['/dashboard/inventarySale/show-sale-information'], { state: { sale: SaleList} }); // Pass the correct arguments to the 'navigate' method
  }



}
