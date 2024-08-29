import { Component } from '@angular/core';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { SaleService } from 'src/app/services/sale/sale.service';

@Component({
  selector: 'app-show-stadistics',
  templateUrl: './show-stadistics.component.html',
  styleUrls: ['./show-stadistics.component.css']
})
export class ShowStadisticsComponent {
  // Datos de ejemplo para ventas
  salesData = {
    monthly: {
      sales: [],
      revenue: [],
      products: []
    }
  };
  sizeData = [];

  view: [number, number] = [700, 400];
  showXAxis = true;
  showYAxis = true;
  gradient = true;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'Month';
  showYAxisLabel = true;
  yAxisLabel = 'Value';
  colorScheme = {
    domain: ['#5AA454', '#A10A28', '#C7B42C']
  };

  selectedPeriod = 'monthly';

  constructor(private salesService: SaleService) {}
 /*
  ngOnInit(): void {
    this.loadData();
  }
  
  loadData(): void {
    this.salesService.getMonthlySales().subscribe(data => {
      this.salesData.monthly.sales = data;
    });
    this.salesService.getMonthlyRevenue().subscribe(data => {
      this.salesData.monthly.revenue = data;
    });
    this.salesService.getMonthlyProducts().subscribe(data => {
      this.salesData.monthly.products = data;
    });
    this.salesService.getSizeData().subscribe(data => {
      this.sizeData = data;
    });
  }

  get currentSalesData() {
    return this.salesData[this.selectedPeriod];
  }

  changePeriod(period: string) {
    this.selectedPeriod = period;
    this.loadData(); // Vuelve a cargar los datos si es necesario
  }*/
}
