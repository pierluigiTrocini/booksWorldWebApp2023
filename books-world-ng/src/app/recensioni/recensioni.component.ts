import { Component, SimpleChanges, OnChanges, OnInit } from '@angular/core';
import { ServerService } from '../server.service';
import { AppComponent } from '../app.component';
import { Input } from '@angular/core';
import { Recensione, Utente } from '../util';

@Component({
  selector: 'app-recensioni',
  templateUrl: './recensioni.component.html',
  styleUrls: ['./recensioni.component.css']
})
export class RecensioniComponent implements OnInit,OnChanges{
   
  @Input() ISBN: string = "";
  @Input() aggiorna: boolean = false;
  @Input() utente: Utente = new Utente();
  
  public recensioni: Recensione[] = [];
  public media: number = 0;
  

  constructor(private service: ServerService, private app: AppComponent) { }


  ngOnInit(): void {
    console.log(this.ISBN);
    console.log(this.utente.username);
    this.service.getRecensioni(this.ISBN).subscribe(recensioni => {
      this.recensioni = recensioni;
      console.log("giusto");
      console.log(this.media);
      for(let recensione of this.recensioni){
        console.log(recensione);
        this.media+=recensione.numeroStelle;
      }
      
      
      if (this.recensioni.length > 0){
          this.media/=this.recensioni.length;
          this.media=Number((this.media).toFixed(1));
      }
      console.log(this.media);
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    //Called before any other lifecycle hook. Use it to inject dependencies, but avoid any serious work here.
    //Add '${implements OnChanges}' to the class.
    /*if(this.aggiorna){
      this.media=0;
      this.service.getRecensioni(this.ISBN).subscribe(recensioni => {
        this.recensioni = recensioni;
        for(let recensione of this.recensioni){
          this.media+=recensione.numeroStelle;
        }
        this.media/=this.recensioni.length;
        this.media=Number((this.media).toFixed(1));
        console.log(this.media);
      });
    }*/
 }

  IncrementaLike(id:BigInt):void{
    this.service.incrementaLikes(id).subscribe(result => {
      if(result){
    this.recensioni.forEach(recensione=>{ if(recensione.id==id){recensione.numeroMiPiace++;}});
        //oppure recensioni lo cambio dal db
        (document.getElementById("dislike-"+id) as HTMLButtonElement).disabled=true;
        (document.getElementById("like-"+id) as HTMLButtonElement).disabled=true;
      }
      
        
      })
  }

  eliminaRecensione(id: BigInt): void{
    this.service.rimuoviRecensione(id).subscribe(result => {
      if(result){
        this.service.getRecensioni(this.ISBN).subscribe(recensioni => {this.recensioni = recensioni;
        this.media=0;
        for(let recensione of this.recensioni){
          this.media+=recensione.numeroStelle;
        }
        this.media/=this.recensioni.length;
        this.media=Number((this.media).toFixed(1));
       } );
      }
    })

}


IncrementaDislike(id:BigInt):void{
  this.service.incrementaDislikes(id).subscribe(result => {
    if(result){
      this.recensioni.forEach(recensione=>{ if(recensione.id==id){recensione.numeroNonMiPiace++;}});
      (document.getElementById("dislike-"+id) as HTMLButtonElement).disabled=true;
      (document.getElementById("like-"+id) as HTMLButtonElement).disabled=true;


  

}})
}

getMedia(): number{
  return this.media;
}

segnalaRecensione(id:BigInt):void{
  
  this.service.SegnalaRecensione(id).subscribe(result => {
    if(result){
      console.log("Segnalata");
      (document.getElementById("segnala-"+id) as HTMLButtonElement).disabled=true;
      
      

  }})
}

}
