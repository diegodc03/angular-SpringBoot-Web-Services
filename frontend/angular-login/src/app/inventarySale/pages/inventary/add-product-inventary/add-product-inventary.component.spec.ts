import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProductInventaryComponent } from './add-product-inventary.component';

describe('AddProductInventaryComponent', () => {
  let component: AddProductInventaryComponent;
  let fixture: ComponentFixture<AddProductInventaryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddProductInventaryComponent]
    });
    fixture = TestBed.createComponent(AddProductInventaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
