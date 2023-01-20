import { Component, OnInit } from '@angular/core';
import { Libro } from '../util';
import { ApiUtilsService } from '../api-utils.service';
import { map } from 'rxjs';

@Component({
  selector: 'app-visualizza-libri',
  templateUrl: './visualizza-libri.component.html',
  styleUrls: ['./visualizza-libri.component.css']
})
export class VisualizzaLibriComponent implements OnInit {

  libri: Libro[] = [];

  constructor(private api: ApiUtilsService) { }

  ngOnInit(): void {
    this.api.initialize().subscribe(response => this.libri = response);
  }

}
