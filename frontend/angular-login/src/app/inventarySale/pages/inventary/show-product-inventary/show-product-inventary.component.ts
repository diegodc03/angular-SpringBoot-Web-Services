import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { Product } from '../../../model/product/product.module' // Ajusta la ruta según tu estructura de carpetas
import { InventaryService } from "../../../service/inventary/inventary.service";
import { ActivatedRoute, Router } from '@angular/router';
import { Garment } from '../../../model/garment/garment.module'; // Ajusta la ruta según tu estructura de carpetas

@Component({
  selector: 'app-show-product-inventary',
  templateUrl: './show-product-inventary.component.html',
  styleUrls: ['./show-product-inventary.component.css']
})
export class ShowProductInventaryComponent {

  
  updateProductError: string = '';
  productId: string = '';
  showProductForm: FormGroup;



  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private inventaryService: InventaryService
  ) {
    this.showProductForm = this.fb.group({
      name: [''],
      description: [''],
      price: [0],
      totalStock: [0],
      isTshirt: [false],
      garments: this.fb.array([])
    });
  }

  ngOnInit(): void {
    
    this.productId = String(this.route.snapshot.paramMap.get('publicId'));
    
    this.showProductForm = this.fb.group({
      name: [''],
      description: [''],
      price: [0],
      totalStock: [0],
      isTshirt: [false],
      garments: this.fb.array([]) // Initialize the FormArray here
    });
  
    this.loadProductData();
    
  }

  get garments() {
    return this.showProductForm.get('garments') as FormArray;
  }


  loadProductData(): void {
    this.inventaryService.getProductById(this.productId).subscribe({
      next: (product) => {
        console.log('Product data:', product);
        console.log('Form before patchValue:', this.showProductForm.value);
  
        this.showProductForm.patchValue({
          name: product.name,
          description: product.description,
          price: product.price,
          totalStock: product.totalStock,
          isTshirt: product.isTshirt
        });
  
        console.log('Form after patchValue:', this.showProductForm.value);
  
        if (product.isTshirt) {
          console.log("Producto es una camiseta");
          console.log(product.garments);
          this.setGarments(product.garments);
        }
       
      },
      error: (err) => {
        console.error('Error loading product data:', err);
      }
    });
  }
  
  setGarments(garmentsList: any[]): void {
    const garmentsFormArray = this.showProductForm.get('garments') as FormArray;
  
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
  

  
  

}
