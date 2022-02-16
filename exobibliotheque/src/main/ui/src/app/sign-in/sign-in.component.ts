import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { AuthService } from '../auth.service';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
model:any = {};
isAuth: boolean;
isAuthSubscription: Subscription;

constructor(private route: ActivatedRoute,
            private router: Router,
            private http: HttpClient,
            private auth:AuthService){}

ngOnInit(): void {
    //sessionStorage.setItem('token','');
    this.isAuthSubscription = this.auth.isAuthSubject.subscribe((authorization) => {
      this.isAuth = authorization;
})
}

onConnect(username:string, password:string){
  let resp = this.auth.signIn(username,password);
  return resp.subscribe(() =>{
    this.auth.isAuth = true;
    this.auth.emitAuth();
    return this.router.navigate(['/oeuvres']);
  },(error) =>{
    console.log(error);
  })
  /*
  setTimeout(() => {
    if(this.auth.isAuth == true){ this.router.navigate(['/oeuvres'])};
  }, 20); // wait 10ms to change the authentication
  */
}
/*
onConnect(){
  let url = 'http://localhost:9007/login';
  let result = this.http.post<Observable<boolean>>(url, {
    email: this.model.email,
    password: this.model.password
  }).subscribe(isValid => {
    if(isValid) {
    sessionStorage.setItem(
      'token',
      btoa(this.model.email + ":" + this.model.password)
    );
    this.router.navigate(['']);
  } else {
    alert("Authentication failed")
  }
    
  })
}
*/
 /*
 authStatus:boolean;
  userForm:FormGroup;
  constructor(private authService:AuthService,
              private formBuilder:FormBuilder,
              private router:Router,) {
               }

  ngOnInit(): void {
    this.authStatus = this.authService.isAuth;
    this.initUserForm();
  }

  initUserForm(){
    this.userForm = this.formBuilder.group(
      {
        email: ['',Validators.required],
        password: ['',Validators.required]
      }
    )
  }
  onConnect(){
    
   this.authService.signIn();
    this.authStatus = this.authService.isAuth;
    this.router.navigate(['oeuvres']);
    this.authService.signIn().then(
      () => {
        console.log('Sign In is successful');
        this.authStatus = this.authService.isAuth;
        this.router.navigate(['oeuvres']);
      }
    );
    
  }

  onSignOut(){
    this.authService.signOut();
    this.authStatus = this.authService.isAuth;
  }
  */
}
