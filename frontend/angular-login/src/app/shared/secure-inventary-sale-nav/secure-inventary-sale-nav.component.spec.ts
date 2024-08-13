import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecureInventarySaleNavComponent } from './secure-inventary-sale-nav.component';

describe('SecureInventarySaleNavComponent', () => {
  let component: SecureInventarySaleNavComponent;
  let fixture: ComponentFixture<SecureInventarySaleNavComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SecureInventarySaleNavComponent]
    });
    fixture = TestBed.createComponent(SecureInventarySaleNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
