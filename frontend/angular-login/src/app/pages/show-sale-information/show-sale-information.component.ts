import { Component, OnInit } from '@angular/core';
import { SaleList } from 'src/app/model/sale-list/sale-list.module';

@Component({
  selector: 'app-show-sale-information',
  templateUrl: './show-sale-information.component.html',
  styleUrls: ['./show-sale-information.component.css']
})
export class ShowSaleInformationComponent implements OnInit {

  saleInformation: SaleList = new SaleList('', 0, new Date(), []);
  showGarments: boolean[] = [];

  constructor() { }

  ngOnInit(): void {
    const state = history.state ;  // Obtiene el estado de la navegación
    console.log('Estado del historial:', state);
  
    if (state && state.sale ) {  // Verifica que el estado contenga la propiedad 'sale'
      console.log('Información de la venta encontrada:', state.sale);
      this.saleInformation = state.sale;  // Asigna la información de la venta recibida
      this.showGarments = this.saleInformation.productsSale.map(() => false);  // Inicializa el array para manejar la visibilidad de las prendas
      console.log('Productos en saleInformation:', this.saleInformation.productsSale);
    } else {
      console.log('No se encontró información de venta en el estado');
    }
  }

  deleteProduct(productId: string): void {
    // Implementa la lógica para eliminar el producto aquí
    console.log('Producto a eliminar:', productId);
  }

  toggleGarments(index: number, event?: Event): void {  
    if (event) {
      event.stopPropagation();  // Evita la propagación del evento al contenedor
    }
    this.showGarments[index] = !this.showGarments[index];
  }
}
