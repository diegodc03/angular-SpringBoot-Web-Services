import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { PlayerDTOModule } from '../../modelDTO/player-dto/player-dto.module';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  private apiURL = "http://localhost:8080/player";

  constructor(private http: HttpClient) { }

  getPlayers(leagueId: number): Observable<PlayerDTOModule[]> {

    return this.http.get<PlayerDTOModule[]>(`${this.apiURL}/all-players`, { params: { leagueId: leagueId } });
  }

  
}
