import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SaleService } from 'src/app/services/sale/sale.service';
import { SaleList } from 'src/app/model/sale-list/sale-list.module';
import { ProductSoldDTO } from 'src/app/model/product-sold-dto/product-sold-dto.module';

@Component({
  selector: 'app-sales-view',
  templateUrl: './history-sales-view.component.html',
  styleUrls: ['./history-sales-view.component.css']
})
export class HistorySalesViewComponent implements OnInit {


  saleList: SaleList[] = [];
  
  constructor(
    private saleService: SaleService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.saleService.getAllSales().subscribe((returningSaleList) => {
      this.saleList = returningSaleList
      console.log('Sales loaded:', this.saleList);
    });
  }

  redirectToInformation(saleId: String): void {
    console.log('Redirect to information');

    this.router.navigate(['/dashboard/inventarySale/show-sale-information'], { state: { saleId: saleId } });
  }

  deleteSale(sale: SaleList): void {
    
    console.log('Deleting sale:', sale);
    this.saleService.deleteSale(sale.saleId).subscribe(() => {
      //this.saleList = this.saleList.filter(s => s.saleId !== sale.saleId);
      this.ngOnInit();
      console.log('Sale deleted successfully');
    })
  }

  showtotalstock(productsSold: ProductSoldDTO[]): number {

    let totalstock = 0;

    productsSold.forEach(p => { 
      totalstock += p.totalStockSold;
    }
    )
    return totalstock;

  }

}
