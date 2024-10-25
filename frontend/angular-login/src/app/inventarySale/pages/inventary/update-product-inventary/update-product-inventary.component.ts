import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { InventaryService } from "../../../service/inventary/inventary.service";
import { ActivatedRoute, Router } from '@angular/router';
import { sizeElement } from '../../../model/garment/sizeElement.module';
import { ProductDTO } from '../../../modelDTO/product-dto/product-dto.module';
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
      sizeElements: this.fb.array([])
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
      sizeElements: this.fb.array([]) // Initialize the FormArray here
    });
  
    this.loadProductData();
    this.onChangesTshirt();
  }

  get sizeElements() {
    return this.updateProductForm.get('sizeElements') as FormArray;
  }

  removeGarment(index: number): void {
    this.sizeElements.removeAt(index);
    this.updateTotalStock();
  }

  updateTotalStock(): void {
    const totalStock = this.sizeElements.controls.reduce((acc, control) => acc + control.value.stock, 0);
    this.updateProductForm.patchValue({ totalStock });
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
          isTshirt: product.haveSize
        });
  
        console.log('Form after patchValue:', this.updateProductForm.value);
  
        if (product.haveSize) {
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
    this.sizeElements.push(this.fb.group({
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
        if (this.sizeElements.length === 0) {
          this.addGarment();
        }
      } else {
        this.updateProductForm.get('totalStock')?.enable();
        this.sizeElements.clear();
        this.updateTotalStock();
      }
    });
  }

  updateTotalStockControlState(): void {
    const isTshirt = this.updateProductForm.get('isTshirt')?.value;
    if (isTshirt) {
      this.updateProductForm.get('totalStock')?.disable();
      if (this.sizeElements.length === 0) {
        this.addGarment();
      }
    } else {
      this.updateProductForm.get('totalStock')?.enable();
      this.sizeElements.clear();
      this.updateTotalStock();
    }
  }


  onSubmit(): void {
    if (this.updateProductForm.valid) {
      const productDTO: ProductDTO = this.updateProductForm.value;
      console.log('Product Data:', productDTO);

      // Enviamos al backend el producto actualizado
      this.inventaryService.updateProduct(this.productId, productDTO).subscribe({
        next: (response: any) => {
          console.log('Product updated successfully:', response);
          // Redirect to the product details page
          this.router.navigate(['/dashboard/inventarySale/inventary']);
        },
        error: (err) => {
          console.error('Error updating product:', err);
          this.updateProductError = 'Failed to update product. Please try again.';
        }
      });
       

    } else {
      this.updateProductError = 'Please fix the errors in the form.';
    }
  }

}




