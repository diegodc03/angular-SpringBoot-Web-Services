import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowProductInventaryComponent } from './show-product-inventary.component';

describe('ShowProductInventaryComponent', () => {
  let component: ShowProductInventaryComponent;
  let fixture: ComponentFixture<ShowProductInventaryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowProductInventaryComponent]
    });
    fixture = TestBed.createComponent(ShowProductInventaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
