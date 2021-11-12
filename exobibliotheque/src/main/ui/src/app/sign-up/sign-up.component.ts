import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  authStatus:boolean;
  userForm:FormGroup;

  constructor(private authService:AuthService) { }

  ngOnInit(): void {
    this.authStatus = this.authService.isAuth;
  }

  onRegister(){
    
  }
  

  }

