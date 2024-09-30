import { JwtInterceptorExpired } from './jwt-interceptor-expired';

describe('JwtInterceptorExpired', () => {
  it('should create an instance', () => {
    expect(new JwtInterceptorExpired()).toBeTruthy();
  });
});
