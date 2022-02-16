import { Component, Input, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from '../auth.service';
import { Oeuvre } from '../model/oeuvre';
import { OeuvreService } from '../oeuvre.service';

@Component({
  selector: 'app-single-oeuvre',
  templateUrl: './single-oeuvre.component.html',
  styleUrls: ['./single-oeuvre.component.scss']
})
export class SingleOeuvreComponent implements OnInit {
  isAuth:boolean;
  isAuthSubscription:Subscription;
  @Input() oeuvre: any;
  constructor(private authService:AuthService) { }

  ngOnInit(): void {
    this.isAuthSubscription = this.authService.isAuthSubject.subscribe(
      (authorization) => {
        this.isAuth = authorization;
        console.log('isAuth fds= '  + this.isAuth)
      }
    );

  }

  onDeleteOeuvre(oeuvre:Oeuvre){
    
  }
}
