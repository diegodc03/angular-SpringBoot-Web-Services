import { Component } from '@angular/core';
import { LeagueDTOModule } from '../../modelDTO/league-dto/league-dto.module';
import { LeagueService } from '../../service/leagueService/league.service';

@Component({
  selector: 'app-show-leagues',
  templateUrl: './show-leagues.component.html',
  styleUrl: './show-leagues.component.css'
})
export class ShowLeaguesComponent {
  
  constructor(private leagueService: LeagueService) { }


  leagues: LeagueDTOModule[] = [];

  selectedFilter: String = '';


  applyFilter() {
    throw new Error('Method not implemented.');
  }



  requestToJoinLeague(leagueId: number) {
    this.leagueService.requestToJoin(leagueId).subscribe(
        response => {
            alert(response.message || 'Solicitud enviada con éxito.');
        },
        error => {
            console.error('Error al enviar solicitud:', error);
            alert('Error al enviar la solicitud. Por favor, inténtelo de nuevo.');
        }
    );
}

joinLeagueDirectly(leagueId: number) {
    this.leagueService.joinLeagueDirectly(leagueId).subscribe(
        response => {
            alert(response.message || 'Te has unido a la liga exitosamente.');
        },
        error => {
            console.error('Error al unirse a la liga:', error);
            alert('Error al unirse a la liga. Por favor, inténtelo de nuevo.');
        }
    );
}


  

}
