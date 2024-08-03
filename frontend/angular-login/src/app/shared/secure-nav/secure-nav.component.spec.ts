import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecureNavComponent } from './secure-nav.component';

describe('SecureNavComponent', () => {
  let component: SecureNavComponent;
  let fixture: ComponentFixture<SecureNavComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SecureNavComponent]
    });
    fixture = TestBed.createComponent(SecureNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
