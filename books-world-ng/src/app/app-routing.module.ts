import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  // {path: 'visualizzaNovita', component: VisualizzaNovitaComponent},
  // {path: 'visualizzaRiviste', component: VisualizzaRivisteComponent},
  // {path: 'visualizzaBestseller', component: VisualizzaBestsellerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
