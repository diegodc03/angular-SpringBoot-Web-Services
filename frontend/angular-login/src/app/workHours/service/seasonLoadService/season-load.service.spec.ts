import { TestBed } from '@angular/core/testing';

import { SeasonLoadService } from './season-load.service';

describe('SeasonLoadService', () => {
  let service: SeasonLoadService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SeasonLoadService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
