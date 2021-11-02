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

const appRoutes : Routes =[
  {path: 'books', component: OeuvreListComponent},
  {path: '', redirectTo:'books' , pathMatch: 'full'},
  {path: '**', redirectTo:'books'},
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    OeuvreListComponent,
    SingleOeuvreComponent
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
