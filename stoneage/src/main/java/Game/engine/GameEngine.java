package Game.engine;

import Game.area.*;
import Game.area.building.BuildingCard;
import Game.area.building.FactoryBuilding;
import Game.area.fields.FactoryFields;
import Game.dice.Dice;
import Game.robots.HunterBot;
import Game.robots.SmartBot;
import Game.robots.RandomBot;
import Game.robots.Robot;
import Game.useful.ColorText;
import Game.useful.GameMessage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GameEngine {

    private Robot[] robots ;
    private Dice dice;
    private int numberRobots;
    private ArrayList<Areas> listOfAreas ;
    private ArrayList<Areas> listNoCapacityFields;
    private int idWinner ;
    private int nbrUsedFigure ;
    private static Logger loggerGameEngine = Logger.getLogger(GameEngine.class.getName());
    private Level level;
    private int nbForbidden=0;
    private ArrayList<Areas> factoryFields;
    private FactoryBuilding factoryBuilding;
    private ArrayList<Heap> heaps;
    private boolean gameFinished ;
    private Areas tmpAreas ;



    public GameEngine(int numberRobots){
        this.gameFinished = false;
        this.idWinner = 0 ;
        this.numberRobots = numberRobots;
        level = Level.FINE;
        initGame();
        initRobot(numberRobots);

    }

    /**
     * initialize the game : Robots and Fields .
     */
    public void initGame(){
        initHeap();
        initAreas();
    }

    /**
     * initialize robots.
     * @param numberRobots the number of robots in the game
     */


    private void initRobot (int numberRobots){
        robots = new Robot[numberRobots];
        robots[0] = new SmartBot(0);
        robots[1] = new HunterBot(1);
        robots[2] = new RandomBot(2);
        robots[3] = new SmartBot(3);
        for(int i = 0;i<this.robots.length;i++){
            robots[i].setLevel(Level.INFO);
        }


    }

    /**
     * initialize building stacks
     */
    private void initHeap(){
        this.factoryBuilding = new FactoryBuilding();
        this.heaps =new ArrayList<>();
        this.heaps.add(factoryBuilding.getHeapA());
        this.heaps.add(factoryBuilding.getHeapB());
        this.heaps.add(factoryBuilding.getHeapC());
        this.heaps.add(factoryBuilding.getHeapD());
    }


    /**
     * flip the first building of a building stack
     */
    public BuildingCard getFirstBuildingFromHeap(int HeapId){
        for(int i = 0 ;i<this.heaps.size();i++){
            if(HeapId == this.heaps.get(i).getHeapId()){
                return this.heaps.get(i).getFirstBuilding();
            }
        }
        return null;
    }


    /**
     * return the stack id where the buildings exist
     */
    public int buildingExistHeap(BuildingCard buildingCard){
        for(int i = 0;i<this.heaps.size();i++){
            if(this.heaps.get(i).isEmpty() == false){
                if(this.heaps.get(i).getFirstBuilding().equals(buildingCard)){
                    return i;
                }
            }
        }
        return -1;
    }


    /**
     * initialize Areas .
     */
    private ArrayList<Areas> initAreas(){
        factoryFields = FactoryFields.initFields();
        listOfAreas = new ArrayList<>();
        listOfAreas.addAll(factoryFields);
        if(getFirstBuildingFromHeap(0) != null){
            listOfAreas.add(getFirstBuildingFromHeap(0)); // premier carte de batiement de pile A

        }
        if(getFirstBuildingFromHeap(1) != null){
            listOfAreas.add(getFirstBuildingFromHeap(1)); // premier carte de batiement de pile A

        }  if(getFirstBuildingFromHeap(2) != null){
            listOfAreas.add(getFirstBuildingFromHeap(2)); // premier carte de batiement de pile A

        }  if(getFirstBuildingFromHeap(3) != null){
            listOfAreas.add(getFirstBuildingFromHeap(3)); // premier carte de batiement de pile A

        }
        listNoCapacityFields = new ArrayList<>();



        return listOfAreas ;
    }


    /**
     * update the resource number of a field
     * @param areas the targeted field
     * @param number the number of resources to remove
     */
    public void updateFieldsResource(Areas areas, int number){
        areas.updateNbrResource(number);
    }

    /**
     * to display the area with the number of resources remaining
     * @return string .
     */
    public String displayFields(){
        String display = "";
        for(int i = 0 ;i<this.listOfAreas.size();i++){
            display += ColorText.ANSI_BLUE + listOfAreas.get(i).getName() + " => " + ColorText.ANSI_RED + listOfAreas.get(i).getNbrResource() + " " + ColorText.ANSI_BLUE + listOfAreas.get(i).getTypeResource() + "\n" + ColorText.ANSI_WHITE;
        }
        return display;
    }

    /**
     * check if a building stack is empty, if it is, the game is over
     */
    public Boolean gameFinished() {
        for (int i = 0; i < this.heaps.size(); i++) {
            if (this.heaps.get(i).getBuildingList().size() == 0) {
                this.gameFinished = true;
                return true;
            }
        }
        return false;
    }

    /**
     *  the robot can buy a building card
     */
    public void buyBuildingCard(Robot robot){
        if(robot.getReservedBuilding().size()> 0 ){
            for(int i = 0 ;i<robot.getReservedBuilding().size() ;i++){
                if(robot.buyBuilding(robot.getReservedBuilding().get(i))){
                    int indexHeap = buildingExistHeap(robot.getReservedBuilding().get(i));
                    this.heaps.get(indexHeap).removeBuilding(robot.getReservedBuilding().get(i));
                    loggerGameEngine.log(level, GameMessage.messageWhenRobotBuyBuilding(robot,robot.getReservedBuilding().get(i)));
                    loggerGameEngine.log(level, GameMessage.messageWhenGetPoint(robot,robot.getReservedBuilding().get(i).getEarnedPoint()));
                    robot.getReservedBuilding().remove(robot.getReservedBuilding().get(i));


                }
            }
        }
    }



    /**
     * the robot can choose a resource field or it can reserve a building
     * @param robot ...
     */
    public boolean updateRobotChoice(Robot robot) {


        int idField = 0;
        // On recupère tous les noms des zones qui ont déjà été choisi par le joueur

        ArrayList<String> listFieldForbidden= new ArrayList<>();
        if(robot.getChosenFieldsResource().size()>0){
            for(String field : robot.getChosenFieldsResource().keySet()){
                listFieldForbidden.add(field);
            }
        }
        // On recupère tous les noms des batiments qui ont déjà été choisi par les joueurs

        for(int i = 0;i<this.heaps.size();i++){
            if(getFirstBuildingFromHeap(i).getReserved() != null){
                listFieldForbidden.add(getFirstBuildingFromHeap(i).getName());
            }


        }

        // On copie la liste des champs disponibles dans le jeu  // ca peut rester comme elle est !(pour les batiment)
        ArrayList<Areas> listFieldChoice = new ArrayList<>();
        for(int i=0;i<listOfAreas.size();i++){
            if(listOfAreas.get(i) instanceof BuildingCard){
                BuildingCard buildingCard = (BuildingCard)listOfAreas.get(i);
                if(buildingCard.getReserved()==null){
                    listFieldChoice.add(listOfAreas.get(i));
                }
            }else{
                if(listOfAreas.get(i).getFigureCapacity()>0){
                    listFieldChoice.add(listOfAreas.get(i));
                }
            }
        }

        // On supprime tout les zones qui ont déjà été choisi par le joueur dans le même tour
        for(int j=0;j<listFieldForbidden.size();j++){
            for(int i=0;i<listFieldChoice.size();i++){
                if(listFieldChoice.get(i).getName().equals(listFieldForbidden.get(j))){
                    listFieldChoice.remove(listFieldChoice.get(i));
                }
            }

        }

        if(listFieldChoice.size()==0){
            nbForbidden+=1;
            return true;
        }


        // On place les figurines
        idField = robot.chooseField(listFieldChoice);
        int capacityField = listFieldChoice.get(idField).getFigureCapacity();

        int numberFigure = 0;
        // forcer a utiliser le reste de figurines dans la chasse !
        if(listFieldChoice.size()==1 && getFieldByName("chasse")!=null || listFieldChoice.get(0).getName().equals("chasse")) {
            numberFigure = robot.getFigures();

        }else{
            // ici j'utilise un nombre entre 1 et la
            numberFigure = robot.chooseNumberUsedFigures(listFieldChoice);
            numberFigure = Math.min(numberFigure,capacityField);

        }

        Areas fieldChoice = listFieldChoice.get(idField);
        tmpAreas = fieldChoice;

        if(fieldChoice.addChoiceToRobot(fieldChoice,robot,numberFigure ,idField, listOfAreas, listFieldChoice, listNoCapacityFields) == true){

            if(fieldChoice.getTypeArea() == TypeArea.Fields){
                loggerGameEngine.log(level, GameMessage.messageWhenFieldChoosen(robot,fieldChoice,numberFigure));
                nbrUsedFigure+=numberFigure;
                return true;

            }
            if(fieldChoice.getTypeArea()== TypeArea.BuildingCard){
                loggerGameEngine.log(level, GameMessage.messageWhenBuildingChoosen(robot, fieldChoice, numberFigure));
                nbrUsedFigure+=numberFigure;
                return true;
            }

        }




        return false;



    }





    /**
     * update the Robot resource .
     * @param robot
     */
    public void updateRobotResource(Robot robot){

        for (String field : robot.getChosenFieldsResource().keySet()) {

            if(field.equals("champ")){
                robot.upgradeAgriculture();
                loggerGameEngine.log(level,GameMessage.messageWhenUpgradeAgriculture(robot));
            }
            else {

                dice = new Dice(robot.getChosenFieldsResource().get(field));
                robot.setResources(dice.getFace(), getFieldByName(field));
                if (robot.getResourceAdded() > 0) {
                    loggerGameEngine.log(level, GameMessage.messageWhenRobotGetResource(robot, getFieldByName(field)));

                }
                updateFieldsResource(getFieldByName(field), robot.getResourceAdded());

            }


        }
    }

    /**
     * return a resource field or a building using its name
     */
    public Areas getFieldByName(String fieldName){
        for(int i=0;i< listOfAreas.size();i++){
            if(listOfAreas.get(i).getName().equals(fieldName)){
                return listOfAreas.get(i);
            }
        }
        return null;
    }

    /**
     * look for the winner
     */
    public void updateWinner(){
        int value = -1000;
        int agricultureLevel = -1 ;
        int id = 0 ;
        int idLevel = 0;

        ArrayList<Robot> robot = new ArrayList<>();
        for (int i = 0 ;i<this.robots.length;i++){
            if(this.robots[i].getScore() > value){
                value = this.robots[i].getScore() ;
                id = this.robots[i].getRobotId();

            }
        }

        for(int i =0;i<this.robots.length;i++){
            if(this.robots[i].getScore() == value){
                robot.add(this.robots[i]);
            }
        }
        if(robot.size()>=2){
            for(int i = 0;i<robot.size();i++){
                if(robot.get(i).getAgricultureLevel() > agricultureLevel){
                    agricultureLevel = robot.get(i).getAgricultureLevel();
                    idLevel = robot.get(i).getRobotId();
                }
            }
            this.idWinner = idLevel;
        }else{
            this.idWinner = id;
        }





        loggerGameEngine.log(level,GameMessage.messageWhenGameEnd(this.robots,this.idWinner ));





    }


    /**
     * the robot can only make one choice: choose a field or a building
     */
    public void updateOneTurn(){

        for(int i=0;i< robots.length;i++){
            if (robots[i].getFigures() > 0) {
                updateRobotChoice(robots[i]);
            }


        }
    }

    /**
     * update the game by updating the robot choices and updating its resources
     */
    public void updateGame(){


        loggerGameEngine.log(level,GameMessage.messageActionPoseFigures());

        int allSumFigures=0;

        for(int i=0;i< robots.length;i++){
            allSumFigures+= robots[i].getFigures();  // je recupere toute les figurines des robots
        }


        while(nbrUsedFigure < allSumFigures  ){
            if(nbrUsedFigure==allSumFigures-1) break;
            if(nbForbidden>=4) {break;}
            updateOneTurn();
        }


        initAreas();
        nbForbidden=0;
        loggerGameEngine.log(level,GameMessage.messageActionRealizeAction());

        for(int i = 0;i<this.numberRobots;i++){
            updateRobotResource(robots[i]);
        }

        loggerGameEngine.log(level,GameMessage.simpleMessage());
        for(int i = 0;i<this.numberRobots;i++){
            buyBuildingCard(this.robots[i]);
        }
        loggerGameEngine.log(level,GameMessage.messageActionFeed());

        for(int i = 0;i<this.numberRobots;i++){
            robots[i].feedFigures();
        }
        nbrUsedFigure=0;
        for (int i = 0 ; i<this.robots.length;i++){
            this.robots[i].resetFigures();
            this.robots[i].getChosenFieldsResource().clear();
            this.robots[i].resetReservedBuilding();
        }
        gameFinished();



    }

    /**
     * start the game and finish it when a building stack is empty
     */
    public void miniGame(){
        int round = 0;
        loggerGameEngine.log(level,GameMessage.messageWhenGameStart(this.numberRobots));
        loggerGameEngine.log(level,GameMessage.simpleMessage());

        while (gameFinished == false){
            loggerGameEngine.log(level,GameMessage.messageWhenRoundStart(round));
            updateGame();
            loggerGameEngine.log(level,GameMessage.simpleMessage());
            round += 1;

        }
        updateWinner();



    }


    /**
     * to start the Game .
     */
    public void startGame(){
        miniGame();
    }

    /**
     * to get the list of Robot in the game .
     * @return
     */
    public Robot[] getRobots() {
        return robots;
    }
    public int getIdWinner() {
        return idWinner;
    }

    /**
     * to make the display visible or hidden
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    public ArrayList<Areas> getListFields() {
        return listOfAreas;
    }


    public FactoryBuilding getFactoryBuilding() {
        return factoryBuilding;
    }
    public boolean isGameFinished() {
        return gameFinished;
    }

    public ArrayList<Heap> getHeaps() {
        return heaps;
    }


    /**
     * return a resource field or a building:
     * this method is used for testing
     */
    public Areas getTmpAreas() {
        return tmpAreas;
    }

}















