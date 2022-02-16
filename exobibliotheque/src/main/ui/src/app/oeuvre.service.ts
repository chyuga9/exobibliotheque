import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject, Observable, of, interval } from 'rxjs';
import { takeUntil,catchError, retry, take, tap  } from 'rxjs/operators';
import { Category } from './model/category';
import { Genre } from './model/genre';
import { Oeuvre } from './model/oeuvre';

@Injectable({
  providedIn: 'root'
})

// -------- peut être utile https://makina-corpus.com/front-end/mise-en-pratique-de-rxjs-dans-angular
// -------- https://angular.io/guide/http
// -------- https://angular.io/tutorial/toh-pt6
export class OeuvreService {
    rootURL = 'http://localhost:9007/api';

    counterSubject: Subject<Observable<number>> = new Subject<Observable<number>>();
  oeuvres:Oeuvre[] = [];
  oeuvresSubject = new Subject<Oeuvre[]>();

constructor(private http: HttpClient){
  this.test();
  this.getOeuvres();
}

  getOeuvres() {
    this.http.get<Oeuvre[]>(this.rootURL + '/oeuvres').subscribe(
      (oeuvres:Oeuvre[]) => {
        //(oeuvres:any) => {
        this.oeuvres = oeuvres;
        console.log("oeuvreService - " + this.oeuvres)
        this.emitOeuvres();
      }
    );
      }

  emitOeuvres(){
    this.oeuvresSubject.next(this.oeuvres);
  }

  test(){
    let counter = interval(1000);
    this.counterSubject.next(counter);
    console.log("From OeuvreService " + counter);
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
 // https://angular.io/tutorial/toh-pt6
  searchGenre(term:string): Observable<Genre[]>{
    if (!term.trim()) {
      // if not search term, return empty genre array.
      return of([]);
    } 
    console.log({term}); 
    return this.http.get<Genre[]>(`${this.rootURL}/genres/${term}`).pipe(
      tap(x => x.length ?
         console.log(`found genres matching "${term}"`) :
         console.log(`no genres matching "${term}"`)),
      catchError(this.handleError<Genre[]>('searchGenre', []))
    );
  }
  
/**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
 private handleError<T>(operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    //this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
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
