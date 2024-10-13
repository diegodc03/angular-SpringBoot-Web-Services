import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePadelMatchComponent } from './create-padel-match.component';

describe('CreateMatchComponent', () => {
  let component: CreatePadelMatchComponent;
  let fixture: ComponentFixture<CreatePadelMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreatePadelMatchComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatePadelMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
