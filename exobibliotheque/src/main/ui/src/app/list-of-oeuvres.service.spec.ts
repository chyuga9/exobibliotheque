import { TestBed } from '@angular/core/testing';

import { ListOfOeuvresService } from './list-of-oeuvres.service';

describe('ListOfOeuvresService', () => {
  let service: ListOfOeuvresService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListOfOeuvresService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
