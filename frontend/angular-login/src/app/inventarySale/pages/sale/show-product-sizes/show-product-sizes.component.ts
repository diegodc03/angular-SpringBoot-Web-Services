import { Component } from '@angular/core';
import { sizeElement } from '../../../model/garment/sizeElement.module';
import { GarmentSoldDtoModule } from '../../../modelDTO/garment-sold-dto/garment-sold-dto.module';
import { Router } from '@angular/router';



@Component({
  selector: 'app-show-product-sizes',
  templateUrl: './show-product-sizes.component.html',
  styleUrls: ['./show-product-sizes.component.css']
})
export class ShowProductSizes {


  // In the sizes array we will store the sizes of the product that we want to show
  sizes: sizeElement[] = [];

  sizesSold: GarmentSoldDtoModule[] = [];
  
  productId: String = '';

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  
    const state = history.state;
    console.log(state);
    if (state && state.sizes && state.productId) {
      this.sizes = state.sizes;
      this.productId = state.productId;
    } else {
      console.log('No se encontraron productos en el estado');
    }
  }


  addToCartSize(selectedSize: sizeElement) {
    console.log('Add to cart:', selectedSize.size);

    if (selectedSize.stock === 0) {
      alert('No es posible añadir al carrito un producto sin stock');
      return;
    } else {
      selectedSize.stock--;
      // Is a object "GarmentSoldDtoModule" with the size and the stockSold
      const existingSizeIndex = this.sizesSold.findIndex(s => s.size === selectedSize.size);
      if (existingSizeIndex !== -1) {
        this.sizesSold[existingSizeIndex].stockSold++;
      } else {
        this.sizesSold.push({ size: selectedSize.size, stockSold: 1 });
      }
      console.log('Sizes sold:', this.sizesSold);
    }
  }


  confirmSelection() {
    if (this.sizesSold.length > 0) {
      this.router.navigate(['/dashboard/inventarySale/sales'], { state: { selectedSize: this.sizesSold, productId: this.productId} });
    } else {
      alert('Please select a size before confirming.');
    }
  }







}
