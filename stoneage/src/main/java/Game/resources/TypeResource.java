package Game.resources;

public enum TypeResource {
    Or("Or",0),
    Argile("Argile",0),
    Pierre("Pierre",0),
    Bois("Bois",0),
    Nourriture("Nourriture",0);

    private String type = "";
    private int initValue ;

    TypeResource(String type,int initValue){
        this.type = type ;
        this.initValue = initValue;
    }


}
