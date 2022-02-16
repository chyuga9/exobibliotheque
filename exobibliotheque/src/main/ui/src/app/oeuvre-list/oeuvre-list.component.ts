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
  machinSub: Subscription;
  counterSub: Subscription;
  isAuth:boolean;
  isAuthSubscription:Subscription;  
  oeuvres:Oeuvre[] = [];
  oeuvreSubscription: Subscription;

  constructor(private oeuvreService: OeuvreService,
              private router:Router,
              private authService:AuthService){}



  ngOnInit(): void{
      

      this.isAuthSubscription = this.authService.isAuthSubject.subscribe(
        (authorization) => {
          console.log('iniit');
        this.isAuth = authorization,
        console.log('Oeuvrelist - ' + authorization);
      }
      );
      this.authService.emitAuth();
     this.oeuvreSubscription = this.oeuvreService.oeuvresSubject.subscribe(
      (oeuvres:Oeuvre[]) => {
        console.log('oeuvre');
        this.oeuvres = oeuvres;
    console.log(this.oeuvres);
      }, (error) => {
        console.log('Il y a une erreur ' + error);
        
      }
    );
    this.oeuvreService.emitOeuvres();
    // arrive forcément avant car local et asynchrone, normal qu'il soit vide
    console.log(this.oeuvres);

    //this.authStatus = this.authService.isAuth;
      /* 
      // Partie Test
      this.machinSub = this.authService.machinSubject.subscribe(
        (value) => {
        console.log('AuthServiceMachin1 - ' + value);
      }
      );
      //this.authService.machinSubject.next("45");
      
      // ne fonctionne pas
     
      

     // Fonctionne
     /*
      this.counterSub = this.authService.counter.subscribe(
        (value) => {
          console.log(value);
        console.log('AuthService1 - ' + value);
      }
      );

      this.authService.subject.subscribe(
      {
        next: (v) => console.log(`ojhfosq: ${v}`)
      })

       this.authService.counterSubject.subscribe(
        (counter) => {
          console.log(counter);
        console.log('AuthService2 - ' + counter);
      });
      */

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


  

