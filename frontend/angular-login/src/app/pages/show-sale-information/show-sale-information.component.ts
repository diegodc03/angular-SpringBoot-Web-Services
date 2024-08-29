import { Component } from '@angular/core';
import { SaleList } from 'src/app/model/sale-list/sale-list.module';

@Component({
  selector: 'app-show-sale-information',
  templateUrl: './show-sale-information.component.html',
  styleUrls: ['./show-sale-information.component.css']
})
export class ShowSaleInformationComponent {
  
  saleInformation: SaleList = new SaleList('', 0, new Date(), []);
  showGarments: boolean[] = this.saleInformation.productsSale.map(() => false);

  

  constructor() { }

  ngOnInit(): void {
    const state = history.state;
    console.log(state);
    if (state && state.sale ) {
      console.log('Productos encontrados en el estado');
      console.log(state.sale);
      this.saleInformation = state.sale;
      console.log('Productos encontrados en saleInformation');
      console.log(this.saleInformation.productsSale);
    } else {
      console.log('No se encontraron productos en el estado');
    }
  }



  deleteProduct(productId: string): void {
    // Implementa la lógica para eliminar el producto aquí
  }

  toggleGarments(index: number, event?: Event): void {  
    if (event) {
        event.stopPropagation(); // Evitar que el clic en el botón propague el evento al contenedor
    }
    this.showGarments[index] = !this.showGarments[index];
  }

}
