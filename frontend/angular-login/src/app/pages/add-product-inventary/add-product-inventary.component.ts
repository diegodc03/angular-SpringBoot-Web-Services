import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';

import { InventaryService } from "../../services/inventary/inventary.service";
import { ProductDTO } from '../../model/product-dto/product-dto.module';


@Component({
  selector: 'app-add-product-inventary',
  templateUrl: './add-product-inventary.component.html',
  styleUrls: ['./add-product-inventary.component.css']
})
export class AddProductInventaryComponent {
  addProduct: FormGroup;
  addProductError: string = '';
  selectedFile: File | undefined;

  constructor(private fb: FormBuilder,
              private inventaryService: InventaryService
  ) 
  {
    this.addProduct = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: [null, [Validators.required, Validators.min(1)]],
      image: [''],
      totalStock: [{ value: '', disabled: false }, Validators.required, Validators.min(0)],
      isTshirt: [false],
      garments: this.fb.array([])
    });

  }

  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];
    }
  }


  ngOnInit(): void {
    this.onChanges();
    // Si `isTshirt` es `true`, añade una entrada de prenda por defecto
    if (this.addProduct.get('isTshirt')?.value) {
      this.addGarment();
      this.addProduct.get('totalStock')?.disable();
    }
  }

  get garments() {
    return this.addProduct.get('garments') as FormArray;
  }

  onChanges(): void {
    this.addProduct.get('isTshirt')?.valueChanges.subscribe(value => {
      if (value) {
        // Si `isTshirt` es verdadero, deshabilita el campo `totalStock` y añade al menos una entrada de prenda
        this.addProduct.get('totalStock')?.disable();
        if (this.garments.length === 0) {
          this.addGarment(); // Añade una entrada de prenda si no hay ninguna
        }
      } else {
        // Si `isTshirt` es falso, habilita el campo `totalStock` y limpia las prendas
        this.addProduct.get('totalStock')?.enable();
        this.garments.clear(); // Limpia las prendas si `isTshirt` es falso
        this.updateTotalStock(); // Actualiza el stock total
      }
    });
  }


  addGarment(): void {
    this.garments.push(this.fb.group({
      size: ['', Validators.required],
      color: ['', Validators.required],
      material: ['', Validators.required],
      stock: [null, [Validators.required, Validators.min(0)]]
    }));
  }

  removeGarment(index: number): void {
    this.garments.removeAt(index);
    this.updateTotalStock();
  }

  updateTotalStock(): void {
    const totalStock = this.garments.controls.reduce((acc, control) => acc + control.value.stock, 0);
    this.addProduct.patchValue({ totalStock });
  }

  onSubmit(): void {
    console.log('Formulario:', this.addProduct)
    if (this.addProduct.value) {
      
      const productForm: ProductDTO = this.addProduct.value;
      console.log('Product Data:', productForm);
      
      const productDTO: ProductDTO = {
        name: this.addProduct.get('name')?.value,
        description: this.addProduct.get('description')?.value,
        price: this.addProduct.get('price')?.value,
        isTshirt: this.addProduct.get('isTshirt')?.value,
        totalStock: this.addProduct.get('totalStock')?.value,
        garments: this.addProduct.get('garments')?.value
      };

      const formData = new FormData();
      if(this.selectedFile){
        formData.append("file", this.selectedFile);
      }

      

      this.inventaryService.addProduct(productDTO, formData).subscribe({
        next: response => {
          console.log('Producto creado con éxito', response);
          // Resetear el formulario o navegar a otra vista
          this.addProduct.reset();
        },
        error: error => {
          console.error('Error al crear el producto', error);
          this.addProductError = 'Error al crear el producto.';
        }
      });

      // Aquí puedes hacer la llamada al servicio para enviar los datos
      // this.productService.addProduct(product).subscribe(response => { ... });
    } else {
      this.addProductError = 'Please fix the errors in the form.';
    }
  }
}
