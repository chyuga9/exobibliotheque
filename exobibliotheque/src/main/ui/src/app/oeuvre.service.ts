import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { takeUntil,catchError, retry, take  } from 'rxjs/operators';
import { Category } from './model/category.model';
import { Oeuvre } from './model/oeuvre.model';

@Injectable({
  providedIn: 'root'
})
export class OeuvreService {
    rootURL = 'http://localhost:9007/api';


  oeuvres:Oeuvre[] = [];
  oeuvreSubject = new Subject<Oeuvre[]>();

constructor(private http: HttpClient){
  this.getOeuvres();
}

  getOeuvres() {
    this.http.get(this.rootURL + '/oeuvres').subscribe(
      //(oeuvres:Oeuvre[]) => {
        (oeuvres:any) => {
        this.oeuvres = oeuvres;
        this.emitOeuvres();
      }
    );
      }

  emitOeuvres(){
    this.oeuvreSubject.next(this.oeuvres);
  }

  saveOeuvre(oeuvre:Oeuvre){
    console.log('Sauvegarde de l\' oeuvre');
    console.log(oeuvre);
    this.http.post(this.rootURL + '/oeuvre',oeuvre);
  }

  getCategories(){
    return this.http.get<Category[]>(this.rootURL + '/categories');
    
      }
    
  

/*
  Version fonctionnelle

  rootURL = 'http://localhost:9007/api';

  constructor(private http: HttpClient) { }

  getOeuvres(){
    console.log('OeuvreSerivce - Fonctionne')
    //return this.http.get(this.rootURL + '/oeuvres');
    return this.http.get('http://localhost:9007/api/oeuvres');
  }
  */
/*
  postOeuvre(){
    console.log('OeuvreSerivce - Fonctionne')
    return this.http.post('http://localhost:9007/api/oeuvres');

  }
  */
}
