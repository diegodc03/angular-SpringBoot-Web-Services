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
    playerId: number; // Referencia al ID del jugador
    leagueId: number; // Referencia al ID de la liga
    partidosGanados: number;
    partidosPerdidos: number;
    partidosJugados: number;
    juegosGanados: number;
    juegosPerdidos: number;
    juegosTotales: number;
    puntosClasificacion: number;
  
    constructor(
      id: number,
      playerId: number,
      leagueId: number,
      partidosGanados: number,
      partidosPerdidos: number,
      partidosJugados: number,
      juegosGanados: number,
      juegosPerdidos: number,
      juegosTotales: number,
      puntosClasificacion: number
    ) {
      this.id = id;
      this.playerId = playerId;
      this.leagueId = leagueId;
      this.partidosGanados = partidosGanados;
      this.partidosPerdidos = partidosPerdidos;
      this.partidosJugados = partidosJugados;
      this.juegosGanados = juegosGanados;
      this.juegosPerdidos = juegosPerdidos;
      this.juegosTotales = juegosTotales;
      this.puntosClasificacion = puntosClasificacion;
    }
}
  



