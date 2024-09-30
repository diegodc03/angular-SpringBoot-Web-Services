import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateProductInventaryComponent } from './update-product-inventary.component';

describe('UpdateProductInventaryComponent', () => {
  let component: UpdateProductInventaryComponent;
  let fixture: ComponentFixture<UpdateProductInventaryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateProductInventaryComponent]
    });
    fixture = TestBed.createComponent(UpdateProductInventaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
