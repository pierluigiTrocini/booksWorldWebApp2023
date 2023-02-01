import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DettagliLibriComponent } from './dettagli-libri.component';

describe('DettagliLibriComponent', () => {
  let component: DettagliLibriComponent;
  let fixture: ComponentFixture<DettagliLibriComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DettagliLibriComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DettagliLibriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
