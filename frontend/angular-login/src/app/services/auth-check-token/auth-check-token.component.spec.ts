import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthCheckTokenComponent } from './auth-check-token.component';

describe('AuthCheckTokenComponent', () => {
  let component: AuthCheckTokenComponent;
  let fixture: ComponentFixture<AuthCheckTokenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AuthCheckTokenComponent]
    });
    fixture = TestBed.createComponent(AuthCheckTokenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
