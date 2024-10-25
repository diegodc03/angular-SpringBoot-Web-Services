import { Component } from '@angular/core';
import { BaseLeagueDTO, LeagueDTOModule, ShowLeagueDTO } from '../../modelDTO/league-dto/league-dto.module';
import { LeagueService } from '../../service/leagueService/league.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LeagueIdService } from '../../service/league-id.service';


@Component({
  selector: 'app-show-leagues-joined',
  templateUrl: './show-leagues-joined.component.html',
  styleUrl: './show-leagues-joined.component.css'
})
export class ShowLeaguesJoinedComponent {

  selectedFilter: string = '';
  leagues: ShowLeagueDTO[] = [];

  constructor(
      private leagueIdService: LeagueIdService, 
      private leagueService: LeagueService,
      private http: HttpClient,
      private router: Router) { }


  ngOnInit(): void {
    
    this.leagueService.chargeLeaguesUserJoined().subscribe(
        leagues => {
            this.leagues = leagues;
        }
    );
  }


  leagueInformation(leagueId: number) {
    this.leagueIdService.setLeagueId(leagueId);
    this.router.navigate(['/sports/league-information', leagueId]);
  }


  leagueExit(leagueId: number) {

    const LeagueIdDTOModule = {
      leagueId: leagueId
    };

    this.leagueService.exitLeague(LeagueIdDTOModule).subscribe({
        next: (response) => {
            alert(response || 'Salida de la liga con éxito.');
        },
        error: (error) => {
            alert('Error al salir de la liga. Por favor, inténtelo de nuevo.');
        }
    });
  }


}
