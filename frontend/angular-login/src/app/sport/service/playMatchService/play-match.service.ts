import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PlayMatchDTOWithIds, PlayMatchDTOWithPlayers } from '../../modelDTO/match-dto/match-dto.module';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlayMatchService {
  
  private apiURL = "http://localhost:8080/sport";
  
  addPlayMatch(playMatchDTO: PlayMatchDTOWithIds): Observable<string> {
    return this.http.post<string>(`${this.apiURL}/playMatch/add-play-match`, playMatchDTO , { responseType: 'text' as 'json' });
  }

  getMatchInformation(matchId: number): Observable<PlayMatchDTOWithPlayers> {
    return this.http.get<PlayMatchDTOWithPlayers>(`${this.apiURL}/playMatch/get-match-by-id/${matchId}`);
  }

  deletePlayMatch(matchId: number): Observable<string> {
    return this.http.delete<string>(`${this.apiURL}/playMatch/delete-play-match/${matchId}`, { responseType: 'text' as 'json' });
  }


  constructor(private http: HttpClient) { }

}
