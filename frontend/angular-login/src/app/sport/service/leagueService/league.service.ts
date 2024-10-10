import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseLeagueDTO, LeagueDTOModule } from '../../modelDTO/league-dto/league-dto.module';


@Injectable({
  providedIn: 'root'
})
export class LeagueService {
 
  

  constructor(private http: HttpClient) { }


  requestToJoin(leagueId: number): Observable<any> {
    return this.http.post(`/api/leagues/request-to-join/${leagueId}`, {});
  }

  joinLeagueDirectly(leagueId: number): Observable<any> {
      return this.http.post(`/api/leagues/join/${leagueId}`, {});
  }

  chargeLeagues(): Observable<LeagueDTOModule[]> {
    return this.http.get<LeagueDTOModule[]>('/leagues/get-all-leagues');
  }


  chargeLeaguesUserJoined(): Observable<BaseLeagueDTO[]> {
    return this.http.get<BaseLeagueDTO[]>('/leagues/get-leagues-joined');
  }



}
