import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { Product } from '../../model/product/product.module' // Ajusta la ruta según tu estructura de carpetas



@Component({
  selector: 'app-add-product-inventary',
  templateUrl: './add-product-inventary.component.html',
  styleUrls: ['./add-product-inventary.component.css']
})
export class AddProductInventaryComponent {
  addProduct: FormGroup;
  addProductError: string = '';

  constructor(private fb: FormBuilder) {
    this.addProduct = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: [null, [Validators.required, Validators.min(0)]],
      totalStock: [{ value: '', disabled: true }],
      isTshirt: [false],
      garments: this.fb.array([])
    });

    this.onChanges();
  }

  ngOnInit(): void {
    this.addGarment(); // Añade una entrada de ropa por defecto
  }

  get garments() {
    return this.addProduct.get('garments') as FormArray;
  }

  onChanges(): void {
    this.addProduct.get('isTshirt')?.valueChanges.subscribe(value => {
      if (value) {
        this.addGarment(); // Muestra las entradas de ropa si `isTshirt` es verdadero
      } else {
        this.garments.clear(); // Limpia las entradas de ropa si `isTshirt` es falso
      }
      this.updateTotalStock();
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
    if (this.addProduct.valid) {
      const product: Product = this.addProduct.value;
      console.log('Product Data:', product);

      // Aquí puedes hacer la llamada al servicio para enviar los datos
      // this.productService.addProduct(product).subscribe(response => { ... });
    } else {
      this.addProductError = 'Please fix the errors in the form.';
    }
  }
}
