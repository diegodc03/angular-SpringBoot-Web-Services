import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowLeaguesJoinedComponent } from './show-leagues-joined.component';

describe('ShowLeaguesJoinedComponent', () => {
  let component: ShowLeaguesJoinedComponent;
  let fixture: ComponentFixture<ShowLeaguesJoinedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowLeaguesJoinedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowLeaguesJoinedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
