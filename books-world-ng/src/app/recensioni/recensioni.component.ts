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
export class RecensioniComponent {
   
  @Input() ISBN: string = "";
  @Input() aggiorna: boolean = true;
  @Input() utente: Utente = new Utente();
  
  public recensioni: Recensione[] = [];
  public media: number = 0;
  

  constructor(private service: ServerService, private app: AppComponent) { }


  ngOnInit(): void {
    console.log(this.ISBN);
    this.service.getRecensioni(this.ISBN).subscribe(recensioni => {
      this.recensioni = recensioni;
      for(let recensione of this.recensioni){
        this.media+=recensione.NumeroStelle;
      }
      this.media/=this.recensioni.length;
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    //Called before any other lifecycle hook. Use it to inject dependencies, but avoid any serious work here.
    //Add '${implements OnChanges}' to the class.
    if(this.aggiorna){
      this.service.getRecensioni(this.ISBN).subscribe(recensioni => {
        this.recensioni = recensioni;})
  }
}

  IncrementaLike(id:BigInt):void{
    this.service.incrementaLikes(id).subscribe(result => {
      if(result){
    this.recensioni.forEach(recensione=>{ if(recensione.id==id){recensione.numeroMiPiace++;}});
        //oppure recensioni lo cambio dal db
      }
        
      })
  }

  eliminaRecensione(id: BigInt): void{
    this.service.rimuoviRecensione(id).subscribe(result => {
      if(result){
        this.service.getRecensioni(this.ISBN).subscribe(recensioni => this.recensioni = recensioni);
      }
    })

}


IncrementaDislike(id:BigInt):void{
  this.service.incrementaDislikes(id).subscribe(result => {
    if(result){
      this.recensioni.forEach(recensione=>{ if(recensione.id==id){recensione.numeroNonMiPiace++;}});
  

}})
}

getMedia(): number{
  return this.media;
}

segnalaRecensione(id:BigInt):void{
  this.service.SegnalaRecensione(id).subscribe(result => {
    if(result){
      console.log("Segnalata");
      

  }})
}

}
