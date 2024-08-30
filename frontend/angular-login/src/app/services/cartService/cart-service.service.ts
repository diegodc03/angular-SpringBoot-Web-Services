import { Injectable } from '@angular/core';
import { ProductSoldDTO } from 'src/app/model/product-sold-dto/product-sold-dto.module';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  

  private productsSold: ProductSoldDTO[] = [];

  addProduct(product: ProductSoldDTO) {
    const index = this.productsSold.findIndex(p => p.productId === product.productId);
    if (index !== -1) {
      // Si el producto ya está en el carrito, actualiza sus valores
      this.productsSold[index] = product;
    } else {
      // Si no está, añádelo al carrito
      this.productsSold.push(product);
    }
  }

  getProducts(): ProductSoldDTO[] {
    return this.productsSold;
  }

  clearCart() {
    this.productsSold = [];
  }


  deleteProduct(product: ProductSoldDTO) {
    
    const index = this.productsSold.findIndex(p => p.productId === product.productId);
    if (index !== -1) {
      this.productsSold.splice(index, 1);
    }
    console.log('Producto eliminado del carrito:', this.productsSold);
  }

}
