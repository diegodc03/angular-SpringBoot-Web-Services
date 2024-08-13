import { Inject, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class Garment { 

  id: number;
  size: string;
  color: string;
  material: string;
  stock: number;

  constructor(@Inject(Number) id: number, @Inject(String) size: string, @Inject(String) color: string, @Inject(String) material: string, @Inject(Number) stock: number) {
      this.id = id;
      this.size = size;
      this.color = color;
      this.material = material;
      this.stock = stock;
    }

}
