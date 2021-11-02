import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OeuvreListComponent } from './oeuvre-list.component';

describe('OeuvreListComponent', () => {
  let component: OeuvreListComponent;
  let fixture: ComponentFixture<OeuvreListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OeuvreListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OeuvreListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
