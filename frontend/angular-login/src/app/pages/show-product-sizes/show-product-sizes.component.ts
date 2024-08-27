import { Component } from '@angular/core';
import { Garment } from 'src/app/model/garment/garment.module';
import { GarmentSoldDtoModule } from 'src/app/model/garment-sold-dto/garment-sold-dto.module';
import { Router } from '@angular/router';


@Component({
  selector: 'app-show-product-sizes',
  templateUrl: './show-product-sizes.component.html',
  styleUrls: ['./show-product-sizes.component.css']
})
export class ShowProductSizes {


  // In the sizes array we will store the sizes of the product that we want to show
  sizes: Garment[] = [];

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



  addToCartSize(selectedSize: Garment) {
    console.log('Add to cart:', selectedSize.size);

    if (selectedSize.stock === 0) {
      alert('No es posible añaadir al carrito un producto sin stock');
      return;
    }
    else{
      selectedSize.stock--;
      // Is a object "GarmentSoldDtoModule" with the size and the stockSold
      const existingSize = this.sizesSold.find(s => s.size === selectedSize.size);
      if (existingSize) {
        existingSize.stockSold++;

      } else {
        this.sizesSold.push({ size: selectedSize.size, stockSold: 1 });
      }
    }
    
  }


  confirmSelection() {
    if (this.sizesSold.length > 0) {
      this.router.navigate(['/dashboard/inventarySale/sale'], { state: { selectedSize: this.sizesSold, productId: this.productId} });
    } else {
      alert('Please select a size before confirming.');
    }
  }







}
