import { Component } from '@angular/core';
import { PlayMatchService } from '../../service/playMatchService/play-match.service';
import { LeagueService } from '../../service/leagueService/league.service';
import { LeagueIdService } from '../../service/league-id.service';
import { LeagueInforamationDTO } from '../../modelDTO/league-dto/league-dto.module';
import { MatchDTOModule } from '../../modelDTO/match-dto/match-dto.module';
import { PlayerLeaguesdtoModule } from '../../modelDTO/player-leaguesdto/player-leaguesdto.module';

@Component({
  selector: 'app-show-clasification',
  templateUrl: './show-league-information.component.html',
  styleUrl: './show-league-information.component.css'
})
export class ShowLeagueInformationComponent {

league: LeagueInforamationDTO | undefined;
leagueId: number;


players: PlayerLeaguesdtoModule[] = []; 
matchs: MatchDTOModule[] = [];

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

  this.leagueService.getLeagueInformation(this.leagueId).subscribe({
    next: (response) => {
      this.league= response;
      this.players = this.league.players;
      this.matchs = this.league.matchs;

    },
    error: (error) => {
      alert('Error al cargar la información de la liga');
    }
  });


}


}
