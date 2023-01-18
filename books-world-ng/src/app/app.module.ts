import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuBarComponent } from './menu-bar/menu-bar.component';
import { VisualizzaRicercaAvanzataComponent } from './visualizza-ricerca-avanzata/visualizza-ricerca-avanzata.component';
import { InfoComponent } from './info/info.component';
import { VisualizzaLibriComponent } from './visualizza-libri/visualizza-libri.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuBarComponent,
    VisualizzaRicercaAvanzataComponent,
    InfoComponent,
    VisualizzaLibriComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
