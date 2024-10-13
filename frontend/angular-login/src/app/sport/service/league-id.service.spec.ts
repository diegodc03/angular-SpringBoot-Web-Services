import { TestBed } from '@angular/core/testing';

import { LeagueIdService } from './league-id.service';

describe('LeagueIdService', () => {
  let service: LeagueIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LeagueIdService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
