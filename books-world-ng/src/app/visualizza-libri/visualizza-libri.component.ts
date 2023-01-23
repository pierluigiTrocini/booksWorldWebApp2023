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

  ricercaAvanzata(): void {
    this.api.cercaLibriRicercaAvanzata(this.autore, this.editore, this.genere)
              .subscribe(response => this.libri = response);
  }

  ngOnInit(): void {
    this.api.initialize().subscribe(response => this.libri = response);
  }



}
