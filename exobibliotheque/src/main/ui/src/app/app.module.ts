import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
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

const appRoutes : Routes =[
  {path: 'oeuvres', component: OeuvreListComponent},
  {path: 'newoeuvre', component: NewOeuvreComponent},
  {path: '', redirectTo:'oeuvres' , pathMatch: 'full'},
  {path: '**', redirectTo:'oeuvres'},
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    OeuvreListComponent,
    SingleOeuvreComponent,
    NewOeuvreComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    // Ajouter manuellement l'import
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [
    UserService,
    OeuvreService,
    ListOfOeuvresService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
