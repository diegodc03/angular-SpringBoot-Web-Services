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
import { AuthService } from 'src/app/authentication/service/authService/auth.service';
import { MatchService } from '../../service/MatchService/match.service';



@Component({
  selector: 'app-show-season-matches',
  standalone: true,
  imports: [CommonModule], // Importa CommonModule aquí para usar *ngFor
  templateUrl: './show-season-matches.component.html',
  styleUrl: './show-season-matches.component.css'
})
export class ShowSeasonMatchesComponent {



  filters: String[] = ['Todos los Partidos', 'Partidos Locales', 'Partidos Visitantes', 'Partidos Trabajados', 'Partidos No Trabajados'];


  roleMatchPaymentRequestListDTO: RoleMatchPaymentRequestDTO[] = [];
  selectedSeasonId: number = -1;
  seasons: SeasonDTO[] = [];
  
  matches: (MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[] = [];
  
  errorMessages: { [matchId: number]: string } = {};

  workingRoles: WorkingRoleDTO[] = [];
  selectedSeasonNameShow: string = '';
selectedFilter: any;
  

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
    
    const seasonId = this.seasonLoadService.getSeasonId();
    if (seasonId != null) {
      this.selectedSeasonId = seasonId;
    }

    return this.seasonService.getAllSeasons().pipe(
      switchMap(seasons => {
        this.seasons = seasons;

        if(this.selectedSeasonId == -1){
          if (this.seasons.length === 0) {
            console.log('No hay temporadas');
            return of([]); // Devuelve un observable vacío si no hay temporadas
          } else if (this.seasons.length === 1) {
            this.selectedSeasonId = this.seasons[0].id;
            this.selectedSeasonNameShow = this.seasons[0].seasonName;
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
                this.selectedSeasonNameShow = season.seasonName;
                console.log(this.selectedSeasonId);
                break; // Termina el ciclo una vez que encuentres la temporada
              }
            }
          }
          
          this.seasonLoadService.setSeasonId(this.selectedSeasonId);
        }else{
          // Establecer el nombre de la temporada seleccionada
          
            const selectedSeason = this.seasons.find(season => season.id === this.selectedSeasonId);
            if (selectedSeason) {
              this.selectedSeasonNameShow = selectedSeason.seasonName;
            }
          
        }

        if (this.selectedSeasonId === -1) {
          console.error('No se pudo seleccionar una temporada válida');
          return of([]); // Devuelve un observable vacío si no se seleccionó una temporada válida
        }
  
        console.log('Temporada seleccionada:', this.selectedSeasonId);
     
        // Aquí es donde se hace la segunda petición
        return this.matchService.getMatchesBySeasonId(this.selectedSeasonId);
      })
    );
  }

  loadMatches(): Observable<(MatchWithUserInfoDTO | WorkedMatchWithUserInfo)[]> {
    
    return this.matchService.getMatchesBySeasonId(this.selectedSeasonId).pipe(
      switchMap(matches => {
        this.matches = matches;

        if(this.matches.length == 0){
          console.log('No hay partidos');
          this.deleteSeason(this.selectedSeasonId);
          return of([]); // Devuelve un observable vacío si no hay partidos
        }
        console.log('Matches loaded:', this.matches);
        return of(matches);
      })
    );
  }



  onSelect($event: Event): void {
    const selectedSeasonId = ($event.target as HTMLSelectElement).value;
    console.log('Temporada seleccionada:', selectedSeasonId);
    this.selectedSeasonId = parseInt(selectedSeasonId);

    this.seasonLoadService.setSeasonId(this.selectedSeasonId);
    
    this.loadMatches().subscribe(matches => {
      this.matches = matches
      console.log('Matches loaded:', this.matches);
    });
    
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


  onSelectFilter($event: Event) {
    const selectedFilter = ($event.target as HTMLSelectElement).value;
    console.log('Filtro seleccionado:', selectedFilter);
    this.selectedFilter = selectedFilter;

    switch (selectedFilter) {
      case 'Partidos Locales':
        this.matchService.getLocalMatchesWithUserInfo(this.selectedSeasonId).subscribe(matches => {
          this.matches = matches;
          console.log('Matches loaded:', this.matches);
        });
        break;
      case 'Partidos Visitantes':
        this.matchService.getAwayMatchesWithUserInfo(this.selectedSeasonId).subscribe(matches => {
          this.matches = matches;
          console.log('Matches loaded:', this.matches);
        });
        break;
      case 'Partidos Trabajados':
        this.matchService.getOnlyWorkedMatches(this.selectedSeasonId).subscribe(matches => {
          this.matches = matches;
          console.log('Matches loaded:', this.matches);
        });
        break;
      case 'Partidos No trabajados':
        this.matchService.getOnlyNotWorkedMatches(this.selectedSeasonId).subscribe(matches => {
          this.matches = matches;
          console.log('Matches loaded:', this.matches);
        });
        break;
      default:
        this.loadMatches().subscribe(matches => {
          this.matches = matches;
          console.log('Matches loaded:', this.matches);
        });
        break;
    }
  }


  addWork() {
    console.log('Trabajo añadido:', this.roleMatchPaymentRequestListDTO);
    this.earningsService.addWork(this.roleMatchPaymentRequestListDTO).pipe(
      switchMap(state => {
        console.log('Trabajo añadido:', state);
        this.roleMatchPaymentRequestListDTO = [];
        return this.loadMatches(); // Return the observable from loadData
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
        
        return this.loadMatches(); // Return the observable from loadData
      
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
    

    const match = this.matches.find(match => match.match.id === matchId) as MatchWithUserInfoDTO | WorkedMatchWithUserInfo;
    
    if(match.userWorked){
      
        this.seasonService.deleteWork(matchId).pipe(
          switchMap(state => {
            console.log('Trabajo eliminado:', state);
            
            // Luego de eliminar el trabajo, eliminamos el match
            return this.matchService.deleteMatch(matchId);
          }),
          switchMap(response => {
            console.log('Match eliminado:', response);
            
            // Luego de eliminar el match, cargamos los matches nuevamente
            return this.loadMatches();
          })
        ).subscribe(matches => {
          this.matches = matches;
          console.log('Matches cargados:', this.matches);
        });
    }else{
      this.matchService.deleteMatch(matchId).subscribe({
        next: () => {
          console.log('Match deleted');
          
          return this.loadMatches().subscribe(
            matches => {
              this.matches = matches;
              console.log('Matches loaded:', this.matches);
            }
          ); // Return the observable from loadData
        },
        error: (error) => {
          console.error('Error deleting match:', error);
        }
      });
    }
  }

  deleteSeason(seasonId: number) {
    this.seasonService.deleteSeason(seasonId);
    this.selectedSeasonId = -1;
    this.selectedSeasonNameShow = '';
    
    const seasonIndex = this.seasons.findIndex(season => season.id === seasonId);
    if (seasonIndex > -1) {
      this.seasons.splice(seasonIndex, 1);
    }
  }
    
  

}
