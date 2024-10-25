import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


export class PlayerDTOModule { 

  
    id: number;           // ID del jugador (único e identificador)
    name: string;         // Nombre del jugador

    constructor(
      id: number,
      name: string
    ) {
      this.id = id;
      this.name = name;
    }
  
  


}
