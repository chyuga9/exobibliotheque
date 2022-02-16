import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ListOfOeuvresService } from './list-of-oeuvres.service';
import { UserService } from './user.service';
import { HeaderComponent } from './header/header.component';
import { OeuvreListComponent } from './oeuvre-list/oeuvre-list.component';
import { OeuvreService } from './oeuvre.service';
import { SingleOeuvreComponent } from './single-oeuvre/single-oeuvre.component';
import { NewOeuvreComponent } from './new-oeuvre/new-oeuvre.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { AuthService } from './auth.service';
import { FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faFilm, faMinus } from '@fortawesome/free-solid-svg-icons';

const appRoutes : Routes =[
  {path: 'oeuvres', component: OeuvreListComponent},
  {path: 'newoeuvre', component: NewOeuvreComponent},
  {path: 'signin', component:SignInComponent},
  {path: 'signup', component:SignUpComponent},
  {path: '', redirectTo:'oeuvres' , pathMatch: 'full'},
  {path: '**', redirectTo:'oeuvres'},
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    OeuvreListComponent,
    SingleOeuvreComponent,
    NewOeuvreComponent,
    SignInComponent,
    SignUpComponent
  ],
  imports: [
    BrowserModule,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    // Ajouter manuellement l'import
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [
    UserService,
    OeuvreService,
    ListOfOeuvresService,
    AuthService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

  // tuto => https://github.com/FortAwesome/angular-fontawesome/blob/5e897dc284dec645251c46ae27617cb5d5b2a6d7/docs/usage/icon-library.md
  constructor(library:FaIconLibrary){
    library.addIcons(faMinus);
  }

 }
