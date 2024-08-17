import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { Product } from '../../model/product/product.module' // Ajusta la ruta según tu estructura de carpetas
import { InventaryService } from "../../services/inventary/inventary.service";
import { ActivatedRoute, Router } from '@angular/router';
import { Garment } from 'src/app/model/garment/garment.module';
@Component({
  selector: 'app-update-product-inventary',
  templateUrl: './update-product-inventary.component.html',
  styleUrls: ['./update-product-inventary.component.css']
})
export class UpdateProductInventaryComponent implements OnInit {
  updateProductForm: FormGroup;
  updateProductError: string = '';
  productId: string = '';


  

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private inventaryService: InventaryService
  ) {
    this.updateProductForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: [null, [Validators.required, Validators.min(1)]],
      totalStock: [{ value: '', disabled: false }, Validators.required, Validators.min(0)],
      isTshirt: [false],
      garments: this.fb.array([])
    });
  }

  ngOnInit(): void {
    
    this.productId = String(this.route.snapshot.paramMap.get('publicId'));
    
    this.updateProductForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(0)]],
      totalStock: [{ value: 0, disabled: true }, Validators.required],
      isTshirt: [false],
      garments: this.fb.array([]) // Initialize the FormArray here
    });
  
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
    } else {
      this.updateProductError = 'Please fix the errors in the form.';
    }
  }

  loadProductData(): void {
    this.inventaryService.getProductById(this.productId).subscribe({
      next: (product) => {
        console.log('Product data:', product);
        console.log('Form before patchValue:', this.updateProductForm.value);
  
        this.updateProductForm.patchValue({
          name: product.name,
          description: product.description,
          price: product.price,
          totalStock: product.totalStock,
          isTshirt: product.isTshirt
        });
  
        console.log('Form after patchValue:', this.updateProductForm.value);
  
        if (product.isTshirt) {
          console.log("Producto es una camiseta");
          console.log(product.garments);
          this.setGarments(product.garments);
        }
        this.updateTotalStockControlState();
      },
      error: (err) => {
        console.error('Error loading product data:', err);
      }
    });
  }
  
  setGarments(garmentsList: any[]): void {
    const garmentsFormArray = this.updateProductForm.get('garments') as FormArray;
  
    // Clear existing controls (if any)
    garmentsFormArray.clear();
  
    garmentsList.forEach(garment => {
      const garmentGroup = this.fb.group({
        size: [garment.size, Validators.required],
        color: [garment.color, Validators.required],
        material: [garment.material, Validators.required],
        stock: [garment.stock, [Validators.required, Validators.min(0)]]
      });
  
      garmentsFormArray.push(garmentGroup);
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
        this.updateProductForm.get('totalStock')?.disable();
        if (this.garments.length === 0) {
          this.addGarment();
        }
      } else {
        this.updateProductForm.get('totalStock')?.enable();
        this.garments.clear();
        this.updateTotalStock();
      }
    });
  }

  updateTotalStockControlState(): void {
    const isTshirt = this.updateProductForm.get('isTshirt')?.value;
    if (isTshirt) {
      this.updateProductForm.get('totalStock')?.disable();
      if (this.garments.length === 0) {
        this.addGarment();
      }
    } else {
      this.updateProductForm.get('totalStock')?.enable();
      this.garments.clear();
      this.updateTotalStock();
    }
  }
}
