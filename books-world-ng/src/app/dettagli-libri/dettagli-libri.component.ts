import { Component, OnInit} from '@angular/core';
import { Libro, Utente} from '../util';
import { ServerService } from '../server.service';
import { ActivatedRoute } from '@angular/router';
import { ApiUtilsService } from '../api-utils.service';
import { HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-dettagli-libri',
  templateUrl: './dettagli-libri.component.html',
  styleUrls: ['./dettagli-libri.component.css']
})
export class DettagliLibriComponent implements OnInit {

  url : string = "";


  isbn: string = "";
  lunghezzaIsbn?: number;

  sessionId: string = "";

  libri: Libro[] = [];

  utente: Utente = new Utente();

  titolo?: string;
  testo?: string;
  numStelle?: number;





  utentePossiedeLibro : boolean = false;
  utenteHaPostatoRecensione : boolean = false;    // Serve a capire se l'utente ha postato una recensione riguardante questo libro


  constructor(private service: ServerService, private http: HttpClient, private api: ApiUtilsService, private activatedRoute: ActivatedRoute){}




  ngOnInit(): void {

    const url = new URL(window.location.href);
    this.url = url.href;
    this.isbn = url.pathname.split("/")[2];

    this.lunghezzaIsbn = this.isbn.length;


    let sessionId = url.searchParams.get("jsessionid");
    if (sessionId){
      this.sessionId = sessionId;
      this.service.getUserBySession(this.sessionId).subscribe( utente => {
        this.utente = utente;}).add(()=>{
          if (this.utente.username ){
            console.log("ciao");
              this.service.proprietaLibro(this.isbn, this.utente.username).subscribe( utentePossiedeLibro => {
                this.utentePossiedeLibro = utentePossiedeLibro;
              console.log(utentePossiedeLibro);});
      
              this.service.postataRecensione(this.isbn, this.utente.username).subscribe( utenteHaPostatoRecensione => {
                this.utenteHaPostatoRecensione = utenteHaPostatoRecensione;});
          }
    });
      }




    this.api.cercaLibriPerIsbn(this.isbn)
              .subscribe(response => this.libri = response);
              console.log(this.libri.length);

  }



  aggiungiAlCarrello(): void{
    if (this.utente.username != null){
      this.service.aggiungiProdottoCarrello(this.utente.username, this.isbn).subscribe(result => {
        if(result){
          console.log("aggiuntoConSuccesso");
        }})
    }

  }


  invioRecensione(): void{


    if (((document.getElementById("formRecensione") as HTMLInputElement).checkValidity()) ){

      (document.getElementById("messaggioErrore") as HTMLInputElement).innerHTML = "";

      this.titolo = (document.getElementById("titoloRecensione") as HTMLInputElement).value;
      this.testo = (document.getElementById("testoRecensione") as HTMLInputElement).value;

      if ((document.getElementById("valutazioneUno") as HTMLInputElement).checked){
          this.numStelle = 5;
      }
      else if ((document.getElementById("valutazioneDue") as HTMLInputElement).checked){
        this.numStelle = 4;
      }
      else if ((document.getElementById("valutazioneTre") as HTMLInputElement).checked){
        this.numStelle = 3;
      }
      else if ((document.getElementById("valutazioneQuattro") as HTMLInputElement).checked){
        this.numStelle = 2;
      }
      else if ((document.getElementById("valutazioneCinque") as HTMLInputElement).checked){
        this.numStelle = 1;
      }
      if (this.numStelle != null && this.utente.username != null){

        this.service.aggiungiRecensione(this.isbn, this.utente.username, this.titolo , this.testo, this.numStelle ).subscribe(result =>{
          if(result){
            console.log("recensioneInseritaConSuccesso");
          }
        })
      }

      this.utenteHaPostatoRecensione = true;
  
    }

    else{

      (document.getElementById("messaggioErrore") as HTMLInputElement).innerHTML = "Riempi tutti i campi in maniera corretta.";

    }



  }







}
