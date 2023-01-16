import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VisualizzaRicercaAvanzataComponent } from './visualizza-ricerca-avanzata/visualizza-ricerca-avanzata.component';

const routes: Routes = [
  // {path: 'visualizzaNovita', component: VisualizzaNovitaComponent},
  // {path: 'visualizzaRiviste', component: VisualizzaRivisteComponent},
  // {path: 'visualizzaBestseller', component: VisualizzaBestsellerComponent},
  {path: 'visualizzaRicercaAvanzata', component: VisualizzaRicercaAvanzataComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
