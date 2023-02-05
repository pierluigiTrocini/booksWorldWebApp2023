import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable, map } from 'rxjs';
import { UrlBuilder } from './api-services/UrlBuilder';
import { GoogleBooksApis, Libro, NewYorkTimesApi, NyBook } from './util';

@Injectable({
  providedIn: 'root'
})
export class ApiUtilsService {

  constructor(private http: HttpClient) { }

  initialize(startIndex: number): Observable<Libro[]> {
    let builder = new UrlBuilder();
    builder.maxResults(40);
    builder.startIndex(startIndex);
    builder.relevance();
    builder.paidBooks();
    return this.http.get<GoogleBooksApis> (builder.build()).pipe(map((data: GoogleBooksApis) => data.items));
  }

  mostraNovita(): Observable<Libro[]> {
    let builder = new UrlBuilder();
    builder.newest();
    return this.http.get<GoogleBooksApis>(builder.build())
            .pipe(map((data: GoogleBooksApis) => data.items));
  }

  cercaLibriRicercaAvanzata(autore: string, editore: string, genere: string): Observable<Libro[]> {
    let builder = new UrlBuilder();
    builder.author(autore);
    builder.publisher(editore);
    builder.category(genere);
    builder.maxResults(40);
    return this.http.get<GoogleBooksApis>(builder.build())
          .pipe(map((data: GoogleBooksApis) => data.items));
  }

  cercaLibriPerIsbn(isbn: string): Observable<Libro[]>{
    let builder = new UrlBuilder();
    builder.isbn(isbn);
    builder.maxResults(1);
    return this.http.get<GoogleBooksApis>(builder.build())
          .pipe(map((data: GoogleBooksApis) => data.items));
  }

  getNyTimesBestsellers( nyApiUrl: string ): Observable<NyBook[]>{
    return this.http.get<NewYorkTimesApi>(nyApiUrl)
      .pipe(map((data: NewYorkTimesApi) => data.results.books));
  }

}
