import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseLeagueDTO, CreateLeagueDTO, LeagueDTOModule, LeagueInforamationDTO, ShowLeagueDTO } from '../../modelDTO/league-dto/league-dto.module';
import { LeagueIdDTOModule } from '../../modelDTO/league-id-dto/league-id-dto.module';


@Injectable({
  providedIn: 'root'
})
export class LeagueService {
  
 
  constructor(private http: HttpClient) { }

  apiURL: string = 'http://localhost:8080/sport';

  

  chargeLeagues(): Observable<LeagueDTOModule[]> {
    return this.http.get<LeagueDTOModule[]>(`${this.apiURL}/league/get-all-leagues`);
  }


  chargeLeaguesUserJoined(): Observable<ShowLeagueDTO[]> {
    return this.http.get<ShowLeagueDTO[]>(`${this.apiURL}/league/get-leagues-joined`);
  }


  createLeague(createLeagueDTO: CreateLeagueDTO): Observable<string> {
    return this.http.post<string>(`${this.apiURL}/league/create-league`, createLeagueDTO,{ responseType: 'text' as 'json' });
  }


  requestToJoin(leagueIdDTO: LeagueIdDTOModule): Observable<String> {
    return this.http.post<String>(`${this.apiURL}/player/request-to-join-league`, leagueIdDTO, { responseType: 'text' as 'json' });
  }

  joinLeagueDirectly(leagueId: LeagueIdDTOModule): Observable<String> {
      return this.http.post<String>(`${this.apiURL}/player/join-league`, leagueId,{

        responseType: 'text' as 'json' 
      });
  }


  // Tener cuidado con el tipo de parametro que se envia, puede que no se pueda enviar un long como parametro en la url de la peticion http 
  chargeClasification(leagueId: number): Observable<any> {
    return this.http.get(`/leagues/get-league-info`, { params: { leagueId: leagueId } });
  } 



  exitLeague(deletePlayer: LeagueIdDTOModule): Observable<string> {

    return this.http.delete<string>(`${this.apiURL}/player/get-out-of-league`, {
      body: deletePlayer,  // Envía el objeto directamente
      responseType: 'text' as 'json'
    });
  }


  getLeagueInformation(leagueId: number): Observable<LeagueInforamationDTO> {
    return this.http.get<LeagueInforamationDTO>(`${this.apiURL}/league/get-league-information/${leagueId}`);
  }

}
