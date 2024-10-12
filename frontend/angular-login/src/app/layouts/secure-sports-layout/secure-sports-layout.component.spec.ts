import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecureSportsLayoutComponent } from './secure-sports-layout.component';

describe('SecureSportsLayoutComponent', () => {
  let component: SecureSportsLayoutComponent;
  let fixture: ComponentFixture<SecureSportsLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SecureSportsLayoutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SecureSportsLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
