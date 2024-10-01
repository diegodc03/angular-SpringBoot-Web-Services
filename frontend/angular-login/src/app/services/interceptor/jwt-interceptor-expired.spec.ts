import { JwtInterceptorExpired } from './jwt-interceptor-expired';
import { Router } from '@angular/router';
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('JwtInterceptorExpired', () => {

  let routerMock: jasmine.SpyObj<Router>;

  beforeEach(() => {
    // Creamos un mock del Router
    routerMock = jasmine.createSpyObj('Router', ['navigate']);
    
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        { provide: Router, useValue: routerMock }  // Inyectamos el mock del Router
      ]
    });
  });

  it('should create an instance', () => {
    const interceptor = new JwtInterceptorExpired(routerMock);
    expect(interceptor).toBeTruthy();
  });
});
