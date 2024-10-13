import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LeagueService } from '../../service/leagueService/league.service';
import { PlayMatchService } from '../../service/playMatchService/play-match.service';
import { PlayerService } from '../../service/playerService/player.service';
import {  PlayerDTOModule } from '../../modelDTO/player-dto/player-dto.module';

@Component({
  selector: 'app-create-padel-match',
  templateUrl: './create-padel-match.component.html',
  styleUrl: './create-padel-match.component.css'
})
export class CreatePadelMatchComponent {

  
  leagues = []; // Cargar las ligas desde el servicio
  availablePlayers: PlayerDTOModule[] = []; // Cargar los jugadores desde el servicio
  addPlayMatchError = '';
  addPlayMatchForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private playerService: PlayerService,
    private leagueService: LeagueService,
    private playMatchService: PlayMatchService
  ) {

    this.addPlayMatchForm = this.fb.group({
      fecha: ['', Validators.required],
      ubicacion: ['', Validators.required],
      jugador1: ['', Validators.required],
      jugador2: ['', Validators.required],
      jugador3: ['', Validators.required],
      jugador4: ['', Validators.required],
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
      this.playMatchService.addPlayMatch(this.addPlayMatchForm.value).subscribe({
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



  private loadPlayers() {
    this.playerService.getPlayers(4).subscribe(data => {
      this.availablePlayers = data;
    });
  }
}
