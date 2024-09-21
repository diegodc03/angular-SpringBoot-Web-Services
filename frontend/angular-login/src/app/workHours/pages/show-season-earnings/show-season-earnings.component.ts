import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SeasonService } from '../../service/seasonService/season-service.service';
import { SeasonDTO } from '../../modelDTO/season-dto/SeasonDTO';
import { EarningsDTO } from '../../modelDTO/EarningsDTO/EarningsDTO';
import { SeasonLoadService } from '../../service/seasonLoadService/season-load.service';


@Component({
  selector: 'app-show-season-earnings',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './show-season-earnings.component.html',
  styleUrl: './show-season-earnings.component.css'
})
export class ShowSeasonEarningsComponent {

  selectedSeasonId: number = -1;
  seasons: SeasonDTO[] = [];
  earnings: EarningsDTO = new EarningsDTO(new SeasonDTO(0, ''), 0, 0, 0);


  constructor(private router: Router, private seasonService: SeasonService, private route: ActivatedRoute, private seasonLoadService:SeasonLoadService ) { }

  ngOnInit(): void {
    
    // Obtener el ID de temporada desde la ruta
    let seasonId = this.seasonLoadService.getSeasonId();

    if(seasonId != null){
      this.selectedSeasonId = seasonId;
      // Llamada a getEarningsBySeasonId después de la asignación
      this.seasonService.getEarningsBySeasonId(this.selectedSeasonId).subscribe( earnings => 
        this.earnings = earnings

      );
    }
  
    // Llamada a getAllSeasons independientemente de la asignación anterior
    this.seasonService.getAllSeasons().subscribe(seasons => {
      this.seasons = seasons;
    });

  }






  getMatches() {
      this.router.navigate(['/work-hours/matchs-working-hours']);
    }

  onSelect($event: Event) {
    const selectedSeasonId = ($event.target as HTMLSelectElement).value;
    console.log('Temporada seleccionada:', selectedSeasonId);
  }


  getEarnings() {
    this.seasonService.getEarningsBySeasonId(this.selectedSeasonId).subscribe(earnings => {
      console.log('Ganancias de la temporada:', earnings);
      this.earnings = earnings;
    });
  }

}
