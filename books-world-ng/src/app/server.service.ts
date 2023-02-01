import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Recensione } from './util';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServerService {

  constructor(private http: HttpClient) { }

  incrementaLikes( id: BigInt):Observable<boolean>{

    return this.http.get<boolean>('http://localhost:8080/IncrementaLikes?id=' + id);
  }

  incrementaDislikes( id: BigInt):Observable<boolean>{

    return this.http.get<boolean>('http://localhost:8080/IncrementaDislikes?id=' + id);
  }
  
  getRecensioni (ISBN: string): Observable<Recensione[]>{
    return this.http.get<Recensione[]>('http://localhost:8080/getRecensioni', {params: {ISBN: ISBN}});
  }
  

  rimuoviRecensione(id: BigInt): Observable<boolean>{
    return this.http.get<boolean>('http://localhost:8080/removeReview?id=' + id);
  }

  SegnalaRecensione(id: BigInt): Observable<boolean>{
    return this.http.get<boolean>('http://localhost:8080/segnalaRecensione?id=' + id);
  }






  aggiungiProdottoCarrello(username: string, isbn: string): Observable<boolean>{
    return this.http.get<boolean>('http://localhost:8080/addToCart', {params: {username: username, isbn: isbn}});
  }

  proprietaLibro(isbn: string, username: string): Observable<boolean>{
    return this.http.get<boolean>('http://localhost:8080/proprietaLibro', {params: {isbn: isbn, username: username}});
  }

  postataRecensione(isbn: string, username: string): Observable<boolean>{
    return this.http.get<boolean>('http://localhost:8080/postataRecensione', {params: {isbn: isbn, username: username}});
  }

  aggiungiRecensione(isbn: string, username: string, titolo: string, testo: string, numStelle: number): Observable<boolean>{
    return this.http.get<boolean>('http://localhost:8080/addToReviews', {params: {isbn: isbn, username: username, titolo: titolo, testo: testo, numStelle: numStelle}});
  }



  
}
