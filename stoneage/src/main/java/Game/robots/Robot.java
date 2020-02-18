package Game.robots;

import Game.area.Areas;
import Game.area.building.BuildingCard;
import Game.resources.*;
import Game.useful.ColorText;
import Game.useful.GameMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Robot {

    private String name;
    private int robotId;
    private int figures;
    private int initFigure;
    private Resources resources;
    private int resourceAdded;
    private int score;
    private HashMap<String, Integer> chosenFieldsForResource;
    private static Logger loggerRobot = Logger.getLogger(Robot.class.getName());
    private Level level;
    private HashMap<TypeResource,Integer> food;
    private int agricultureLevel;
    private ArrayList<BuildingCard> buildingList;
    private ArrayList<BuildingCard> reservedBuilding ;




    public Robot(int id, String name, int nbrFigures) {
        this.name = name;
        this.robotId = id;
        this.figures = nbrFigures;
        this.initFigure = nbrFigures;
        this.resources = new Resources();
        this.chosenFieldsForResource = new HashMap<>();
        this.score = 0;
        this.resourceAdded = 0;
        this.level = Level.FINE;
        this.food = new HashMap<>();
        this.agricultureLevel = 0;
        this.buildingList = new ArrayList<>();
        this.reservedBuilding = new ArrayList<>();



    }

    /**
     * modify the number of robot figurines
     *
     * @param figures the number of figurines used
     */
    public void setFigures(int figures) {
        this.figures = figures;
    }

    /**
     * return the number of robot figurines
     *
     * @return
     */
    public int getFigures() {
        return figures;
    }

    /**
     * return the name of robot .
     *
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     * return the name of robot
     *
     * @return
     */
    public int getRobotId() {
        return this.robotId;
    }

    /**
     * return the robot resource list
     *
     * @return
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * Used for test
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }


    /**
     * update the resource number of a field + update score of robot
     *
     * @param valueDice the dice roll result
     * @param field     the targeted field
     */
    public void setResources(int valueDice, Areas field) {
            this.resources.updateResource(field.getTypeResource(), valueDice / field.getTax());
            this.resourceAdded = valueDice / field.getTax();


    }

    /**
     * return robot score .
     *
     * @return
     */
    public int getScore() {
        return score;
    }


    /**
     * reset figures
     */
    public void resetFigures() {
        this.figures = initFigure;
    }

    /**
     * get the number of added resource .
     *
     * @return
     */
    public int getResourceAdded() {
        return resourceAdded;
    }

    /**
     * return the list where the robot has placed his figurines : to calculate the resources .
     *
     * @return
     */
    public HashMap<String, Integer> getChosenFieldsResource() {
        return chosenFieldsForResource;
    }


    /**
     * add a field to the chosen field list by a robot + the number of figurines placed
     *
     * @param areas     the targeted field
     * @param nbrFigures the number to add
     */
    public void addChosenFieldsResource(Areas areas, int nbrFigures) {
        this.chosenFieldsForResource.put(areas.getName(), nbrFigures);
    }

    /**
     * choose a number of figures
     */

    public abstract int chooseNumberUsedFigures(ArrayList<Areas> areas);

    /**
     * choose a field .
     *
     * @param areas list of available zones .
     * @return
     */
    public abstract int chooseField(ArrayList<Areas> areas);


    /**
     * convert the different type of resource to food
     * @param value
     * @return
     */
    public int feedMe(int value){
        int difference = value ;

        int cpt = 0 ;
        for(TypeResource typeResource : this.resources.getResourceTable().keySet()){
            if(difference == 0){break;}
            if(typeResource != TypeResource.Nourriture && this.resources.nbrResourceViaType(typeResource) >0 && difference<this.resources.nbrResourceViaType(typeResource) ){
                this.food.put(typeResource,difference);
                this.resources.updateResource(typeResource,-difference);
                cpt += difference;
                difference = 0 ;
                break;
            }
            if(typeResource != TypeResource.Nourriture && this.resources.nbrResourceViaType(typeResource) >0 && difference >= this.resources.nbrResourceViaType(typeResource)){
                int get =  this.resources.nbrResourceViaType(typeResource) ;
                this.food.put(typeResource,get);
                cpt += get;
                this.resources.updateResource(typeResource,-get);
                difference -= get;


            }
        }
        return cpt;
    }

    /**
     * to give food to the robot figures
     */
    public boolean feedFigures () {
        int sumFood = this.resources.nbrResourceViaType(TypeResource.Nourriture) + this.agricultureLevel;
        int cptFigures = 0 ;


        if(sumFood >= initFigure){
            loggerRobot.log(level,GameMessage.messageWhenFeedFigures(this,initFigure,TypeResource.Nourriture));
            this.resources.updateResource(TypeResource.Nourriture,-initFigure);
            cptFigures = initFigure;
            return true ;
        }
        if(sumFood > 0 && sumFood < initFigure){
            this.food.put(TypeResource.Nourriture,sumFood);
            this.resources.updateResource(TypeResource.Nourriture,-sumFood);
            int difference = initFigure - sumFood;
            cptFigures = feedMe(difference) + sumFood;

        }
        if(sumFood == 0){
            int difference = initFigure;
            cptFigures = feedMe(difference);
        }
        if(cptFigures >= this.initFigure){
            String display = ColorText.ANSI_RED + this.name + " " + this.robotId + ColorText.ANSI_WHITE +  " gives ";
            for(TypeResource typeResource : this.food.keySet()){
                display += " " + ColorText.ANSI_BLUE + this.food.get(typeResource) + " " + ColorText.ANSI_GREEN + typeResource.toString() + " " +ColorText.ANSI_WHITE ;
            }
            loggerRobot.log(level,GameMessage.displayMessage(display));
            this.food.clear();
            return true;

        }


         if(cptFigures < initFigure){
            for(TypeResource typeResource : this.food.keySet()){
                this.resources.updateResource(typeResource,this.food.get(typeResource));
            }
             this.food.clear();
             this.score += -10 ;
            loggerRobot.log(level,GameMessage.messageWhenLostPoint(this,10));

        }
        this.food.clear();
        return false;


    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setInitFigure(int initFigure) {
        this.initFigure = initFigure;
    }

    /**
     * update the farming level
     */
    public void upgradeAgriculture(){
        if(this.agricultureLevel <10){
            int level = this.agricultureLevel;
            this.setAgricultureLevel(level + 1);
        }
    }

    public void setAgricultureLevel(int agricultureLevel) {
        this.agricultureLevel = agricultureLevel;
    }

    public int getAgricultureLevel() {
        return agricultureLevel;
    }

    /**
     * return the resource number of a type
     */
    public int nbrResourceViaType(TypeResource typeResource){
        return this.resources.nbrResourceViaType(typeResource);
    }

    /**
     * the robot can buy a building
     */
    public boolean buyBuilding(BuildingCard e){
        if(e.getNbrPierre() <= nbrResourceViaType(TypeResource.Pierre) && e.getNbrArgile()<= nbrResourceViaType(TypeResource.Argile) && e.getNbrBois() <= nbrResourceViaType(TypeResource.Bois) && e.getNbrOr() <= nbrResourceViaType(TypeResource.Or) ) {
            this.buildingList.add(e);
            this.resources.updateResource(TypeResource.Pierre,-e.getNbrPierre());
            this.resources.updateResource(TypeResource.Argile,-e.getNbrArgile());
            this.resources.updateResource(TypeResource.Or,-e.getNbrOr());
            this.resources.updateResource(TypeResource.Bois,-e.getNbrBois());
            this.score += e.getEarnedPoint();

            return true ;

        }
        return false;
    }


    /**
     * book a building
     */
    public void addReservedBuilding(BuildingCard buildingCard){
        this.reservedBuilding.add(buildingCard);
    }

    /**
     *  return the list of buildings purchased
     */
    public ArrayList<BuildingCard> getBuildingList() {
        return buildingList;
    }

    /**
     * empty the list of buildings reserved
     */
    public void resetReservedBuilding(){
        if(this.reservedBuilding.size()>0){
            for(int i =0 ;i<this.reservedBuilding.size();i++){
                this.reservedBuilding.get(i).setReserved(null);
            }
        }
        this.reservedBuilding.clear();
    }

    /**
     * return the list of buildings reserved
     */

    public ArrayList<BuildingCard> getReservedBuilding() {
        return reservedBuilding;
    }
    public int getInitFigure() {
        return initFigure;
    }

}



