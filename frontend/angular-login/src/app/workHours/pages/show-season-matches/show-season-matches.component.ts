import { Component } from '@angular/core';
import { SeasonDTO } from '../../modelDTO/season-dto/SeasonDTO';
import { SeasonService } from '../../service/seasonService/season-service.service';
import { CommonModule } from '@angular/common';
import { MatchDTO } from '../../modelDTO/MatchDTO/MatchDTO';
import { MatchWithUserInfoDTO } from '../../modelDTO/MatchWithUserInfo/MatchWithUserInfo';
import { WorkedMatchWithUserInfo } from '../../modelDTO/MatchWithUserInfo/WorkedMatchWithUserInfo';
import { Router } from '@angular/router';
import { Observable, of, switchMap } from 'rxjs';
import { SeasonLoadService } from '../../service/seasonLoadService/season-load.service';
import { WorkingRoleDTO } from '../../modelDTO/WorkingRoleDTO/WorkingRoleDTO';
import { WorkingRolesService } from '../../service/workingRolesService/working-roles.service';

@Component({
  selector: 'app-show-season-matches',
  standalone: true,
  imports: [CommonModule], // Importa CommonModule aquí para usar *ngFor
  templateUrl: './show-season-matches.component.html',
  styleUrl: './show-season-matches.component.css'
})
export class ShowSeasonMatchesComponent {
addWork(arg0: number) {
throw new Error('Method not implemented.');
}

  selectedSeasonId: number = -1;
  seasons: SeasonDTO[] = [];
  matches: (MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[] = [];
  
  commonModule: CommonModule;

  workingRoles: WorkingRoleDTO[] = [];
  

  constructor(commonModule: CommonModule, private seasonService: SeasonService, private router: Router, private seasonLoadService: SeasonLoadService , private workingRolesService: WorkingRolesService) {
    this.commonModule = commonModule;
  }

  //año 2024-2025

  ngOnInit(): void {
    this.loadData().subscribe(matches => {
      this.matches = matches;
      console.log('Matches loaded:', this.matches);
    });

    this.workingRolesService.getWorkingRoles().subscribe(workingRoles => {
      console.log('Roles de trabajo:', this.workingRoles);
      this.workingRoles = workingRoles;
    });

  }


  loadData(): Observable<(MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[]> {
    return this.seasonService.getAllSeasons().pipe(
      switchMap(seasons => {
        this.seasons = seasons;

        if (this.seasons.length === 0) {
          console.log('No hay temporadas');
          return of([]); // Devuelve un observable vacío si no hay temporadas
        } else if (this.seasons.length === 1) {
          this.selectedSeasonId = this.seasons[0].id;

        } else {
          const actualYear = new Date().getFullYear();
          let actualSeason = '';
  
          if (new Date().getMonth() > 7) {
            actualSeason = `${actualYear}-${actualYear + 1}`;
          } else {
            actualSeason = `${actualYear - 1}-${actualYear}`;
          }
  
          for (const season of this.seasons) {
            if (season.seasonName === actualSeason) {
              this.selectedSeasonId = season.id;
              console.log(this.selectedSeasonId);
              break; // Termina el ciclo una vez que encuentres la temporada
            }
          }
        }
        
        if (this.selectedSeasonId === -1) {
          console.error('No se pudo seleccionar una temporada válida');
          return of([]); // Devuelve un observable vacío si no se seleccionó una temporada válida
        }
  
        console.log('Temporada seleccionada:', this.selectedSeasonId);
        this.seasonLoadService.setSeasonId(this.selectedSeasonId);
  
        // Aquí es donde se hace la segunda petición
        return this.seasonService.getMatchesBySeasonId(this.selectedSeasonId);
      })
    );
  }


  onSelect($event: Event): void {
    const selectedSeasonId = ($event.target as HTMLSelectElement).value;
    console.log('Temporada seleccionada:', selectedSeasonId);
    this.selectedSeasonId = parseInt(selectedSeasonId);
    this.seasonLoadService.setSeasonId(this.selectedSeasonId);
    
  }


  isWorkedMatch(match: MatchWithUserInfoDTO | WorkedMatchWithUserInfo): match is WorkedMatchWithUserInfo {
    return 'role' in match && 'payment' in match;
  }


  deleteWork(arg0: number) {
    throw new Error('Method not implemented.');
  }


  onSelectedMatch(matchId: number): void {
    //this.router.navigate(['/workHours/match', matchId]);
    throw new Error('Method not implemented.');
  }

  
  

}
