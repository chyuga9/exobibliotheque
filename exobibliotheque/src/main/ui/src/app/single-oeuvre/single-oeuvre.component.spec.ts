import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleOeuvreComponent } from './single-oeuvre.component';

describe('SingleOeuvreComponent', () => {
  let component: SingleOeuvreComponent;
  let fixture: ComponentFixture<SingleOeuvreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SingleOeuvreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SingleOeuvreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
