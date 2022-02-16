import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, OnDestroy {

  authStatus:boolean;
  isAuthSubscription:Subscription;
  constructor(private authService:AuthService) { }

  ngOnInit(): void {
    this.isAuthSubscription = this.authService.isAuthSubject.subscribe(
      (authorization) => {this.authStatus = authorization,
      //Quand il n'a aucune information, ce qu'il y a dans subscribe ne s'execute pas
      console.log('Header - '+ authorization)
      }

    );
    console.log('Header - laaaaa')
  }

  ngOnDestroy(){
    this.isAuthSubscription.unsubscribe();
  }

  onDisconnect(){
    this.authService.signOut();
    console.log('authStatus = ' + this.authStatus )
  }

  onChangeStatus(){
    if(this.authStatus === true){
      this.authService.signOut();
    console.log('authStatus = ' + this.authStatus )
  }else{
    this.authService.signIn("","");
    console.log('authStatus = ' + this.authStatus )
  }
}
}
