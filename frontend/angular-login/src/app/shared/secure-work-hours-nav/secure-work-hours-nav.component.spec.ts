import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecureWorkHoursNavComponent } from './secure-work-hours-nav.component';

describe('SecureWorkHoursNavComponent', () => {
  let component: SecureWorkHoursNavComponent;
  let fixture: ComponentFixture<SecureWorkHoursNavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SecureWorkHoursNavComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SecureWorkHoursNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
