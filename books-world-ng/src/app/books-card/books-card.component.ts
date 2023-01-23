import { Component, Input } from '@angular/core';
import { Libro } from '../util';

@Component({
  selector: 'app-books-card',
  templateUrl: './books-card.component.html',
  styleUrls: ['./books-card.component.css']
})
export class BooksCardComponent {

  @Input()libro?: Libro;

  autore: string = "";
  editore: string = "";

}
