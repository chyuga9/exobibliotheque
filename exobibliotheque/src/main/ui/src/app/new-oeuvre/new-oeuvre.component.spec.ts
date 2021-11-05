import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewOeuvreComponent } from './new-oeuvre.component';

describe('NewOeuvreComponent', () => {
  let component: NewOeuvreComponent;
  let fixture: ComponentFixture<NewOeuvreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewOeuvreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewOeuvreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
