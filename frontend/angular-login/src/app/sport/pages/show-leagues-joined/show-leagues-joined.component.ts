import { Component } from '@angular/core';
import { LeagueDTOModule } from '../../modelDTO/league-dto/league-dto.module';

@Component({
  selector: 'app-show-leagues-joined',
  templateUrl: './show-leagues-joined.component.html',
  styleUrl: './show-leagues-joined.component.css'
})
export class ShowLeaguesJoinedComponent {


  selectedFilter: string = '';
  leagues: LeagueDTOModule[] = [];

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
