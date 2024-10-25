import { Component, Input } from '@angular/core';
import { MatchDTO } from 'src/app/workHours/modelDTO/MatchDTO/MatchDTO';
import { PlayMatchDTOWithPlayers } from '../../modelDTO/match-dto/match-dto.module';
import { MatchService } from 'src/app/workHours/service/MatchService/match.service';
import { PlayMatchService } from '../../service/playMatchService/play-match.service';
import { PlayerDTOModule } from '../../modelDTO/player-dto/player-dto.module';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-show-matches',
  templateUrl: './show-matches.component.html',
  styleUrl: './show-matches.component.css'
})
export class ShowMatchesComponent {

  // Recibimos los partidos desde el componente padre
  // Recibimos el partido desde el componente padre
  // Inicializamos con un valor vacío
  match: PlayMatchDTOWithPlayers = new PlayMatchDTOWithPlayers(0, new Date(), '', 0, '', [], 
    new PlayerDTOModule(0, ''), // Jugador 1 vacío
    new PlayerDTOModule(0, ''), // Jugador 2 vacío
    new PlayerDTOModule(0, ''), // Jugador 3 vacío
    new PlayerDTOModule(0, '')  // Jugador 4 vacío
  );

  matchId: number;

  constructor(
    private playMatchService: PlayMatchService,
    private route: ActivatedRoute
  ) {
    // Obtiene el ID del partido desde la URL
    this.matchId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.getMatchInformation(this.matchId);
  }

  // Actualizamos el método para aceptar el parámetro matchId
  getMatchInformation(matchId: number) {
    // Usa el servicio para obtener la información del partido con el matchId
    this.playMatchService.getMatchInformation(matchId).subscribe({
      next: (response) => {
        this.match = response;
      },
      error: (error) => {
        alert('Error al obtener la información del partido. Por favor, inténtelo de nuevo.');
      }
    });
  }
}
