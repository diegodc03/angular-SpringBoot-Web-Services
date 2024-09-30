import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowSeasonMatchesComponent } from './show-season-matches.component';

describe('ShowSeasonMatchesComponent', () => {
  let component: ShowSeasonMatchesComponent;
  let fixture: ComponentFixture<ShowSeasonMatchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowSeasonMatchesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowSeasonMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
