import { Component } from '@angular/core';
import { LeagueDTOModule } from '../../modelDTO/league-dto/league-dto.module';
import { LeagueService } from '../../service/leagueService/league.service';

@Component({
  selector: 'app-show-leagues',
  templateUrl: './show-leagues.component.html',
  styleUrl: './show-leagues.component.css'
})
export class ShowLeaguesComponent {
  
  constructor(private leagueService: LeagueService) { 

    
  }


  ngOnInit(): void {
    
    this.leagueService.chargeLeagues().subscribe(
        leagues => {
            this.leagues = leagues;
        }
    );

  }


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
    this.leagueService.joinLeagueDirectly(leagueId).subscribe({
        next: (response) => {
            alert(response || 'Unido a la liga con éxito.');
        },
        error: (error) => {
            console.error("Error al unirse a la liga:", error);

            // Mostrar un mensaje de error personalizado
            if (error.status === 404) {
                alert("No se encontró el usuario, jugador o liga.");
            } else if (error.status === 409) {
                alert("El jugador ya es parte de esta liga.");
            } else {
                alert("Error inesperado al unirse a la liga.");
            }
        }
    });
}



  

}
