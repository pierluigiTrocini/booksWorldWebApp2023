import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { ServerService } from '../server.service';
import { Utente } from '../util';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profilo',
  templateUrl: './profilo.component.html',
  styleUrls: ['./profilo.component.css']
})
export class ProfiloComponent implements OnInit{
    public sessionId="";
    public utente:Utente=new Utente();

    constructor(private app:AppComponent,private service:ServerService,private router:Router){}


    ngOnInit(): void {
        const url=new URL(window.location.href);
        let sessionId = url.searchParams.get('jsessionid');
    if (sessionId != null){
      console.log("ciao");
      this.sessionId = sessionId;
      this.service.getUserBySession(this.sessionId).subscribe( Utente => {
        this.utente = Utente;});
      }

    }

}
