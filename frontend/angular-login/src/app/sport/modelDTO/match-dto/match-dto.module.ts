import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SetDTOModule } from '../set-dto/set-dto.module';


/*
@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})*/
export class MatchDTOModule { 

  id: number;
  fecha: Date;
  ubicacion: string;
  leagueId: number;
  jugador1Id: number;
  jugador2Id: number;
  jugador3Id: number;
  jugador4Id: number;
  ganadorEquipo: string;
  sets: SetDTOModule[];

  constructor(
    id: number,
    fecha: Date,
    ubicacion: string,
    leagueId: number,
    jugador1Id: number,
    jugador2Id: number,
    jugador3Id: number,
    jugador4Id: number,
    ganadorEquipo: string,
    sets: SetDTOModule[]
  ) {
    this.id = id;
    this.fecha = fecha;
    this.ubicacion = ubicacion;
    this.leagueId = leagueId;
    this.jugador1Id = jugador1Id;
    this.jugador2Id = jugador2Id;
    this.jugador3Id = jugador3Id;
    this.jugador4Id = jugador4Id;
    this.ganadorEquipo = ganadorEquipo;
    this.sets = sets;
  }


}
