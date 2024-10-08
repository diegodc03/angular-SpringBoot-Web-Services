import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowClasificationComponent } from './show-clasification.component';

describe('ShowClasificationComponent', () => {
  let component: ShowClasificationComponent;
  let fixture: ComponentFixture<ShowClasificationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowClasificationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowClasificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
