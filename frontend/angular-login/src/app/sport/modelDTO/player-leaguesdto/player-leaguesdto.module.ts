import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


/*
@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})*/
export class PlayerLeaguesdtoModule { 


    id: number;
    player: number; // Referencia al ID del jugador
    playerName: String;
    league: number; // Referencia al ID de la liga
    partidosGanados: number;
    partidosPerdidos: number;
    partidosJugados: number;
    juegosGanados: number;
    juegosPerdidos: number;
    juegosTotales: number;
    puntosClasificacion: number;
  
    constructor(
      id: number,
      player: number,
      playerName: String,
      league: number,
      partidosGanados: number,
      partidosPerdidos: number,
      partidosJugados: number,
      juegosGanados: number,
      juegosPerdidos: number,
      juegosTotales: number,
      puntosClasificacion: number
    ) {
      this.id = id;
      this.player = player;
      this.playerName = playerName;
      this.league = league;
      this.partidosGanados = partidosGanados;
      this.partidosPerdidos = partidosPerdidos;
      this.partidosJugados = partidosJugados;
      this.juegosGanados = juegosGanados;
      this.juegosPerdidos = juegosPerdidos;
      this.juegosTotales = juegosTotales;
      this.puntosClasificacion = puntosClasificacion;
    }
}
  



