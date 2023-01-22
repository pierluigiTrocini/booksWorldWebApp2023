import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VisualizzaLibriComponent } from './visualizza-libri/visualizza-libri.component';
import { BooksCardComponent } from './books-card/books-card.component';
import { MenuBarComponent } from './menu-bar/menu-bar.component';

const routes: Routes = [
  {path: '#', component: MenuBarComponent},
  {path: 'visualizzaLibri', component: VisualizzaLibriComponent},
  {path: 'booksCard', component: BooksCardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
