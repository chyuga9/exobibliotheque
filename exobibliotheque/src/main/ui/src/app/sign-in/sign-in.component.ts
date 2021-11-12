import { Component, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

 authStatus:boolean;
  userForm:FormGroup;
  constructor(private authService:AuthService,
              private router:Router,) {
               }

  ngOnInit(): void {
    this.authStatus = this.authService.isAuth;
  }

  onConnect(){
    
    this.authService.signIn();
    this.authStatus = this.authService.isAuth;
    this.router.navigate(['oeuvres']);
    /*this.authService.signIn().then(
      () => {
        console.log('Sign In is successful');
        this.authStatus = this.authService.isAuth;
        this.router.navigate(['oeuvres']);
      }
    );
    */
  }

  onSignOut(){
    this.authService.signOut();
    this.authStatus = this.authService.isAuth;
  }
}
