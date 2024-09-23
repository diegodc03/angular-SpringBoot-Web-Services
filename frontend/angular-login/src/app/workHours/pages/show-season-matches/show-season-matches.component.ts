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
import { RoleMatchPaymentRequestDTO} from '../../modelDTO/RoleMatchPaymentRequestDTO/RoleMatchPaymentRequestDTO';
import { EarningsService } from '../../service/earningsService/earnings.service';
import { state } from '@angular/animations';
import { AuthService } from 'src/app/auth/service/authService/auth.service';
import { MatchService } from '../../service/MatchService/match.service';



@Component({
  selector: 'app-show-season-matches',
  standalone: true,
  imports: [CommonModule], // Importa CommonModule aquí para usar *ngFor
  templateUrl: './show-season-matches.component.html',
  styleUrl: './show-season-matches.component.css'
})
export class ShowSeasonMatchesComponent {


  roleMatchPaymentRequestListDTO: RoleMatchPaymentRequestDTO[] = [];
  selectedSeasonId: number = -1;
  seasons: SeasonDTO[] = [];
  
  matches: (MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[] = [];
  
  errorMessages: { [matchId: number]: string } = {};

  workingRoles: WorkingRoleDTO[] = [];
  

  constructor(private seasonService: SeasonService,
              private earningsService: EarningsService, 
              private router: Router, 
              private seasonLoadService: SeasonLoadService , 
              private workingRolesService: WorkingRolesService,
              public authService: AuthService,
              private matchService: MatchService) {
    
  }

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
        return this.matchService.getMatchesBySeasonId(this.selectedSeasonId);
      })
    );
  }


  onSelect($event: Event): void {
    const selectedSeasonId = ($event.target as HTMLSelectElement).value;
    console.log('Temporada seleccionada:', selectedSeasonId);
    this.selectedSeasonId = parseInt(selectedSeasonId);
    this.seasonLoadService.setSeasonId(this.selectedSeasonId);
    
  }


  onSelectRole($event: Event, match: MatchDTO): void {
    const roleSelected = ($event.target as HTMLSelectElement).value;
    console.log('Rol Seleccionado:', roleSelected);
    

    let index = this.roleMatchPaymentRequestListDTO.findIndex(roleMatchPay => roleMatchPay.matchId === match.id )
    if (index > -1) {
      this.roleMatchPaymentRequestListDTO.splice(index, 1);
    }

    if (new Date(match.date) < new Date()) {
      const roleMatchPaymentRequestDTO = new RoleMatchPaymentRequestDTO(roleSelected, match.id);
      this.roleMatchPaymentRequestListDTO.push(roleMatchPaymentRequestDTO);
    }else{
      console.error('No se puede añadir un trabajo a un partido que no ha ocurrido todavía');
      
      this.errorMessages[match.id] = 'No se puede añadir un trabajo a un partido que no ha ocurrido todavía';
      console.error(this.errorMessages[match.id]);
    }
  }


  addWork() {
    console.log('Trabajo añadido:', this.roleMatchPaymentRequestListDTO);
    this.earningsService.addWork(this.roleMatchPaymentRequestListDTO).pipe(
      switchMap(state => {
        console.log('Trabajo añadido:', state);
        this.roleMatchPaymentRequestListDTO = [];
        return this.loadData(); // Return the observable from loadData
      })
    ).subscribe(matches => {
      this.matches = matches;
      console.log('Matches loaded:', this.matches);
    });
  }


  isRoleMatchAnyRequest(): boolean {
    return this.roleMatchPaymentRequestListDTO.length > 0;
  }


  isWorkedMatch(match: MatchWithUserInfoDTO | WorkedMatchWithUserInfo): match is WorkedMatchWithUserInfo {
    return 'role' in match && 'payment' in match;
  }
  

  deleteWork(matchId : number){ 
    
    this.seasonService.deleteWork(matchId).pipe(
      switchMap(state => {
        console.log('Trabajo eliminado:', state);
        
        return this.loadData(); // Return the observable from loadData
      
      })).subscribe(matches => {
        this.matches = matches;
        console.log('Matches loaded:', this.matches);
      });


  }


  addMatch() {
    this.router.navigate(['work-hours/add-matches']);
  }
  
  addSeason() {
    this.router.navigate(['work-hours/add-season']);
  }

  deleteMatch(matchId: number) {
    
    this.matchService.deleteMatch(matchId).subscribe({
      next: () => {
        console.log('Match deleted');
        return this.loadData();
      },
      error: (error) => {
        console.error('Error deleting match:', error);
      }
    });
  }
    
  
}
