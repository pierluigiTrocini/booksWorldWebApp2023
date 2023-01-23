import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaBestsellerNytimesComponent } from './visualizza-bestseller-nytimes.component';

describe('VisualizzaBestsellerNytimesComponent', () => {
  let component: VisualizzaBestsellerNytimesComponent;
  let fixture: ComponentFixture<VisualizzaBestsellerNytimesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VisualizzaBestsellerNytimesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VisualizzaBestsellerNytimesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
