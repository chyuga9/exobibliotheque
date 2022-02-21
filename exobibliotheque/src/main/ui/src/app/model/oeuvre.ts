import { Category } from "./category";
import { Genre } from "./genre";

export class Oeuvre {
    
            id:number;
            /*
            // Ne peut pas transformer la classe en interface car un moment je crée un objet
            // Oeuvre pour l'envoyer dans l'api Java
            name:String;
            dateDeSortie: Date;
            synopsis: string;
            category:Category;
            genres:Genre[];
            image?:string;
            */

            // vient de l'ancienne classe que j'ai transformé en interface
            constructor(public name:String, public dateDeSortie: Date, public synopsis: string, public category:Category, public genres:Genre[], public image?:string){}
            // constructor(private name:String, private dateDeSortie: Date, private synopsis: string){}
         }