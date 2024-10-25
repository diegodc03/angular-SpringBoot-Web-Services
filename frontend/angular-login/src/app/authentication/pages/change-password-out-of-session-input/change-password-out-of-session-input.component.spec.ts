import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangePasswordOutOfSessionInputComponent } from './change-password-out-of-session-input.component';

describe('ChangePasswordOutOfSessionInputComponent', () => {
  let component: ChangePasswordOutOfSessionInputComponent;
  let fixture: ComponentFixture<ChangePasswordOutOfSessionInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChangePasswordOutOfSessionInputComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChangePasswordOutOfSessionInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
