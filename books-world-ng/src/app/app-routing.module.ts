import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BooksCardComponent } from './books-card/books-card.component';
import { DettagliLibriComponent } from './dettagli-libri/dettagli-libri.component';
import { MenuBarComponent } from './menu-bar/menu-bar.component';

const routes: Routes = [
  {path: '#', component: MenuBarComponent},
  {path: 'book/:ISBN', component: DettagliLibriComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
