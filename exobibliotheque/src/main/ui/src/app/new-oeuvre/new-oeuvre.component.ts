import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Category } from '../model/category';
import { Genre } from '../model/genre';
import { Oeuvre } from '../model/oeuvre';
import { OeuvreService } from '../oeuvre.service';

@Component({
  selector: 'app-new-oeuvre',
  templateUrl: './new-oeuvre.component.html',
  styleUrls: ['./new-oeuvre.component.scss']
})

//  Cette vidéo aide https://www.youtube.com/watch?v=bzYMbWxcX1c&ab_channel=OSTechHelp
export class NewOeuvreComponent implements OnInit, OnDestroy {

  oeuvreForm = this.formBuilder.group(
    {genres: this.formBuilder.array(
      [
        this.formBuilder.control('')
      ]
    )}
  )
/*
  oeuvreForm:FormGroup;
  genreForm:FormGroup;
  genresTab:FormArray;
  
  genreForm = new FormGroup({    
    genre :new FormControl ('', Validators.required),
  });
  
  genres = new FormArray([genreForm,]);
  */
  //fileIsUploading=false;
  //fileIsUploaded=false;
  //fileUrl:string;
  categories:Category[];
  categoriesSubscription: Subscription;
  genresList:Genre[];
  genresSubscription:Subscription;

  constructor(private oeuvreService:OeuvreService,
              private formBuilder: FormBuilder,
              private router:Router) { }

  ngOnInit(): void {
    this.initForm();
    this.getCategories();
    this.getGenres();
  }

  getCategories(){
    this.categoriesSubscription = this.oeuvreService.getCategories().subscribe(
      (categories:Category[]) => {
        this.categories = categories;
      }
    )
  }

  getGenres(){
    this.genresSubscription = this.oeuvreService.getGenres().subscribe(
      (genres:Genre[]) => {
        this.genresList = genres;
      }
    )
  }

initForm(){

  this.oeuvreForm = this.formBuilder.group(
    {
      name: ['', Validators.required],
      dateDeSortie: ['',Validators.required],
      synopsis:'',
      genres: this.genres,
      category:['',Validators.required],
    }
  );
  
}

addGenre(){
  this.genres.push(this.formBuilder.control(''));
}

removeGenre(index:number){
  this.genres.removeAt(index);
}

// https://www.youtube.com/watch?v=aOQ1xFC3amw&ab_channel=AngularUniversity




/*
addGenre(){
  this.genreForm = this.formBuilder.group({
    genre:['', Validators.required],
  })
  this.genres.push(this.genreForm);
}
*/
get genres(){
  return this.oeuvreForm.get('genres') as FormArray;
}



// https://angular.io/guide/http
// https://stackoverflow.com/questions/48458343/angular-5-httpclient-post-not-posting
onSaveOeuvre(){
  const name = this.oeuvreForm.get('name')?.value;
  const dateDeSortie = this.oeuvreForm.get('dateDeSortie')?.value;
  const synopsis = this.oeuvreForm.get('synopsis')?.value;
  const category = this.oeuvreForm.get('category')?.value;
  const genres = this.oeuvreForm.get('genres')?.value;
  const newOeuvre = new Oeuvre(name, dateDeSortie, synopsis, category, genres);
  //  Observables need to be observed to make a request.
  this.oeuvreService.saveOeuvre(newOeuvre).subscribe(
    //response=> console.log(response),
    //err=> console.log(err)
  );
  this.router.navigate(['/oeuvres']);
  }

  ngOnDestroy(){
    this.categoriesSubscription.unsubscribe();
  }
}
