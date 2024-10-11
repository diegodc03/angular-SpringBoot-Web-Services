import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseLeagueDTO, LeagueDTOModule } from '../../modelDTO/league-dto/league-dto.module';


@Injectable({
  providedIn: 'root'
})
export class LeagueService {
 
  constructor(private http: HttpClient) { }

  
  chargeLeagues(): Observable<LeagueDTOModule[]> {
    return this.http.get<LeagueDTOModule[]>('/leagues/get-all-leagues');
  }


  chargeLeaguesUserJoined(): Observable<BaseLeagueDTO[]> {
    return this.http.get<BaseLeagueDTO[]>('/leagues/get-leagues-joined');
  }


  createLeague(LeagueDTOModule: LeagueDTOModule): Observable<any> {
    return this.http.post(`/leagues/create-league`, { league: LeagueDTOModule });
  }


  requestToJoin(leagueId: number): Observable<any> {
    return this.http.post(`/api/leagues/request-to-join/${leagueId}`, {});
  }

  joinLeagueDirectly(leagueId: number): Observable<any> {
      return this.http.post(`/api/leagues/join/${leagueId}`, {});
  }


  // Tener cuidado con el tipo de parametro que se envia, puede que no se pueda enviar un long como parametro en la url de la peticion http 
  chargeClasification(leagueId: number): Observable<any> {
    return this.http.get(`/leagues/get-league-info`, { params: { leagueId: leagueId } });
  } 

}
