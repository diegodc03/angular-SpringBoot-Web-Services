import { Component } from '@angular/core';
import { SeasonDTO } from '../../modelDTO/season-dto/SeasonDTO';
import { SeasonService } from '../../service/seasonService/season-service.service';
import { CommonModule } from '@angular/common';
import { MatchDTO } from '../../modelDTO/MatchDTO/MatchDTO';
import { MatchWithUserInfoDTO } from '../../modelDTO/MatchWithUserInfo/MatchWithUserInfo';
import { WorkedMatchWithUserInfo } from '../../modelDTO/MatchWithUserInfo/WorkedMatchWithUserInfo';
import { Router } from '@angular/router';


@Component({
  selector: 'app-show-season-matches',
  standalone: true,
  imports: [CommonModule], // Importa CommonModule aquí para usar *ngFor
  templateUrl: './show-season-matches.component.html',
  styleUrl: './show-season-matches.component.css'
})
export class ShowSeasonMatchesComponent {


  selectedSeasonId: number = 0;
  seasons: SeasonDTO[] = [];
  matches: (MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[] = [];
  
  commonModule: CommonModule;
  

  constructor(commonModule: CommonModule, private seasonService: SeasonService, private router: Router) {
    this.commonModule = commonModule;
  }

  ngOnInit(): void {
    this.seasonService.getAllSeasons().subscribe(seasons => {
      this.seasons = seasons;
    });

    this.seasonService.getMatchesBySeasonId(this.selectedSeasonId).subscribe(match => {
      console.log('Temporada 1:', match);
      this.matches = match;
    });
  }


  onSelect($event: Event): void {
    const selectedSeasonId = ($event.target as HTMLSelectElement).value;
    console.log('Temporada seleccionada:', selectedSeasonId);
    this.selectedSeasonId = parseInt(selectedSeasonId);
  }


  isWorkedMatch(match: MatchWithUserInfoDTO | WorkedMatchWithUserInfo): match is WorkedMatchWithUserInfo {
    return 'role' in match && 'payment' in match;
  }

  getEarnings() {
    this.router.navigate(['/work-hours/show-season-earnings)']);
  }

}
