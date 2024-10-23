import { Component } from '@angular/core';
import { PlayMatchDTOWithIds } from '../../modelDTO/match-dto/match-dto.module';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LeagueService } from '../../service/leagueService/league.service';
import { PlayMatchService } from '../../service/playMatchService/play-match.service';
import { LeagueIdService } from '../../service/league-id.service';
import { PlayerService } from '../../service/playerService/player.service';
import { PlayerDTOModule } from '../../modelDTO/player-dto/player-dto.module';

@Component({
  selector: 'app-create-tennis-match',
  templateUrl: './create-tennis-match.component.html',
  styleUrl: './create-tennis-match.component.css'
})
export class CreateTennisMatchComponent {

  isFourPlayers: boolean = false;
  
  leagueId: number = this.leagueIdService.getLeagueId();
 
  availablePlayers: PlayerDTOModule[] = []; // Cargar los jugadores desde el servicio
  addPlayMatchError = '';
  addPlayMatchForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private playerService: PlayerService,
    private leagueService: LeagueService,
    private playMatchService: PlayMatchService,
    private leagueIdService: LeagueIdService
  ) {

    this.addPlayMatchForm = this.fb.group({
      fecha: ['', Validators.required],
      ubicacion: ['', Validators.required],
      jugador1: ['', Validators.required],
      jugador2: ['', Validators.required],
      jugador3: [''],
      jugador4: [''],
      sets: this.fb.array([])
    });

  }

  ngOnInit() {
   
    this.loadPlayers();
  }

  get sets(): FormArray {
    return this.addPlayMatchForm.get('sets') as FormArray;
  }

  

  addSet() {
    this.sets.push(
      this.fb.group({
        numeroSet: ['', Validators.required],
        juegosEquipo1: ['', Validators.required],
        juegosEquipo2: ['', Validators.required],
      })
    );
  }

  removeSet(index: number) {
    this.sets.removeAt(index);
  }

  isPlayerSelected(playerId: number): boolean {
    const { jugador1, jugador2, jugador3, jugador4 } = this.addPlayMatchForm.value;
    return [jugador1, jugador2, jugador3, jugador4].includes(playerId);
  }

  onSubmit() {
    if (this.addPlayMatchForm.valid) {

      const matchDTO: PlayMatchDTOWithIds = new PlayMatchDTOWithIds(
        0,
        this.addPlayMatchForm.value.fecha,
        this.addPlayMatchForm.value.ubicacion,
        this.leagueId,
        '',
        this.addPlayMatchForm.value.sets,
        this.addPlayMatchForm.value.jugador1,
        this.addPlayMatchForm.value.jugador2,
        this.addPlayMatchForm.value.jugador3,
        this.addPlayMatchForm.value.jugador4,
      );

      console.log('MatchDTO:', matchDTO);

      this.playMatchService.addPlayMatch(matchDTO).subscribe({
        next: (response) => {
          // Manejar la respuesta
          console.log('Match added:', response);
        },
        error: (error) => {
          this.addPlayMatchError = error.message;
          console.error('Match error:', error);
        }
    });
    }
  }

    // Método para cambiar entre 2 y 4 jugadores
    toggleFourPlayers() {
      this.isFourPlayers = !this.isFourPlayers;
      if (!this.isFourPlayers) {
        // Si desactivamos el juego de 4 jugadores, limpiamos jugador3 y jugador4
        this.addPlayMatchForm.get('jugador3')?.reset();
        this.addPlayMatchForm.get('jugador4')?.reset();
      }
    }

  private loadPlayers() {
    this.playerService.getPlayers(this.leagueId).subscribe(data => {
      this.availablePlayers = data;
    });
  }

  


}
