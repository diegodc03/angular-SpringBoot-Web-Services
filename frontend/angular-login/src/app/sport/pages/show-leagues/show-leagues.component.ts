import { Component } from '@angular/core';
import { LeagueDTOModule } from '../../modelDTO/league-dto/league-dto.module';
import { LeagueService } from '../../service/leagueService/league.service';
import { LeagueIdDTOModule } from '../../modelDTO/league-id-dto/league-id-dto.module';

@Component({
  selector: 'app-show-leagues',
  templateUrl: './show-leagues.component.html',
  styleUrl: './show-leagues.component.css'
})
export class ShowLeaguesComponent {
  
    leagues: LeagueDTOModule[] = [];
    selectedFilter: String = '';


    constructor(private leagueService: LeagueService) { 
    }


    ngOnInit(): void {
        this.leagueService.chargeLeagues().subscribe(
            leagues => {
                this.leagues = leagues;
                console.log(leagues);
            }
        );
    }


    requestToJoinLeague(leagueId: number) {
        
        const leagueIdDTO = new LeagueIdDTOModule(leagueId);

        this.leagueService.requestToJoin(leagueIdDTO).subscribe({
            next: (response) => {
                alert(response || 'Solicitud enviada con éxito.');
            },
            error:  (error) =>{
                alert('Error al enviar la solicitud. Por favor, inténtelo de nuevo.');
            }
        });
    }


    joinLeagueDirectly(leagueId: number) {

        const leagueIdDTOModule = {
            leagueId: leagueId
        };

        this.leagueService.joinLeagueDirectly(leagueIdDTOModule).subscribe({
            next: (response) => {
                alert(response || 'Unido a la liga con éxito.');
            },
            error: (error) => {

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
