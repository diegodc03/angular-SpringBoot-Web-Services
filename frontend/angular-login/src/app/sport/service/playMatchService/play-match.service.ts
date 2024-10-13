import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MatchDTOModule } from '../../modelDTO/match-dto/match-dto.module';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlayMatchService {
  
  
  private apiURL = "http://localhost:8080/sport";
  
  addPlayMatch(playMatchDTO: MatchDTOModule): Observable<any> {
    return this.http.post<any>(`${this.apiURL}/playMatch/add-play-match`, playMatchDTO);
  }

  constructor(private http: HttpClient) { }
}
