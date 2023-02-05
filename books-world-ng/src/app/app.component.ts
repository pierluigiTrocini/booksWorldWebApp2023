import { Component, OnInit } from '@angular/core';
import { ServerService } from './server.service';
import { Utente } from './util';
import { Location } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'books-world-ng';
  isLogged: boolean =false;
  sessionId:string="";
  utente:Utente =new Utente();

  constructor(private service:ServerService,private location:Location){}

  ngOnInit():void{

    const urlParams=new URLSearchParams(window.location.search);
    let session=urlParams.get('jsessionid');
    if(session!=null && session != ""){
      this.service.checkisLogged(session).subscribe(isLogged=>this.isLogged=isLogged).add(()=>{
        if(this.isLogged && session!=null && session!=""){
          this.service.getUserBySession(session).subscribe(utente=>this.utente=utente).add(()=>{
            console.log(this.utente.username);
            if(this.isLogged && session!=null && session!=""){
              this.sessionId=session;
              if(this.utente.username == undefined){
                this.isLogged=false;
                window.location.replace("http://localhost4200/");
                alert("Login invalido");
              }
            }
          });
        }
      });
    }

  }

 public getSessionId():string{
    return this.sessionId;
  }
}
