package Game.area;


import Game.resources.TypeResource;
import Game.robots.Robot;
import Game.useful.ColorText;

import java.util.ArrayList;


public  abstract class Areas {
    private String name ;
    private TypeResource typeResource;
    private int tax;
    private int nbrResource ;
    private int figureCapacity ;
    private int idBuilding;
    private int earnedPoint;
    private int nbrOr ;
    private int nbrArgile;
    private int nbrPierre;
    private int nbrBois;
    private Robot reserved;
    private TypeArea typeArea;






    /**
     * constructor of Fields
     */
    public Areas(String name, TypeResource typeResource, int figureCapacity, int nbrResource, int tax) {
        this.name = name;
        this.typeResource = typeResource ;
        this.nbrResource = nbrResource ;
        this.figureCapacity = figureCapacity;
        this.tax = tax;
        this.typeArea = TypeArea.Fields;

    }

    /**
     * constructor of FarmLand
     */
    public Areas(String name, int figureCapacity) {
        this.name = name;
        this.figureCapacity = figureCapacity;
        this.typeArea = TypeArea.Fields;

    }

    /**
     * constructor of buildings
     */
    public Areas(int idBuilding, int nbrOr, int nbrArgile, int nbrPierre, int nbrBois, int earnedPoint,Robot robot){
        this.idBuilding = idBuilding;
        this.figureCapacity = 1;
        this.earnedPoint = earnedPoint;
        this.nbrOr = nbrOr;
        this.nbrArgile = nbrArgile;
        this.nbrBois = nbrBois;
        this.nbrPierre = nbrPierre;
        this.reserved = robot;
        this.name = getBuildingName();
        this.typeArea = TypeArea.BuildingCard;

    }


    /***
     * returning the field capacity
     * @return
     */
    public int getFigureCapacity() {
        return figureCapacity;
    }

    /***
     * update the capacity of a field
     * @param value the value to withdraw from the capacity
     */
    public void setFigureCapacity(int value) {
        this.figureCapacity = value;
    }

    /***
     * return the name of field
     * @return
     */

    public String getName() {
        return this.name;
    }

    /***
     * return the type of resource
     * @return
     */
    public TypeResource getTypeResource() {
        return typeResource;
    }

    /***
     * return the value on which we will divide the die roll result
     * @return
     */
    public int getTax() {
        return tax;
    }

    /***
     * return the number of resources
     * @return
     */
    public int getNbrResource() {
        return nbrResource;
    }

    /***
     * update the number of resources
     * @param number
     */
    public void updateNbrResource(int number) {
        this.nbrResource -= number;
    }

    /**
     * the building is reserved by a robot
     * @param reserved the targeted building
     */
    public void setReserved(Robot reserved) {
        this.reserved = reserved;
    }

    /**
     * check if the building is reserved
     */
    public Robot getReserved() {
        return reserved;
    }

    /**
     * return the name of a building
     */
    public String getBuildingName(){
        String name = ColorText.ANSI_BLACK + "凸 "+ColorText.ANSI_GREEN+ this.idBuilding +"|" +ColorText.ANSI_WHITE;
        if(this.nbrOr >0){
            name+=this.nbrOr + ColorText.ANSI_BLUE +"Or" +ColorText.ANSI_WHITE;
        }
        if(this.nbrArgile>0){
            name+=this.nbrArgile+ ColorText.ANSI_RED+ "Argile"+ColorText.ANSI_WHITE;
        }
        if(this.nbrBois>0){
            name+=this.nbrBois+ColorText.ANSI_PURPLE+ "Bois"+ColorText.ANSI_WHITE;
        }
        if(this.nbrPierre>0){
            name+=this.nbrPierre+ ColorText.ANSI_BLACK+ "Pierre"+ColorText.ANSI_WHITE;
        }
        return name ;

    }

    /**
     * return points win if you choose a building
     */
    public int getEarnedPoint() {
        return earnedPoint;
    }

    public int getNbrOr() {
        return nbrOr;
    }

    public int getNbrArgile() {
        return nbrArgile;
    }

    public int getNbrPierre() {
        return nbrPierre;
    }

    public int getNbrBois() {
        return nbrBois;
    }

    /**
     * return the type of zone
     */
    public TypeArea getTypeArea() {
        return typeArea;
    }

    /**
     * the robot can choose a resource field or a building
     */
    public abstract boolean addChoiceToRobot(Areas fieldChoice, Robot robot, int numberFigure , int idField, ArrayList<Areas> listOfAreas, ArrayList<Areas> listFieldChoice, ArrayList <Areas> listNoCapacityFields);






}
