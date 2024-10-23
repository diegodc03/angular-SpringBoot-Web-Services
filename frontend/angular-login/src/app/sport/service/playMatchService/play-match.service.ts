import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PlayMatchDTOWithIds, PlayMatchDTOWithPlayers } from '../../modelDTO/match-dto/match-dto.module';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlayMatchService {
  
  private apiURL = "http://localhost:8080/sport";
  
  addPlayMatch(playMatchDTO: PlayMatchDTOWithIds): Observable<any> {
    return this.http.post<any>(`${this.apiURL}/playMatch/add-play-match`, playMatchDTO);
  }

  getMatchInformation(matchId: number): Observable<PlayMatchDTOWithPlayers> {
    return this.http.get<PlayMatchDTOWithPlayers>(`${this.apiURL}/playMatch/get-match-by-id/${matchId}`);
  }


  constructor(private http: HttpClient) { }

}
