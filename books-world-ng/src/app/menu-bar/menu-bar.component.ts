import { Component, Input, OnInit } from '@angular/core';
import { Libro } from '../util';
import { ApiUtilsService } from '../api-utils.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent implements OnInit {

  ricercaAvanzataChecked: boolean = false;
  infoChecked: boolean = false;

  libri: Libro[] = [];

  constructor(private router: Router){}

  changeStatusRicerca($event: any) {
    this.ricercaAvanzataChecked = !this.ricercaAvanzataChecked;
   // if( !this.ricercaAvanzataChecked ){
        //this.router.navigateByUrl('#');
   // }
  }

  changeStatusInfo($event: any) {
    this.infoChecked = !this.infoChecked;
  }

  ngOnInit(): void {

  }

}
