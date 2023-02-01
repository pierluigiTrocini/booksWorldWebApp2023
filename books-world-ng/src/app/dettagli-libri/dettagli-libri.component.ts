import { Component, OnInit} from '@angular/core';
import { Libro, Utente} from '../util';
import { ServerService } from '../server.service';
import { ActivatedRoute } from '@angular/router';
import { ApiUtilsService } from '../api-utils.service';
import { HttpClient, HttpHandler } from '@angular/common/http';
import {FormControl, FormGroup, Validators} from '@angular/forms'

@Component({
  selector: 'app-dettagli-libri',
  templateUrl: './dettagli-libri.component.html',
  styleUrls: ['./dettagli-libri.component.css']
})
export class DettagliLibriComponent implements OnInit {

  formRecensione = new FormGroup({
    titolo: new FormControl('', [Validators.required, Validators.minLength(15), Validators.maxLength(100), Validators.pattern("([a-zA-Z]*)([a-zA-z0-9 ]*)")]),
    corpo: new FormControl('', [Validators.required, Validators.minLength(50), Validators.maxLength(2000), Validators.pattern("([a-zA-Z0-9]*)([a-zA-z0-9 ]*)")]),
    option: new FormControl('', Validators.required)

  })

  url : string = "";


  isbn: string = "";

  sessionId: string = "";

  libri: Libro[] = [];
  libro?: Libro;

  username: string = "";

  utente?: Utente;





  utentePossiedeLibro : boolean = false;
  utenteHaPostatoRecensione : boolean = false;    // Serve a capire se l'utente ha postato una recensione riguardante questo libro


  constructor(private service: ServerService, private http: HttpClient, private api: ApiUtilsService, private activatedRoute: ActivatedRoute){}


  

  ngOnInit(): void {

    const url = new URL(window.location.href);
    this.url = url.href;
    this.isbn = url.pathname.split("/")[2];

    
    let sessionId = url.searchParams.get("sessionId")
    if (sessionId != null){
      this.sessionId = sessionId;
    }


    if (this.username !== ""){
        this.service.proprietaLibro(this.isbn, this.username).subscribe( utentePossiedeLibro => {
          this.utentePossiedeLibro = utentePossiedeLibro;});
        
        this.service.postataRecensione(this.isbn, this.username).subscribe( utenteHaPostatoRecensione => {
          this.utenteHaPostatoRecensione = utenteHaPostatoRecensione;});
    }
      



    this.api.cercaLibriPerIsbn(this.isbn)
              .subscribe(response => this.libri = response);
    this.libro = this.libri[0];




  }
  


  aggiungiAlCarrello(): void{
    this.service.aggiungiProdottoCarrello(this.username, this.isbn).subscribe(result => {
      if(result){
        console.log("aggiuntoConSuccesso");
      }})

  }


  invioRecensione(): void{

    this.formRecensione.markAllAsTouched();

    if (this.formRecensione.valid){

      let titolo = this.formRecensione.get('titolo')?.value;
      let testo = this.formRecensione.get('corpo')?.value;
      let option = this.formRecensione.get('option')?.value;

      let numStelle;


        if (option != null){
          if (option === "option1")
            numStelle = 5;
          else if (option === "option2")
            numStelle = 4;
          else if (option === "option3")
            numStelle = 3;
          else if (option === "option4")
            numStelle = 2;
          else if (option === "option5")
            numStelle = 1;
        }

        if (titolo != null && testo != null && numStelle != null){
            this.service.aggiungiRecensione(this.isbn, this.username, titolo , testo, numStelle ).subscribe(result =>{
                if(result){
                  console.log("recensioneInseritaConSuccesso");
                }
              })

        }

        this.utenteHaPostatoRecensione = true;
    }

    else{
      alert("I campi non sono stati riempiti in maniera corretta");
    }

  }











}
