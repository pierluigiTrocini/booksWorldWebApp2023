import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Libro } from '../util';
import { UrlBuilder } from '../api-services/UrlBuilder';

@Component({
  selector: 'app-visualizza-libri',
  templateUrl: './visualizza-libri.component.html',
  styleUrls: ['./visualizza-libri.component.css']
})
export class VisualizzaLibriComponent implements OnInit {

  libri: Libro[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    let builder = new UrlBuilder();
    builder.newest();
    var items: any;
    this.http.get<JSON>(builder.build()).subscribe(jsonItems => items = jsonItems.items);
    for (var i = 0; i < items.length; i++) {
      var item = items[i];
      let libro: Libro = {
        titolo: item.volumeInfo.title,
        autori: item.volumeInfo.authors,
        editore: item.volumeInfo.publisher,
        isbn: item.volumeInfo.industryIdentifiers[0].identifier,
        categorie: item.volumeInfo.categories,
        prezzo: 0,
        linkAnteprima: item.accessInfo.webReaderLink,
        linkImmagine: item.volumeInfo.imageLinks.thumbnail
      }
      libro.prezzo = 0; // pierlui, arrangiati
      this.libri.push(libro);
    }
  }

}
