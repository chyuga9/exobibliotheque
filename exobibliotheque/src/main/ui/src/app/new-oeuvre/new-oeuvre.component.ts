import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { from, Observable, Subject, Subscription } from 'rxjs';
import { debounceTime, distinctUntilChanged, filter, last, map, switchMap } from 'rxjs/operators';
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

  categories:Category[];
  categoriesSubscription: Subscription;
  ggg:string[]=[];
  genresList:Genre[];
  genresSubscription:Subscription;
  genres$!: Observable<Genre[]>;
  listGenres:Subscription;
  oeuvreForm = this.formBuilder.group(
    {genres: this.formBuilder.array(
      [

      ],Validators.required
    )}
  )

  private searchTerms = new Subject<string>();
  private searchTermsSubscription:Subscription;

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


  constructor(private oeuvreService:OeuvreService,
              private formBuilder: FormBuilder,
              private router:Router) { }

  ngOnInit(): void {
    this.initForm();
    this.getCategories();
    //this.getGenres();
    this.searchTermsSubscription = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(500),
      // ignore new term if same as previous term
      distinctUntilChanged(),
      // switch to new search observable each time the term changes
      switchMap((term: string) => this.oeuvreService.searchGenre(term)),
      map(
        genres => genres.map(
          genre => {
            console.log('Viens ici, résultat booleen = ' + this.ggg.includes(genre.genre) );
            if(this.ggg.includes(genre.genre)){
            console.log("quelque chose " + genre);
            genres.splice(genres.indexOf(genre));
          };
          this.genresList = genres;
          }
        )
            /*genre => {if(this.ggg.includes(genre.genre)){
            console.log("quelque chose " + genre);
            */
            //this.genresList.splice(this.genresList.indexOf(genre));
          

      ))
      .subscribe(
// ne fonctionne pas
      //genres => this.genresList = genres,
          //genre => this.ggg.includes(genre.genre),
        
      )        
      ;

      /*
      this.genres$.(
        genres => {genres.filter(
          genre => {genre.genre !== this.genre      //!== this.genres.controls.values )
  }))
*/
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
        console.log('genreList = '+ this.genresList);
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

containsInGgg(genre:string): boolean{
  for(let k = 0; k < this.ggg.length ; k++){
    if(genre !== this.ggg[k])
    {return false} ;
    
  }
  return true;
}

addGenre(genre:string){
  this.genres.push(this.formBuilder.control(genre));
  this.ggg.push(genre);
  /*
  for(let i = 0; i < this.genresList.length; i++ ){
    for(let k= 0; k < this.ggg.length; k++){
      if (this.genresList[i].genre === this.ggg[k]){
        this.genresList.splice
      }
      
    }
    
  }*/
  ;
  console.log(this.genres);
  console.log(this.ggg);
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

    // Push a search term into the observable stream.
  search(term:string){
    this.searchTerms.next(term);
    console.log(term)
  }

  ngOnDestroy(){
    this.categoriesSubscription.unsubscribe();
    this.searchTermsSubscription.unsubscribe();
  }
}
