import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


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




}
