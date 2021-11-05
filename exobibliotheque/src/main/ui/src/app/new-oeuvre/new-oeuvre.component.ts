import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Category } from '../model/category.model';
import { Oeuvre } from '../model/oeuvre.model';
import { OeuvreService } from '../oeuvre.service';

@Component({
  selector: 'app-new-oeuvre',
  templateUrl: './new-oeuvre.component.html',
  styleUrls: ['./new-oeuvre.component.scss']
})

//  Cette vidéo aide https://www.youtube.com/watch?v=bzYMbWxcX1c&ab_channel=OSTechHelp
export class NewOeuvreComponent implements OnInit, OnDestroy {

  oeuvreForm:FormGroup;
  fileIsUploading=false;
  fileIsUploaded=false;
  fileUrl:string;
  categories:Category[];
  categoriesSubscription: Subscription;
  categorySelected:Category ={
    category:""
  }

  constructor(private oeuvreService:OeuvreService,
              private formBuilder: FormBuilder,
              private router:Router) { }

  ngOnInit(): void {
    this.initForm();
    this.getCategories();
  }

  // Dramane si tu as une autre idée pour récupérer les catégories sans créer un modele je suis preneur
  getCategories(){
    this.categoriesSubscription = this.oeuvreService.getCategories().subscribe(
      (categories:Category[]) => {
        this.categories = categories;
      }
    )
  }

initForm(){
  this.oeuvreForm = this.formBuilder.group(
    {
      name: ['', Validators.required],
      dateDeSortie: ['',Validators.required],
      synopsis:'',

      genres:[''],
      category:['',Validators.required],
      //genres:[[''], Validators.required]
    }
  )
}

onSaveOeuvre(){
  const name = this.oeuvreForm.get('name')?.value;
  const dateDeSortie = this.oeuvreForm.get('dateDeSortie')?.value;
  const synopsis = this.oeuvreForm.get('synopsis')?.value;
  const category = this.oeuvreForm.get('category')?.value;
  const genres = this.oeuvreForm.get('genres')?.value;
  const newOeuvre = new Oeuvre(name, dateDeSortie, synopsis, category, genres);
  this.oeuvreService.saveOeuvre(newOeuvre);
  this.router.navigate(['/oeuvres']);
  }

  ngOnDestroy(){
    this.categoriesSubscription.unsubscribe();
  }
}
