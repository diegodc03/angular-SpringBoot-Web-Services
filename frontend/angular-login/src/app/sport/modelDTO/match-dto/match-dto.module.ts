import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SetDTOModule } from '../set-dto/set-dto.module';
import { PlayerDTOModule } from '../player-dto/player-dto.module';


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
  jugador1: PlayerDTOModule;
  jugador2: PlayerDTOModule;
  jugador3: PlayerDTOModule;
  jugador4: PlayerDTOModule;
  ganadorEquipo: string
  sets: SetDTOModule[];

  constructor(
    id: number,
    fecha: Date,
    ubicacion: string,
    leagueId: number,
    jugador1: PlayerDTOModule,
    jugador2: PlayerDTOModule,
    jugador3: PlayerDTOModule,
    jugador4: PlayerDTOModule,
    ganadorEquipo: string,
    sets: SetDTOModule[]
  ) {
    this.id = id;
    this.fecha = fecha;
    this.ubicacion = ubicacion;
    this.leagueId = leagueId;
    this.jugador1 = jugador1;
    this.jugador2 = jugador2;
    this.jugador3 = jugador3;
    this.jugador4 = jugador4;
    this.ganadorEquipo = ganadorEquipo;
    this.sets = sets;
  }


}
