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
export class BaseDTOModule { 

  id: number;
  fecha: Date;
  ubicacion: string;
  leagueId: number;
  ganadorEquipo: string
  sets: SetDTOModule[];

  constructor(
    id: number,
    fecha: Date,
    ubicacion: string,
    leagueId: number,
    ganadorEquipo: string,
    sets: SetDTOModule[]
  ) {
    this.id = id;
    this.fecha = fecha;
    this.ubicacion = ubicacion;
    this.leagueId = leagueId;
    this.ganadorEquipo = ganadorEquipo;
    this.sets = sets;
  }


}


export class PlayMatchDTOWithIds extends BaseDTOModule{
  
  jugador1Id: number
  jugador2Id: number
  jugador3Id: number
  jugador4Id: number
  
  
  constructor(
    id: number,
    fecha: Date,
    ubicacion: string,
    leagueId: number,
    ganadorEquipo: string,
    sets: SetDTOModule[],
    jugador1: number,
    jugador2: number,
    jugador3: number,
    jugador4: number,

  ) {
    super(id, fecha, ubicacion, leagueId, ganadorEquipo, sets);

    this.jugador1Id = jugador1;
    this.jugador2Id = jugador2;
    this.jugador3Id = jugador3;
    this.jugador4Id = jugador4;

  }
}





export class PlayMatchDTOWithPlayers extends BaseDTOModule{

  jugador1: PlayerDTOModule;
  jugador2: PlayerDTOModule;
  jugador3: PlayerDTOModule;
  jugador4: PlayerDTOModule;

  constructor(
    id: number,
    fecha: Date,
    ubicacion: string,
    leagueId: number,
    ganadorEquipo: string,
    sets: SetDTOModule[],
    jugador1: PlayerDTOModule,
    jugador2: PlayerDTOModule,
    jugador3: PlayerDTOModule,
    jugador4: PlayerDTOModule
  ) {
    super(id, fecha, ubicacion, leagueId, ganadorEquipo, sets);
    this.jugador1 = jugador1;
    this.jugador2 = jugador2;
    this.jugador3 = jugador3;
    this.jugador4 = jugador4;
  }

}