import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowProductSizes } from './show-product-sizes.component';

describe('ShowProductSizesComponent', () => {
  let component: ShowProductSizes;
  let fixture: ComponentFixture<ShowProductSizes>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowProductSizes]
    });
    fixture = TestBed.createComponent(ShowProductSizes);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
