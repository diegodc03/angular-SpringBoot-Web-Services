import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowLeaguesComponent } from './show-leagues.component';

describe('ShowLeaguesComponent', () => {
  let component: ShowLeaguesComponent;
  let fixture: ComponentFixture<ShowLeaguesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowLeaguesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowLeaguesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
