import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { takeUntil,catchError, retry, take  } from 'rxjs/operators';
import { Category } from './model/category';
import { Genre } from './model/genre';
import { Oeuvre } from './model/oeuvre';

@Injectable({
  providedIn: 'root'
})

// -------- https://angular.io/guide/http
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

  saveOeuvre(oeuvre:Oeuvre): Observable<Oeuvre>{
    console.log('Sauvegarde de l\' oeuvre');
    console.log(oeuvre);
    console.log(this.http.post<Oeuvre>(this.rootURL + '/oeuvre',oeuvre));
    console.log('uuuuuuuuuuuuuuuuu');
    return    this.http.post<Oeuvre>(this.rootURL + '/oeuvre',oeuvre);

  }

  getCategories(){
    return this.http.get<Category[]>(this.rootURL + '/categories');
    
      }
    
  getGenres(){
    return this.http.get<Genre[]>(this.rootURL + '/genres');
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
