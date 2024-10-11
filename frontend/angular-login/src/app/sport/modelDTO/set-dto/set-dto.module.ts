import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


/*
@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})*/
export class SetDTOModule {


  id: number;
  numeroSet: number;
  juegosEquipo1: number;
  juegosEquipo2: number;

  constructor(
    id: number,
    numeroSet: number,
    juegosEquipo1: number,
    juegosEquipo2: number
  ) {
    this.id = id;
    this.numeroSet = numeroSet;
    this.juegosEquipo1 = juegosEquipo1;
    this.juegosEquipo2 = juegosEquipo2;
  }
  


 }
