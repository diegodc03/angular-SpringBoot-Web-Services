import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangePasswordOutOfSessionComponent } from './change-password-out-of-session.component';

describe('ChangePasswordOutOfSessionComponent', () => {
  let component: ChangePasswordOutOfSessionComponent;
  let fixture: ComponentFixture<ChangePasswordOutOfSessionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChangePasswordOutOfSessionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChangePasswordOutOfSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
