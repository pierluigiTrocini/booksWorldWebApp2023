import { Component, OnInit, OnChanges, Input, ViewChild } from '@angular/core';
import { Libro } from '../util';
import { ApiUtilsService } from '../api-utils.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-visualizza-libri',
  templateUrl: './visualizza-libri.component.html',
  styleUrls: ['./visualizza-libri.component.css']
})
export class VisualizzaLibriComponent implements OnInit, OnChanges {

  libri: Libro[] = [];

  startIndex: number = 0;

  @Input() autore: string = "";
  @Input() editore: string = "";
  @Input() genere: string = "";

  constructor(
    private api: ApiUtilsService,
    private route: ActivatedRoute
  ) { }

  ngOnChanges(): void {
    this.route.queryParams.subscribe( () => {
      var autore = this.route.snapshot.paramMap.get('autore');
      var editore = this.route.snapshot.paramMap.get('editore');
      var genere = this.route.snapshot.paramMap.get('genere');
      if (autore !== null) this.autore = autore;
      if (editore !== null) this.editore = editore;
      if (genere !== null) this.genere = genere;
    });
    document.querySelectorAll("body>app-root>app-visualizza-libri>div.card-group").forEach( node => node.remove() );
    this.ricercaAvanzata();
  }

  applyFilter(array: Libro[]): Libro[]{
    return array.filter( it => 
      it.volumeInfo.industryIdentifiers != undefined &&
      it.volumeInfo.industryIdentifiers != null &&
      it.volumeInfo.industryIdentifiers.at(0)?.type.includes("ISBN")
    );
  }

  ricercaAvanzata(): void {
    this.api.cercaLibriRicercaAvanzata(this.autore, this.editore, this.genere)
              .subscribe(response => this.libri = this.applyFilter(response))

  }

  ngOnInit(): void {
    if( this.libri.splice(0) )
      this.api.initialize(this.startIndex).subscribe(response => this.libri = this.applyFilter(response))
  }

  addMoreItems(): void{
    this.api.initialize(this.startIndex += 40).subscribe(response => this.libri = this.libri.concat((this.applyFilter(response))));
  }

}
