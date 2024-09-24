import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SeasonDTO } from '../../modelDTO/season-dto/SeasonDTO';
import { SeasonService } from '../../service/seasonService/season-service.service';
import { MatchService } from '../../service/MatchService/match.service';
import { AddMatchDTO } from '../../modelDTO/addMatchDTO/addMatchDTO';
import { MatchDTO } from '../../modelDTO/MatchDTO/MatchDTO';

@Component({
  selector: 'app-add-matches',
  templateUrl: './add-matches.component.html',
  styleUrl: './add-matches.component.css'
})
export class AddMatchesComponent {

  seasonId: number = -1;

  seasons: SeasonDTO[] = [];

  matchError: string = "";

    
  matchForm = this.fb.group({
    localTeam: ['', Validators.required],
    awayTeam: ['', Validators.required],
    date: ['', Validators.required],
    seasonId: ['', Validators.required],
  });

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private seasonService: SeasonService,
    private matchService: MatchService
  ) {
    
    this.matchForm = this.fb.group({
      localTeam: ['', Validators.required],
      awayTeam: ['', Validators.required],
      date: ['', Validators.required],
      seasonId: ['', Validators.required],
    });
  }


  ngOnInit(): void {

    this.seasonService.getAllSeasons().subscribe(seasons => {
      this.seasons = seasons;
    });


  }

  get localTeam() {
    return this.matchForm.controls.localTeam;
  }

  get awayTeam() {
    return this.matchForm.controls.awayTeam;
  }

  get date() {
    return this.matchForm.controls.date;
  }


  onSubmit() {
    console.log('Form submitted:', this.matchForm.value);

    if (this.matchForm.valid) {
        this.matchError = "";

        const { date, localTeam, awayTeam, seasonId } = this.matchForm.value;

        // Convierte la fecha de string a Date
        const matchDTO = new AddMatchDTO(
            new Date(date as string),
            localTeam!,
            awayTeam!,
            Number(seasonId!)
        );

        this.matchService.addMatch(matchDTO).subscribe({
            next: (response) => {
                console.log('Match added:', response);
                this.router.navigateByUrl('/work-hours/matchs-working-hours');
            },
            error: (error) => {
                console.error('Match error:', error);
                this.matchError = 'Error during match creation. Please try again.';
            }
        });
    } else {
        console.error('El formulario no es válido');
    }    
}

  

  onSelectMatch($event: Event) {
    if(this.matchForm.valid){
      if(this.matchForm.value.seasonId){
        this.seasonId = Number(this.matchForm.value.seasonId);
        console.log('Temporada seleccionada:', this.seasonId);
      }
      
    }
      
  }

}
