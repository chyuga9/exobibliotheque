import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { faThumbsDown } from '@fortawesome/free-solid-svg-icons';
import { interval, Observable, Subject, Subscription } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

 /* 
 // Partie Test
  subject = new Subject();
  counter:Observable<number>;
  machin:string = "machin";
  machinSubject=new Subject<String>();
  counterSubject = new Subject();
  */
  isAuth=false;
  isAuthSubject: Subject<boolean> = new Subject<boolean>();
  rootURL = 'http://localhost:9007';

  constructor( private http:HttpClient) { 
   // this.test();
    this.emitAuth();
    }

   emitAuth(){
    this.isAuthSubject.next(this.isAuth);
  }
 

  canActivate() : Observable<Boolean> | Promise<Boolean> | boolean {
    if (this.isAuth === true) {return true}
    else{return false};
  }

  signIn(username:string, password:string ){
    /*
    this.isAuth = true;
    this.emitAuth();
*/
  let url = 'http://localhost:9007/api/';

  // ----- "btoa" sert à encoder le string en base64, je ne sais pas pourquoi on en a besoin mais ça ne fonctionne pas autrement
  let headers = new HttpHeaders({Authorization: 'Basic ' + btoa(username + ':' + password)});
  return this.http.get(url,{headers,responseType:'text'});
  /*
  let resp = this.http.get(url,{headers,responseType:'text'});
  return resp.subscribe(() =>{
    this.isAuth = true;
    return this.emitAuth();
  },(error) =>{
    console.log(error);
  })
  */
    /*
        return new Promise(
          (resolve, reject) => {
            setTimeout(
              () => {
                this.isAuth = true;
                this.emitAuth();
                resolve(true);
              }, 500
            );
            
          }
        )
        */
/*
        return this.http.post<Observable<Boolean>>(this.rootURL+'/login',{
          userName : email,
          password : password
        }).subscribe(

        )*/
  }

  signOut(){
    this.isAuth = false;
    this.emitAuth();
  }

  /*
  test(){
    this.counter = interval(1000);
    // Fonctionne
    //1
    this.subject.subscribe({
      next: (v) => console.log(`observerA: ${v}`)
    });
    this.subject.next(this.counter);

    //2
    
    this.machinSubject.subscribe({
      next: (v) => console.log(`observerB: ${v}`)
    });
    
    this.machinSubject.next(this.machin);

    //3
    
    this.counter.subscribe(
      value =>{
        console.log("truc " + value)
      });
      this.counter.subscribe(this.counterSubject);
    
    this.counter.subscribe(this.subject)
}
    */ 
}


