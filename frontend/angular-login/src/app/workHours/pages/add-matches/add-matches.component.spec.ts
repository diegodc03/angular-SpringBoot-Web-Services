import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMatchesComponent } from './add-matches.component';

describe('AddMatchesComponent', () => {
  let component: AddMatchesComponent;
  let fixture: ComponentFixture<AddMatchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddMatchesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
