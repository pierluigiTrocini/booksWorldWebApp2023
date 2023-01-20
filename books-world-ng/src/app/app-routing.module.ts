import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VisualizzaLibriComponent } from './visualizza-libri/visualizza-libri.component';
import { BooksCardComponent } from './books-card/books-card.component';

const routes: Routes = [
  // {path: 'visualizzaNovita', component: VisualizzaNovitaComponent},
  // {path: 'visualizzaRiviste', component: VisualizzaRivisteComponent},
  // {path: 'visualizzaBestseller', component: VisualizzaBestsellerComponent}
  {path: 'visualizzaLibri', component: VisualizzaLibriComponent},
  {path: 'booksCard', component: BooksCardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
