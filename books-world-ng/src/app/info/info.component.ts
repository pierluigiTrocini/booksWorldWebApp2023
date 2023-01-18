import { Component } from '@angular/core';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent {

  lista: String[] = [];

  autentica() {
    return gapi.auth.signIn({scope: "https://www.googleapis.com/auth/books"});
  }

}
