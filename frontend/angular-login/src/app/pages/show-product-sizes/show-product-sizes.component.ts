import { Component } from '@angular/core';
import { Garment } from 'src/app/model/garment/garment.module';

@Component({
  selector: 'app-show-product-sizes',
  templateUrl: './show-product-sizes.component.html',
  styleUrls: ['./show-product-sizes.component.css']
})
export class ShowProductSizes {


  // In the sizes array we will store the sizes of the product that we want to show
  sizes: Garment[] = [];

  constructor() { }

  ngOnInit(): void {
  
    const state = history.state;
    console.log(state);
    if (state && state.sizes) {
      this.sizes = state.sizes;
    } else {
      console.log('No se encontraron productos en el estado');
    }
  }



  addToCartSize() {
    throw new Error('Method not implemented.');
  }








}
