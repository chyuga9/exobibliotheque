import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OeuvreService {

  rootURL = '/api';

  constructor(private http: HttpClient) { }

  getOeuvres(){
    console.log('OeuvreSerivce - Fonctionne')
    //return this.http.get(this.rootURL + '/oeuvres');
    return this.http.get('http://localhost:9007/api/oeuvres');
  }
/*
  postOeuvre(){
    console.log('OeuvreSerivce - Fonctionne')
    return this.http.post('http://localhost:9007/api/oeuvres');

  }
  */
}
