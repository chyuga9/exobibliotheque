export class Oeuvre{
    private id:number;

    constructor(public name:String, public dateDeSortie: Date, public synopsis: string, public category:any, public genres:any[]){}
   // constructor(private name:String, private dateDeSortie: Date, private synopsis: string){}
}