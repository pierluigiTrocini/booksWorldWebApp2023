import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Libro } from '../util';
import { UrlBuilder } from '../api-services/UrlBuilder';
import { map } from 'rxjs';

@Component({
  selector: 'app-visualizza-libri',
  templateUrl: './visualizza-libri.component.html',
  styleUrls: ['./visualizza-libri.component.css']
})
export class VisualizzaLibriComponent implements OnInit {

  libri: Libro[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    // let builder = new UrlBuilder();
    // builder.newest();
    // this.http.get<JSON>(builder.build()).pipe(map((response: any) => {
    //   for(var i = 0; i < response.items.length; i++) {
    //     var item = response.items[i];
    //     let libro: Libro = {
    //       titolo: item.volumeInfo.title
    //     };
    //     this.libri.push(libro);
    //   }
    // }));
  }

}
