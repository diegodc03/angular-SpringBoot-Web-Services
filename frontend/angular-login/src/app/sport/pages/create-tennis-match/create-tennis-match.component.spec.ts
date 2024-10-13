import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTennisMatchComponent } from './create-tennis-match.component';

describe('CreateTennisMatchComponent', () => {
  let component: CreateTennisMatchComponent;
  let fixture: ComponentFixture<CreateTennisMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateTennisMatchComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateTennisMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
