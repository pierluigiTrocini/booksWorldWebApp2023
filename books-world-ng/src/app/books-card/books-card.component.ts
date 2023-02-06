import { Component, Input } from '@angular/core';
import { Libro } from '../util';
import { AppComponent } from '../app.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-books-card',
  templateUrl: './books-card.component.html',
  styleUrls: ['./books-card.component.css']
})
export class BooksCardComponent {

  @Input()libro?: Libro;

  autore: string = "";
  editore: string = "";
  constructor(private app:AppComponent,private router:Router){}
 
  public getSessionId():string{
    return this.app.getSessionId();
  }

  public NascondiRisultati():void{
    
    this.router.navigateByUrl('#');
  }
}
