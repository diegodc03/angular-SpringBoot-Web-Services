import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecureWorkHoursLayoutComponent } from './secure-work-hours-layout.component';

describe('SecureWorkHoursLayoutComponent', () => {
  let component: SecureWorkHoursLayoutComponent;
  let fixture: ComponentFixture<SecureWorkHoursLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SecureWorkHoursLayoutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SecureWorkHoursLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
