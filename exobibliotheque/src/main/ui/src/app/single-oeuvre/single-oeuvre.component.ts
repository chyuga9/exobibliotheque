import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-single-oeuvre',
  templateUrl: './single-oeuvre.component.html',
  styleUrls: ['./single-oeuvre.component.scss']
})
export class SingleOeuvreComponent implements OnInit {

  @Input() oeuvre: any;
  
  constructor() { }

  ngOnInit(): void {
  }

}
