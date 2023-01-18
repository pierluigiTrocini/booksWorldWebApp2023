import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent implements OnInit {

  @Input()ricercaAvanzataChecked: boolean = false;
  @Input()infoChecked: boolean = false;

  libri: String[] = [];

  changeStatusRicerca($event: any) {
    this.ricercaAvanzataChecked = !this.ricercaAvanzataChecked;
  }

  changeStatusInfo($event: any) {
    this.infoChecked = !this.infoChecked;
  }

  ngOnInit(): void {
  }

}
