import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-visualizza-ricerca-avanzata',
  templateUrl: './visualizza-ricerca-avanzata.component.html',
  styleUrls: ['./visualizza-ricerca-avanzata.component.css']
})
export class VisualizzaRicercaAvanzataComponent {

  autore: string  = "";
  editore: string = "";
  genere: string = "";

  cambiaCampi(): void {
    let autoreCambiato = (document.getElementById("autore") as HTMLInputElement).value;
    let editoreCambiato = (document.getElementById("editore") as HTMLInputElement).value;
    let genereCambiato = (document.getElementById("genere") as HTMLInputElement).value;
    this.autore = autoreCambiato!==""?autoreCambiato:"";
    this.editore = editoreCambiato!==""?editoreCambiato:"";
    this.genere = genereCambiato!==""?genereCambiato:"";
  }

}
