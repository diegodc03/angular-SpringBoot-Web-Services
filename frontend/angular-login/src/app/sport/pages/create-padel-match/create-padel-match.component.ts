import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LeagueService } from '../../service/leagueService/league.service';
import { PlayMatchService } from '../../service/playMatchService/play-match.service';
import { PlayerService } from '../../service/playerService/player.service';
import {  PlayerDTOModule } from '../../modelDTO/player-dto/player-dto.module';
import { LeagueIdService } from '../../service/league-id.service';
import { PlayMatchDTOWithIds } from '../../modelDTO/match-dto/match-dto.module';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-padel-match',
  templateUrl: './create-padel-match.component.html',
  styleUrl: './create-padel-match.component.css'
})
export class CreatePadelMatchComponent {

  leagueId: number = this.leagueIdService.getLeagueId();
  availablePlayers: PlayerDTOModule[] = []; // Cargar los jugadores desde el servicio
  addPlayMatchError = '';
  addPlayMatchForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private playerService: PlayerService,
    private leagueService: LeagueService,
    private playMatchService: PlayMatchService,
    private leagueIdService: LeagueIdService,
    private router: Router
  ) {

    this.addPlayMatchForm = this.fb.group({
      fecha: ['', Validators.required],
      ubicacion: ['', Validators.required],
      jugador1: ['', Validators.required],
      jugador2: ['', Validators.required],
      jugador3: ['', Validators.required],
      jugador4: ['', Validators.required],
      sets: this.fb.array([Validators.required]),
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

        // Verificar si hay al menos un set agregado en el FormArray
        if (this.sets.length <= 0) {
          console.log('No se puede crear un partido sin sets');
          alert('No se puede crear un partido sin sets');
          return;
        }

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

      this.playMatchService.addPlayMatch(matchDTO).subscribe({
        next: (response) => {
          alert(response);
          this.router.navigate(['sports/league-information', this.leagueId]);
            
        },
        error: (error) => {
          this.addPlayMatchError = error.message;
          alert('Error al crear el partido');
        }
      });
    }
  }



  private loadPlayers() {
    this.playerService.getPlayers(this.leagueId).subscribe(data => {
      this.availablePlayers = data;
    });
  }

}
