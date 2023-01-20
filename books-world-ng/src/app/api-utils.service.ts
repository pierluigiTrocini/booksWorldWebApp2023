import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable, map } from 'rxjs';
import { UrlBuilder } from './api-services/UrlBuilder';
import { GoogleBooksApis, Libro } from './util';

@Injectable({
  providedIn: 'root'
})
export class ApiUtilsService {

  constructor(private http: HttpClient) { }

  initialize(): Observable<Libro[]> {
    let builder = new UrlBuilder();
    builder.maxResults(40);
    builder.relevance();
    builder.books();
    return this.http.get<GoogleBooksApis> (builder.build()).pipe(map((data: GoogleBooksApis) => data.items));
  }

  mostraNovita(): Observable<Libro[]> {
    let builder = new UrlBuilder();
    builder.newest();
    return this.http.get<GoogleBooksApis>(builder.build())
            .pipe(map((data: GoogleBooksApis) => data.items));
  }

  cercaLibroPerAutore(autore: string): Observable<Libro[]> {
    let builder = new UrlBuilder();
    builder.author(autore);
    return this.http.get<GoogleBooksApis>(builder.build())
          .pipe(map((data: GoogleBooksApis) => data.items));
  }

}
