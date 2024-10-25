import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecureSportNavComponent } from './secure-sport-nav.component';

describe('SecureSportNavComponent', () => {
  let component: SecureSportNavComponent;
  let fixture: ComponentFixture<SecureSportNavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SecureSportNavComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SecureSportNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
