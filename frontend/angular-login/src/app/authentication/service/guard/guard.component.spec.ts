import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthGuard } from './guard.component';

describe('GuardComponent', () => {
  let component: AuthGuard;
  let fixture: ComponentFixture<AuthGuard>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AuthGuard]
    });
    fixture = TestBed.createComponent(AuthGuard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});