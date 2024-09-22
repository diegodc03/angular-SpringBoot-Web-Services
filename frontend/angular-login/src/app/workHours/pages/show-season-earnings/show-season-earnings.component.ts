import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SeasonService } from '../../service/seasonService/season-service.service';
import { SeasonDTO } from '../../modelDTO/season-dto/SeasonDTO';
import { EarningsDTO } from '../../modelDTO/EarningsDTO/EarningsDTO';
import { SeasonLoadService } from '../../service/seasonLoadService/season-load.service';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';



@Component({
  selector: 'app-show-season-earnings',
  templateUrl: './show-season-earnings.component.html',
  styleUrl: './show-season-earnings.component.css'
  
})
export class ShowSeasonEarningsComponent implements OnInit {
  
  onSubmit() {
  throw new Error('Method not implemented.');
  }

  selectedSeasonId: number = -1;
  seasons: SeasonDTO[] = [];
  earnings: EarningsDTO = new EarningsDTO(new SeasonDTO(0, ''), 0, 0, 0);

  addQuantityPay: FormGroup;
  addQuantityPayError: string = '';

  

  constructor(private router: Router, 
              private seasonService: SeasonService, 
              private route: ActivatedRoute, 
              private seasonLoadService:SeasonLoadService, 
              private fb: FormBuilder) { 
      this.addQuantityPay = this.fb.group({
        quantity: [0, [Validators.required, Validators.min(1), Validators.max(this.earnings.moneyToPay)]],
      });
  }

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

    this.addQuantityPay = this.fb.group({
      quantity: [0, [Validators.required, Validators.min(1)]],
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
