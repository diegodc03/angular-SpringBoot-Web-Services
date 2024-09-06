import { Component, OnInit } from '@angular/core';
import { SaleList } from 'src/app/model/sale-list/sale-list.module';
import { SaleService } from 'src/app/services/sale/sale.service';

@Component({
  selector: 'app-show-sale-information',
  templateUrl: './show-sale-information.component.html',
  styleUrls: ['./show-sale-information.component.css']
})
export class ShowSaleInformationComponent implements OnInit {

  saleInformation: SaleList = new SaleList('', 0, new Date(), []);
  showGarments: boolean[] = [];

  constructor(private saleService: SaleService) { }

  ngOnInit(): void {
    const state = history.state;
    console.log('Estado del historial:', state);

    if (state && state.saleId) {
      // Si el estado contiene información de la venta, utiliza el saleId para obtener los datos actualizados
      const saleId = state.saleId;
      console.log('ID de venta:', saleId);
      this.loadSaleInformation(saleId);
    } else {
      console.log('No se encontró información de venta en el estado');
    }
  }


  // Método para obtener la información actualizada desde el backend
  loadSaleInformation(saleId: String): void {
    this.saleService.getSaleBySaleId(saleId).subscribe({
      next: (returnedSale: SaleList) => {
        this.saleInformation = returnedSale;
        this.showGarments = this.saleInformation.productsSale.map(() => false);  // Reinicializa la visibilidad de los productos
        console.log('Venta cargada:', this.saleInformation);
      },
      error: (error) => {
        console.error('Error al cargar la venta:', error);
      },
      complete: () => {
        console.log('Carga de venta completada');
      }
    });
  }



  deleteProduct(publicId: String): void {
    // Implementa la lógica para eliminar el producto aquí
    console.log('Producto a eliminar:', publicId);
    console.log('Venta:', this.saleInformation.saleId);
  
    // it have to be implemented the logic to delete the product
    this.saleService.deleteProductSale(this.saleInformation.saleId, publicId).subscribe(() => {
    
       // Después de eliminar el producto, carga los datos actualizados
    this.loadSaleInformation(this.saleInformation.saleId);
    }, (error) => {
      console.error('Error al eliminar el producto:', error);
    });


  }

  toggleGarments(index: number, event?: Event): void {  
    if (event) {
      event.stopPropagation();  // Evita la propagación del evento al contenedor
    }
    this.showGarments[index] = !this.showGarments[index];
  }
}
