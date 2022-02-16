import { Category } from "./category";
import { Genre } from "./genre";

export class Oeuvre {
    
            id:number;
 
            // vient de l'ancienne classe que j'ai transformé en interface
            constructor(public name:String, public dateDeSortie: Date, public synopsis: string, public category:Category, public genres:Genre[]){}
            // constructor(private name:String, private dateDeSortie: Date, private synopsis: string){}
         }