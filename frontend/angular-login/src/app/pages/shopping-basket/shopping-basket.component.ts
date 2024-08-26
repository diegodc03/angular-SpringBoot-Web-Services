import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, of, tap } from 'rxjs';
import { ProductSoldDTO } from 'src/app/model/product-sold-dto/product-sold-dto.module';
import { Product } from 'src/app/model/product/product.module';
import { SaleService } from 'src/app/services/sale/sale.service';

@Component({
  selector: 'app-shopping-basket',
  templateUrl: './shopping-basket.component.html',
  styleUrls: ['./shopping-basket.component.css']
})
export class ShoppingBasketComponent {

  productsSold: ProductSoldDTO[] = [];


  constructor(
    private router: Router,
    private saleService: SaleService
  ) { }

  //ngOnInit(): muestra el array de productos que el usuario ha seleccionado para comprar
  ngOnInit(): void {
    const state = history.state;
    console.log(state);
    if (state && state.products) {
      this.productsSold = state.products;
    } else {
      console.log('No se encontraron productos en el estado');
    }
  }

  // Send to backend the products that the user has selected to buy
  // The backed update the stock of the products and create a sale
  saleConfirm() {

    // Call the backend to create the sale

    this.saleService.setNewSale(this.productsSold).pipe(
      tap((response) => {
        console.log('Sale created successfully:', response);
        this.router.navigate(['/dashboard/inventarySale/sales']);
      }),
      catchError((error) => {
        console.error('Error occurred while creating sale:', error);
        return of(null); // Return a safe value or handle the error appropriately
      })
    ).subscribe();
  }



  //angular deletes the product from the shopping basket when the user clicks on the delete button and automatically updates the view
  deleteShoppingBasket(product: ProductSoldDTO ) { 
    const index = this.productsSold.findIndex(p => p.productId === product.productId);
    if (index !== -1) {
      // delete 1 element from index
      this.productsSold.splice(index, 1);
    }
  }


}
