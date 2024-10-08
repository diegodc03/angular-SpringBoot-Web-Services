import { Component } from '@angular/core';
import { LeagueDTOModule } from '../../modelDTO/league-dto/league-dto.module';

@Component({
  selector: 'app-show-leagues',
  templateUrl: './show-leagues.component.html',
  styleUrl: './show-leagues.component.css'
})
export class ShowLeaguesComponent {
  
  
  leagues: LeagueDTOModule[] = [];

  selectedFilter: String = '';


  applyFilter() {
    throw new Error('Method not implemented.');
  }


  requestToJoinLeague(arg0: any) {
    throw new Error('Method not implemented.');
  }

  

}
