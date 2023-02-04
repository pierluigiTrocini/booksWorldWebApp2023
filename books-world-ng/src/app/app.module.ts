import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuBarComponent } from './menu-bar/menu-bar.component';
import { VisualizzaRicercaAvanzataComponent } from './visualizza-ricerca-avanzata/visualizza-ricerca-avanzata.component';
import { InfoComponent } from './info/info.component';
import { VisualizzaLibriComponent } from './visualizza-libri/visualizza-libri.component';
import { HttpClientJsonpModule, HttpClientModule } from '@angular/common/http';
import { BooksCardComponent } from './books-card/books-card.component';
import { RouterModule } from '@angular/router';
import { VisualizzaBestsellerNytimesComponent } from './visualizza-bestseller-nytimes/visualizza-bestseller-nytimes.component';
import { RecensioniComponent } from './recensioni/recensioni.component';
import { DettagliLibriComponent } from './dettagli-libri/dettagli-libri.component';

import { ProfiloComponent } from './profilo/profilo.component'; 



@NgModule({
  declarations: [
    AppComponent,
    MenuBarComponent,
    VisualizzaRicercaAvanzataComponent,
    InfoComponent,
    VisualizzaLibriComponent,
    BooksCardComponent,
    VisualizzaBestsellerNytimesComponent,
    RecensioniComponent,
    DettagliLibriComponent,
    ProfiloComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot([]),
    HttpClientModule,
    HttpClientJsonpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
