// inventary.component.ts
import { Component, NgModule, OnInit } from '@angular/core';
import { InventaryService } from "../../../service/inventary/inventary.service";
import { Product } from '../../../model/product/product.module';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';



@Component({
  selector: 'app-inventary',
  templateUrl: './inventary.component.html',
  styleUrls: ['./inventary.component.css']
})
export class InventaryComponent implements OnInit {

  products: Product[] = [];

  selectedFilter: string = 'none';
  
  showGarments: boolean[] = this.products.map(() => false);

    
  i: any;

  constructor(private inventaryService: InventaryService,
              private router : RouterModule,
              private route : Router
  ) {}

  deleteProduct(publicId: string): void {
    this.inventaryService.deleteProduct(publicId).subscribe(() => {
      this.loadProducts();
    });
  }

  toggleGarments(index: number, event?: Event): void {  
    if (event) {
        event.stopPropagation(); // Evitar que el clic en el botón propague el evento al contenedor
    }
    this.showGarments[index] = !this.showGarments[index];
  }


  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.inventaryService.getAllProducts().subscribe(inventary => {
      this.products = inventary;
      console.log("Productos cargados1");
      console.log(this.inventaryService.getProductsByPriceAscending());
      console.log("Productos cargados2");
      console.log(this.products);

      console.log("Productos cargados3");

    });
  }

  applyFilter(): void {
    switch (this.selectedFilter) {
      case 'price-ascending':
        this.inventaryService.getProductsByPriceAscending().subscribe(inventary => {
          this.products = inventary;
        });
        break;
      case 'price-descending':
        this.inventaryService.getProductsByPriceDescending().subscribe(inventary => {
          this.products = inventary;
        });
        break;
      case 'stock-ascending':
        this.inventaryService.getProductsByStockAscending().subscribe(inventary => {
          this.products = inventary;
        });
        break;
      case 'stock-descending':
        this.inventaryService.getProductsByStockDescending().subscribe(inventary => {
          this.products = inventary;
        });
        break;
      default:
        this.loadProducts();
        break;
    }
  }
}
