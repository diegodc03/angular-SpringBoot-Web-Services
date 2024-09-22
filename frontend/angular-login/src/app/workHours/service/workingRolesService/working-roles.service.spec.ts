import { TestBed } from '@angular/core/testing';

import { WorkingRolesService } from './working-roles.service';

describe('WorkingRolesService', () => {
  let service: WorkingRolesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkingRolesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
