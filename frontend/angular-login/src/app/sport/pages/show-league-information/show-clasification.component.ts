import { Component } from '@angular/core';
import { PlayMatchService } from '../../service/playMatchService/play-match.service';
import { LeagueService } from '../../service/leagueService/league.service';
import { LeagueIdService } from '../../service/league-id.service';
import { LeagueInforamationDTO } from '../../modelDTO/league-dto/league-dto.module';

import { PlayerLeaguesdtoModule } from '../../modelDTO/player-leaguesdto/player-leaguesdto.module';
import { PlayMatchDTOWithPlayers } from '../../modelDTO/match-dto/match-dto.module';

@Component({
  selector: 'app-show-clasification',
  templateUrl: './show-league-information.component.html',
  styleUrl: './show-league-information.component.css'
})
export class ShowLeagueInformationComponent {

view: string = 'clasification'; //Por defecto mostramos la clasificación

league: LeagueInforamationDTO | undefined;
leagueId: number;


players: PlayerLeaguesdtoModule[] = []; 
matchs: PlayMatchDTOWithPlayers[] = [];

constructor(private leagueService: LeagueService,
      private leagueIdService: LeagueIdService
) { 
  this.leagueId = this.leagueIdService.getLeagueId();
}


ngOnInit(): void {
  this.getInformation();
  

}


applyFilter() {
  throw new Error('Method not implemented.');
  }
  selectedFilter: any;



  getInformation() {
    console.log('leagueId:', this.leagueId);
    this.leagueService.getLeagueInformation(this.leagueId).subscribe({
      next: (response) => {
        this.league= response;
        this.players = this.league.players;
        this.matchs = this.league.matchs;
        //Cogemos a partir de players el id del usuario y ponemos guardamos en un array los datos de los jugadores

      },
      error: (error) => {
        alert('Error al cargar la información de la liga');
      }
    });
  }


  switchView(view: string) {
    this.view = view;
  }


}
