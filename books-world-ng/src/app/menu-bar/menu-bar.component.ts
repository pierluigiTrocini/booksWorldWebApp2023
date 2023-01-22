import { Component, Input, OnInit } from '@angular/core';
import { Libro } from '../util';
import { ApiUtilsService } from '../api-utils.service';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent implements OnInit {

  @Input()ricercaAvanzataChecked: boolean = false;
  @Input()infoChecked: boolean = false;

  libri: Libro[] = [];

  changeStatusRicerca($event: any) {
    this.ricercaAvanzataChecked = !this.ricercaAvanzataChecked;
  }

  changeStatusInfo($event: any) {
    this.infoChecked = !this.infoChecked;
  }

  scrollDown($event: any) {
    document.querySelector(".card-group")?.scrollIntoView();
  }

  ngOnInit(): void {

  }

}
