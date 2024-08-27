import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowStadisticsComponent } from './show-stadistics.component';

describe('ShowStadisticsComponent', () => {
  let component: ShowStadisticsComponent;
  let fixture: ComponentFixture<ShowStadisticsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowStadisticsComponent]
    });
    fixture = TestBed.createComponent(ShowStadisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
