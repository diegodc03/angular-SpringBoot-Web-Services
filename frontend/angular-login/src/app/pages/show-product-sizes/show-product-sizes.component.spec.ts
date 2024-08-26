import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowProductSizesComponent } from './show-product-sizes.component';

describe('ShowProductSizesComponent', () => {
  let component: ShowProductSizesComponent;
  let fixture: ComponentFixture<ShowProductSizesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowProductSizesComponent]
    });
    fixture = TestBed.createComponent(ShowProductSizesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
