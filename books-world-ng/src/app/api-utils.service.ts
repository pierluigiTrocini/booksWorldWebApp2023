import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Libro } from './util';

@Injectable({
  providedIn: 'root'
})
export class ApiUtilsService {

  private url: string = "url per i prezzi";

  constructor(private http: HttpClient) { }

  // mostraNovita(): Observable<Libro[]> {
  //   let builder = new UrlBuilder();
  //   builder.newest();
  //   libri: Observable<Libro[]> = this.http.post<Libro[]>(builder.build(), resp).forEach(function(resp) {
  //     for(var i = 0; i < resp.items.length; i++) {
  //       var item = resp.items[i];
  //       let libro: Libro = {
  //         titolo: item.volumeInfo.title,
  //         autori: item.volumeInfo.authors,
  //         editore: item.volumeInfo.publisher,
  //         isbn: item.volumeInfo.industryIdentifiers[0].identifier,
  //         categorie: item.volumeInfo.categories,
  //         prezzo: 0,
  //         linkAnteprima: item.accessInfo.webReaderLink,
  //         linkImmagine: item.volumeInfo.imageLinks.thumbnail
  //       }
  //       libro.prezzo = 0; // pierlui, arrangiati
  //       libri.push(libro);
  //     }
  //   });
  //   return libri;
  // }

}
