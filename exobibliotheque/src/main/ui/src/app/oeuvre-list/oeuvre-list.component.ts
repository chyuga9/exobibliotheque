import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { Oeuvre } from '../model/oeuvre.model';
import { OeuvreService } from '../oeuvre.service';

@Component({
  selector: 'app-oeuvre-list',
  templateUrl: './oeuvre-list.component.html',
  styleUrls: ['./oeuvre-list.component.scss']
})
export class OeuvreListComponent implements OnInit {

  oeuvres:any[] = [];
  oeuvreSubject: Subject<boolean> = new Subject<boolean>();

  constructor(private oeuvreService: OeuvreService) { }

  ngOnInit(): void {
    console.log('OeuvreListComponent - ngoninit');
    this.getAllOeuvres();
  }

  getAllOeuvres() {
    console.log('OeuvreListComponent - getAllOeuvres');
    this.oeuvreService.getOeuvres().pipe(takeUntil(this.oeuvreSubject)).subscribe((oeuvres: any) => {
      console.log(oeuvres);  
      this.oeuvres = oeuvres;
    });
  }

  onNewOeuvre(){
    
  }
}
