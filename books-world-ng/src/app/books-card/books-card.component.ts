import { Component, Input, OnInit } from '@angular/core';
import { Libro } from '../util';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-books-card',
  templateUrl: './books-card.component.html',
  styleUrls: ['./books-card.component.css']
})
export class BooksCardComponent {
  @Input()libro  ?: Libro;

  constructor(
    private route: ActivatedRoute
  ){}


}
