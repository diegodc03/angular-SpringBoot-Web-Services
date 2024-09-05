import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, of, tap } from 'rxjs';
import { ProductSoldDTO } from 'src/app/model/product-sold-dto/product-sold-dto.module';
import { Product } from 'src/app/model/product/product.module';
import { SaleService } from 'src/app/services/sale/sale.service';
import { CartService } from 'src/app/services/cartService/cart-service.service';
import { ProductsToSaleService } from 'src/app/services/products-to-sale/products-to-sale.module';
import { Garment } from 'src/app/model/garment/garment.module';

@Component({
  selector: 'app-shopping-basket',
  templateUrl: './shopping-basket.component.html',
  styleUrls: ['./shopping-basket.component.css']
})
export class ShoppingBasketComponent {

  productsSold: ProductSoldDTO[] = [];
  showGarments: boolean[] = this.productsSold.map(() => false);

  constructor(
    private router: Router,
    private saleService: SaleService,
    private cartService: CartService,
    private productsToSaleService: ProductsToSaleService
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
    if(this.productsSold.length === 0) {
      console.error('No hay productos en el carrito');
      return;
    }

    // Clear the elements updated, the elements are now updated in the db
    this.productsToSaleService.clearCart();
    this.cartService.clearCart();

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
      this.cartService.deleteProduct(product);

      const products = this.productsToSaleService.getProductsToSale();
      const index2 = products.findIndex(p => p.publicId === product.productId);
      if(index2 !== -1){
        products[index2].totalStock += product.totalStockSold;
        this.productsToSaleService.saveProductsToSale(products);

        // Existen tallas para este producto
        // implica que hay que recorrer las tallas y sumar el stock a cada talla en productsToSale
        if(product.existanceSizes){
          const sizesArray: Garment[] = [];
          for(let size of product.garmentsSales){
            const foundSize = products[index2].garments.find(garment => garment.size === size.size);
            if (foundSize) {
              foundSize.stock = foundSize.stock + size.stockSold;
              // Ahora hay que introducir el stock en el array de productsToSale
              // para que se actualice en la vista
              //hacemos un array de garment y luego lo metemos todo
              sizesArray.push(new Garment(foundSize.id, foundSize.size, foundSize.color, foundSize.material, foundSize.stock));
            }
          }
          products[index2].garments = sizesArray;
          this.productsToSaleService.saveProductsToSale(products);
       
          
        }
      }
    }
  }

  toggleGarments(index: number, event?: Event): void {  
    if (event) {
        event.stopPropagation(); // Evitar que el clic en el botón propague el evento al contenedor
    }
    this.showGarments[index] = !this.showGarments[index];
  }


}
