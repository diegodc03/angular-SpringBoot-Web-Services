import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowLeagueInformationComponent } from './show-clasification.component';

describe('ShowLeagueInformationComponent', () => {
  let component: ShowLeagueInformationComponent;
  let fixture: ComponentFixture<ShowLeagueInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowLeagueInformationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowLeagueInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
