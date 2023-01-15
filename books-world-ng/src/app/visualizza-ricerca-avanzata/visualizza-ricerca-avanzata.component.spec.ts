import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaRicercaAvanzataComponent } from './visualizza-ricerca-avanzata.component';

describe('VisualizzaRicercaAvanzataComponent', () => {
  let component: VisualizzaRicercaAvanzataComponent;
  let fixture: ComponentFixture<VisualizzaRicercaAvanzataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VisualizzaRicercaAvanzataComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VisualizzaRicercaAvanzataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
