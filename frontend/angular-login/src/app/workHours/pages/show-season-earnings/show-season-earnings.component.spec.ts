import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowSeasonEarningsComponent } from './show-season-earnings.component';

describe('ShowSeasonEarningsComponent', () => {
  let component: ShowSeasonEarningsComponent;
  let fixture: ComponentFixture<ShowSeasonEarningsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowSeasonEarningsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowSeasonEarningsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
