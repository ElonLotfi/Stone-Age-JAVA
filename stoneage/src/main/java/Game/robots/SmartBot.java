package Game.robots;

import Game.area.Areas;
import Game.area.TypeArea;
import Game.resources.TypeResource;
import java.util.ArrayList;
import java.util.Random;

public class SmartBot extends Robot {
    private Random choose = new Random();


    public SmartBot(int id) {
        super(id, "SmartBot", 5);
    }


    /**
     * choose a random number of figures between 1 and the figurines of the player
     */
    @Override
    public int chooseNumberUsedFigures(ArrayList<Areas> areas) {
        if(areas.size() == 1 && areas.get(0).getName().equals("chasse")){
            return this.getFigures();
        }
        else{return choose.nextInt((getFigures()-1)+1)+1;}
    }

    /**
     * choose a building in case of sufficient resources to buy it, if not choose a field
     */
    @Override
    public int chooseField(ArrayList<Areas> areas) {
        int idAreas =0;
        if( checkBuildingPurchase(areas)){
            idAreas =getAreas(areas,TypeArea.BuildingCard);
        }
        else{
            idAreas =getAreas(areas,TypeArea.Fields);
        }
        return idAreas;
    }


    /**
     * return a resource field or a building depending on the case
     */
    public int getAreas(ArrayList<Areas> areas,TypeArea typeArea){
        int idAreas = 0;
        if(typeArea.equals(TypeArea.Fields)){
            for(int i= 0;i<areas.size();i++){
                if(areas.get(i).getTypeArea().equals(TypeArea.Fields)){
                    idAreas = i;
                    if(areas.get(i).getName().equals("champ") && this.getAgricultureLevel() < 10){break;}
                }
            }
        }
        if(typeArea.equals(TypeArea.BuildingCard)){
            for(int i= 0;i<areas.size();i++){
                if(areas.get(i).getTypeArea().equals(TypeArea.BuildingCard) || areas.get(i).getTypeArea().equals(TypeArea.Fields) ){
                    idAreas = i;
                    if(areas.get(i).getTypeArea().equals(TypeArea.BuildingCard)){break;}
                }
            }
        }

        return idAreas;
    }


    /**
     * check if the robot has the building price
     */
    public Boolean checkBuildingPurchase(ArrayList<Areas> areas) {
        int cpt = 0;
        for (int i = 0; i < areas.size(); i++) {
            if (areas.get(i).getTypeArea().equals(TypeArea.BuildingCard)) {
                if (areas.get(i).getNbrPierre() <= this.nbrResourceViaType(TypeResource.Pierre) && areas.get(i).getNbrArgile() <= this.nbrResourceViaType(TypeResource.Argile) && areas.get(i).getNbrBois() <= this.nbrResourceViaType(TypeResource.Bois) && areas.get(i).getNbrOr() <= this.nbrResourceViaType(TypeResource.Or)) {
                    cpt += 1;
                }
            }
        }
        if(cpt >0){return true;}
        else{return false;}
    }



}
