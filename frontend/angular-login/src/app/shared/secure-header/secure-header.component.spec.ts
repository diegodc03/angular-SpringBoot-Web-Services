import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecureHeaderComponent } from './secure-header.component';

describe('SecureHeaderComponent', () => {
  let component: SecureHeaderComponent;
  let fixture: ComponentFixture<SecureHeaderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SecureHeaderComponent]
    });
    fixture = TestBed.createComponent(SecureHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
