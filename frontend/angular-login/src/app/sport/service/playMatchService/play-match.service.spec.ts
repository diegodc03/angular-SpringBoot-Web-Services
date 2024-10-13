import { TestBed } from '@angular/core/testing';

import { PlayMatchService } from './play-match.service';

describe('PlayMatchService', () => {
  let service: PlayMatchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlayMatchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
