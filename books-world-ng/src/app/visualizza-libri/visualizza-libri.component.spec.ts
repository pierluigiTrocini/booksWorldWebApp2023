import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaLibriComponent } from './visualizza-libri.component';

describe('VisualizzaLibriComponent', () => {
  let component: VisualizzaLibriComponent;
  let fixture: ComponentFixture<VisualizzaLibriComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VisualizzaLibriComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VisualizzaLibriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
