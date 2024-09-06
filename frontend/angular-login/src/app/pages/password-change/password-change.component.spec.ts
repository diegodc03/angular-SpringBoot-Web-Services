import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangePasswordComponent } from './password-change.component';

describe('PasswordChangeComponent', () => {
  let component: ChangePasswordComponent;
  let fixture: ComponentFixture<ChangePasswordComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChangePasswordComponent]
    });
    fixture = TestBed.createComponent(ChangePasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
