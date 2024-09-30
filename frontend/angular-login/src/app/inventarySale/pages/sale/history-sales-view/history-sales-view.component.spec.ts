import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorySalesViewComponent } from './history-sales-view.component';

describe('SalesViewComponent', () => {
  let component: HistorySalesViewComponent;
  let fixture: ComponentFixture<HistorySalesViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HistorySalesViewComponent]
    });
    fixture = TestBed.createComponent(HistorySalesViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
