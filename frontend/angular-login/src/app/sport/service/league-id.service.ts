import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root', // Esto asegura que el servicio esté disponible en toda la aplicación
})
export class LeagueIdService {

  private leagueId: number = 0;

  getLeagueId(): number {
    return this.leagueId;
  }
  
  setLeagueId(leagueId: number): void {
    this.leagueId = leagueId;
  }


  constructor() { }
}
