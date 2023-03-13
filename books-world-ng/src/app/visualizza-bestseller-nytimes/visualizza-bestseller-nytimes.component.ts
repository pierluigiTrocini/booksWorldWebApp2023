import { Component, OnInit } from '@angular/core';
import { ApiUtilsService } from '../api-utils.service';
import { NyBook } from '../util';

@Component({
  selector: 'app-visualizza-bestseller-nytimes',
  templateUrl: './visualizza-bestseller-nytimes.component.html',
  styleUrls: ['./visualizza-bestseller-nytimes.component.css']
})
export class VisualizzaBestsellerNytimesComponent implements OnInit {

  libri: NyBook[] = [];

  private nytApiUrl: string = "https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=";

  constructor(
    private api: ApiUtilsService
  ){}

  ngOnInit(): void {
    this.api.getNyTimesBestsellers(this.nytApiUrl).subscribe(response => this.libri = response);
  }

}
