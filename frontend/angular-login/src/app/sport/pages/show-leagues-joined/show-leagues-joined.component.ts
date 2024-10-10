import { Component } from '@angular/core';
import { BaseLeagueDTO, LeagueDTOModule } from '../../modelDTO/league-dto/league-dto.module';
import { LeagueService } from '../../service/leagueService/league.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-show-leagues-joined',
  templateUrl: './show-leagues-joined.component.html',
  styleUrl: './show-leagues-joined.component.css'
})
export class ShowLeaguesJoinedComponent {



  selectedFilter: string = '';
  leagues: BaseLeagueDTO[] = [];

  constructor(
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


  leagueInformation() {
    this.router.navigate(['/league-information']);
  }



  showLeague(arg0: any) {
    throw new Error('Method not implemented.');
  }
  
  leagueExit(arg0: any) {
    throw new Error('Method not implemented.');
  }
  
  applyFilter() {
    throw new Error('Method not implemented.');
  }

}
