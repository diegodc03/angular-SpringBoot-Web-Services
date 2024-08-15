import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { Product } from '../../model/product/product.module' // Ajusta la ruta según tu estructura de carpetas
import { InventaryService } from "../../services/inventary/inventary.service";
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-product-inventary',
  templateUrl: './update-product-inventary.component.html',
  styleUrls: ['./update-product-inventary.component.css']
})
export class UpdateProductInventaryComponent {
  updateProductForm : FormGroup;
  updateProductError: string = '';
  productId: string = '';

  constructor(private fb: FormBuilder, 
              private route: ActivatedRoute, 
              private router: Router, 
              private inventaryService: InventaryService)
      {
        this.updateProductForm  = this.fb.group({
        name: ['', Validators.required],
        description: ['', Validators.required],
        price: [null, [Validators.required, Validators.min(1)]],
        totalStock: [{ value: '', disabled: false }, Validators.required, Validators.min(0)],
        isTshirt: [false],
        garments: this.fb.array([])
      });

  }

  ngOnInit(): void {
    this.productId = String(this.route.snapshot.paramMap.get('publicId')); // Obtén el ID del producto desde la URL
    this.loadProductData();
    this.onChangesTshirt();
  }

  get garments() {
    return this.updateProductForm.get('garments') as FormArray;
  }


  removeGarment(index: number): void {
    this.garments.removeAt(index);
    this.updateTotalStock();
  }

  updateTotalStock(): void {
    const totalStock = this.garments.controls.reduce((acc, control) => acc + control.value.stock, 0);
    this.updateProductForm.patchValue({ totalStock });
  }

  

  onSubmit(): void {
    if (this.updateProductForm.valid) {
      const product: Product = this.updateProductForm.value;
      console.log('Product Data:', product);

      // Aquí puedes hacer la llamada al servicio para enviar los datos
      // this.productService.addProduct(product).subscribe(response => { ... });
    } else {
      this.updateProductError = 'Please fix the errors in the form.';
    }
  }


  loadProductData(): void {
    this.inventaryService.getProductById(this.productId).subscribe(product => {
      this.updateProductForm.patchValue({
        name: product.name,
        description: product.description,
        price: product.price,
        totalStock: product.totalStock,
        isTshirt: product.isTshirt
      });
      if(product.isTshirt) {
        console.log(product.garments);
        this.setGarments(product.garments);
      }
      this.updateTotalStockControlState();
    });
  }

  setGarments(garments: any[]): void {
    // Lo que hace es limpiar el FormArray de prendas y luego agregar cada prenda del producto
    const garmentsFormArray = this.updateProductForm.get('garments') as FormArray;
    
    console.log(garmentsFormArray);

    garmentsFormArray.clear();
    console.log(garments);

    garments.forEach(garment => {
      garmentsFormArray.push(this.fb.group({
        size: [garment.size, Validators.required],
        color: [garment.color, Validators.required],
        material: [garment.material, Validators.required],
        stock: [garment.stock, [Validators.required, Validators.min(0)]]
      }));
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


  onChangesTshirt(): void {
    this.updateProductForm.get('isTshirt')?.valueChanges.subscribe(value => {
      if (value) {
        // Si `isTshirt` es verdadero, deshabilita el campo `totalStock` y añade al menos una entrada de prenda
        this.updateProductForm.get('totalStock')?.disable();
        if (this.garments.length === 0) {
          this.addGarment(); // Añade una entrada de prenda si no hay ninguna
        }
      } else {
        // Si `isTshirt` es falso, habilita el campo `totalStock` y limpia las prendas
        this.updateProductForm.get('totalStock')?.enable();
        this.garments.clear(); // Limpia las prendas si `isTshirt` es falso
        this.updateTotalStock(); // Actualiza el stock total
      }
    });
  }

  updateTotalStockControlState(): void {
    const isTshirt = this.updateProductForm.get('isTshirt')?.value;
    if (isTshirt) {
      // Si `isTshirt` es verdadero, deshabilita el campo `totalStock` y añade al menos una entrada de prenda
      this.updateProductForm.get('totalStock')?.disable();
      if (this.garments.length === 0) {
        this.addGarment(); // Añade una entrada de prenda si no hay ninguna
      }
    } else {
      // Si `isTshirt` es falso, habilita el campo `totalStock` y limpia las prendas
      this.updateProductForm.get('totalStock')?.enable();
      this.garments.clear(); // Limpia las prendas si `isTshirt` es falso
      this.updateTotalStock(); // Actualiza el stock total
    }
  }


}
