import { Component } from '@angular/core';
import { PlayMatchService } from '../../service/playMatchService/play-match.service';
import { LeagueService } from '../../service/leagueService/league.service';
import { LeagueIdService } from '../../service/league-id.service';
import { LeagueInforamationDTO } from '../../modelDTO/league-dto/league-dto.module';

import { PlayerLeaguesdtoModule } from '../../modelDTO/player-leaguesdto/player-leaguesdto.module';
import { PlayMatchDTOWithPlayers } from '../../modelDTO/match-dto/match-dto.module';
import { ActivatedRoute, Router } from '@angular/router';

  @Component({
    selector: 'app-show-clasification',
    templateUrl: './show-league-information.component.html',
    styleUrl: './show-league-information.component.css'
  })
  export class ShowLeagueInformationComponent {

  view: string = 'partidos'; //Por defecto mostramos la clasificación

  league: LeagueInforamationDTO | undefined;
  leagueId: number;
  selectedFilter:  string = 'points-descending';

  players: PlayerLeaguesdtoModule[] = []; 
  matchs: PlayMatchDTOWithPlayers[] = [];

  constructor(private leagueService: LeagueService,
        private leagueIdService: LeagueIdService,
        private router: Router,
        private route: ActivatedRoute

  ) { 
    this.leagueId = this.leagueIdService.getLeagueId();
    this.view = 'clasification';
  }



  ngOnInit(): void {
    // Suscribirse a los parámetros de la URL
    

    this.route.params.subscribe(params => {
      // Obtener el leagueId desde los parámetros de la URL
      this.leagueId = +params['leagueId']; // '+' convierte el parámetro string en número
      if (this.leagueId) {
        this.getInformation(); // Llamar a tu función para obtener la información
      } else {
        alert('No se encontró el ID de la liga.');
      }
    });
    //this.view = 'clasification';
    this.applyFilter();
  
  }


  applyFilter() {
    
    switch (this.selectedFilter) {
      case 'name-descending':
        this.players.sort((a, b) => b.playerName.toString().localeCompare(a.playerName.toString())); // Alfabéticamente mayor a menor
        break;
      case 'match-descending':
        this.players.sort((a, b) => b.partidosGanados - a.partidosGanados); // Más gente (partidos ganados)
        break;
      case 'match-descending':
        this.players.sort((a, b) => b.partidosJugados - a.partidosJugados); // Más partidos Jugados
        break;
      case 'match-ascending':
        this.players.sort((a, b) => a.partidosJugados - b.partidosJugados); // Menos partidos Jugados
        break;
      case 'points-descending':
        this.players.sort((a, b) => b.puntosClasificacion - a.puntosClasificacion); // Más puntos
        break;
      case 'points-ascending':
        this.players.sort((a, b) => a.puntosClasificacion - b.puntosClasificacion); // Menos puntos
        break;
      case 'games-descending':
        this.players.sort((a, b) => b.juegosGanados - a.juegosGanados); // Más juegos Ganados
        break;
      case 'games-ascending':
        this.players.sort((a, b) => a.juegosGanados - b.juegosGanados); // Menos juegos Ganados
        break;
      default:
        // Orden por defecto, por ejemplo, podrías no hacer nada o volver a la orden original.
        break;
    }

  }

  



    getInformation() {
      console.log('leagueId:', this.leagueId);
      this.leagueService.getLeagueInformation(this.leagueId).subscribe({
        next: (response) => {
          this.league= response;
          this.players = this.league.playerLeagues;
          this.matchs = this.league.matchPlayed
          console.log("players", this.players);
          console.log("match", this.matchs)
          //Cogemos a partir de players el id del usuario y ponemos guardamos en un array los datos de los jugadores

          // Verificamos si los datos de la liga están presentes y luego cambiamos la vista
        // Cambia la vista a 'clasificacion' si hay datos de jugadores o partidos
        if (this.players.length > 0 || this.matchs.length > 0) {
          this.switchView('clasificacion');  // Cambia la vista a 'clasificacion'
        }

        },
        error: (error) => {
          alert('Error al cargar la información de la liga');
        }
      });
    }


    switchView(view: string) {
      this.view = view;
    }

    goToMatchDetail(matchId: number) {
      this.router.navigate(['sports/show-match', matchId]);
      
    }

      // Método para navegar a la página de creación de partidos basado en el tipo de liga
  createMatch() {
    if (this.league?.leagueType === 'tenis') {
      this.router.navigate(['sports/create-tennis-match']);
    } else if (this.league?.leagueType === 'padel') {
      this.router.navigate(['sports/create-padel-match']);
    } else {
      alert('No se puede determinar el tipo de liga.');
    }
  }

  }
