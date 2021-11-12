import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuth=false;
  isAuthSubject: Subject<boolean> = new Subject<boolean>();

  constructor() { 
    this.emitAuth();
    }

   emitAuth(){
    this.isAuthSubject.next(this.isAuth);
  }

  signIn(){
    this.isAuth = true;
    this.emitAuth();

    
    /*
        return new Promise(
          (resolve, reject) => {
            setTimeout(
              () => {
                this.isAuth = true;
                resolve(true);
              }, 500
            );
          }
        )*/
  }

  signOut(){
    this.isAuth = false;
    this.emitAuth();
  }
}
