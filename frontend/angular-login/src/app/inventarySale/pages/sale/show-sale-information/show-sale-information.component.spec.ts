import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowSaleInformationComponent } from './show-sale-information.component';

describe('ShowSaleInformationComponent', () => {
  let component: ShowSaleInformationComponent;
  let fixture: ComponentFixture<ShowSaleInformationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowSaleInformationComponent]
    });
    fixture = TestBed.createComponent(ShowSaleInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
