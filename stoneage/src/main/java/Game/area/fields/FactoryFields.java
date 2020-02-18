package Game.area.fields;


import Game.area.Areas;
import Game.resources.TypeResource;

import java.util.ArrayList;

public class FactoryFields {


    private FactoryFields(){}

    /**
     * initialize resource fields
     */
    public static ArrayList<Areas> initFields() {
        ArrayList<Areas> fields ;
        fields = new ArrayList<>();
        fields.add(new Fields("champ",1));
        fields.add(new Fields("riviere", TypeResource.Or, 7, 10, 6));
        fields.add(new Fields("forêt", TypeResource.Bois, 7, 28, 3));
        fields.add(new Fields("glaisière", TypeResource.Argile, 7, 18, 4));
        fields.add(new Fields("carrière", TypeResource.Pierre, 3, 12, 5));
        fields.add(new Fields("chasse", TypeResource.Nourriture, 9999999, 999999, 2));
        return fields;
    }



}
