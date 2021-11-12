import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject, Subscription } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { AuthService } from '../auth.service';
import { Oeuvre } from '../model/oeuvre';

import { OeuvreService } from '../oeuvre.service';

@Component({
  selector: 'app-oeuvre-list',
  templateUrl: './oeuvre-list.component.html',
  styleUrls: ['./oeuvre-list.component.scss']
})
export class OeuvreListComponent implements OnInit, OnDestroy{

  authStatus:boolean;
  isAuthSubscription:Subscription;  
  oeuvres:Oeuvre[] = [];
  oeuvreSubscription: Subscription;

  constructor(private oeuvreService: OeuvreService,
              private router:Router,
              private authService:AuthService){}



  ngOnInit(): void{
      this.authStatus = this.authService.isAuth;
      this.isAuthSubscription = this.authService.isAuthSubject.subscribe(
        (authorization) => {this.authStatus = authorization,
        console.log('Oeuvrelist - ' + authorization)}
      );
     this.oeuvreSubscription = this.oeuvreService.oeuvreSubject.subscribe(
      (oeuvres:Oeuvre[]) => {
        this.oeuvres = oeuvres;
        console.log("0-");
    console.log(this.oeuvres);
      }, (error) => {
        console.log('Il y a une erreur ' + error);
        
      }
    );
    // arrive forcément avant car local et asynchrone, normal qu'il soit vide
    console.log("1-");
    console.log(this.oeuvres);
    this.oeuvreService.getOeuvres();

  }

  ngOnDestroy() {
    this.oeuvreSubscription.unsubscribe();
    this.isAuthSubscription.unsubscribe();
  }

  onNewOeuvre(){
    this.router.navigate(['/newoeuvre']);
  }
/*
  getAllOeuvres() {
    console.log('OeuvreListComponent - getAllOeuvres');
    this.oeuvreService.getOeuvres().pipe(takeUntil(this.oeuvreSubject)).subscribe((oeuvres: any) => {
      console.log(oeuvres);  
      this.oeuvres = oeuvres;
    });
  }
*/
}
 /*
  Version Fonctionnelle

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
*/


  

