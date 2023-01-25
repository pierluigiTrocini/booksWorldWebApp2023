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


}
