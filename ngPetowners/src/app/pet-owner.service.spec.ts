import { TestBed } from '@angular/core/testing';

import { PetOwnerService } from './pet-owner.service';

describe('PetOwnerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PetOwnerService = TestBed.get(PetOwnerService);
    expect(service).toBeTruthy();
  });
});
