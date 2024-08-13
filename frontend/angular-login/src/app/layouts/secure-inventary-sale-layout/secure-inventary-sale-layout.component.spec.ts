import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecureInventarySaleLayoutComponent } from './secure-inventary-sale-layout.component';

describe('SecureInventarySaleLayoutComponent', () => {
  let component: SecureInventarySaleLayoutComponent;
  let fixture: ComponentFixture<SecureInventarySaleLayoutComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SecureInventarySaleLayoutComponent]
    });
    fixture = TestBed.createComponent(SecureInventarySaleLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
