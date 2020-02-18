package Game;


import Game.area.TypeArea;
import Game.engine.GameEngine;
import Game.resources.TypeResource;
import Game.robots.Robot;
import org.junit.jupiter.api.Test;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class GameEngineTest{

    private GameEngine gameEngine;

    /**
     * check the winner: the one with the highest score is the winner
     */
    @Test
    public void updateWinner(){

        gameEngine = new GameEngine(4);
        gameEngine.setLevel(Level.FINE);
        gameEngine.getRobots()[0].setScore(123);
        gameEngine.getRobots()[1].setScore(1122);
        gameEngine.getRobots()[2].setScore(179);
        gameEngine.getRobots()[3].setScore(22);
        gameEngine.updateWinner();
        assertEquals(1,gameEngine.getIdWinner());

    }


    /**
     * in the event of a tie between two robots, the one with the highest level of farming is the winner
     */
    @Test
    public void updateWinnerCaseTie(){

        gameEngine = new GameEngine(4);
        gameEngine.setLevel(Level.FINE);
        int idWinner = 2;
        gameEngine.getRobots()[0].setScore(-1);
        gameEngine.getRobots()[1].setScore(0);
        gameEngine.getRobots()[2].setScore(1);
        gameEngine.getRobots()[3].setScore(1);
        gameEngine.getRobots()[0].setAgricultureLevel(0);
        gameEngine.getRobots()[1].setAgricultureLevel(0);
        gameEngine.getRobots()[2].setAgricultureLevel(10);
        gameEngine.getRobots()[3].setAgricultureLevel(7);
        gameEngine.updateWinner();
        assertEquals(idWinner,gameEngine.getIdWinner());

    }

    /**
     * check if the robot uses all its figures
     */
    @Test
    public void robotUsesAllFigures(){
        // tester si le 3 type de robot a placer tout ses figurines .
        for(int i = 0;i<5000;i++){
            GameEngine gameEngine = new GameEngine(4);
            gameEngine.setLevel(Level.FINE);
            while (gameEngine.getRobots()[1].getFigures() > 0){
                gameEngine.updateRobotChoice(gameEngine.getRobots()[1]);
            }
            assertEquals(true,gameEngine.getRobots()[1].getFigures() == 0);
        }

        for(int i = 0;i<5000;i++){
            GameEngine gameEngine = new GameEngine(4);
            gameEngine.setLevel(Level.FINE);
            while (gameEngine.getRobots()[0].getFigures() > 0){
                gameEngine.updateRobotChoice(gameEngine.getRobots()[0]);
            }
            assertEquals(true,gameEngine.getRobots()[0].getFigures() == 0);
        }
        for(int i = 0;i<5000;i++){
            GameEngine gameEngine = new GameEngine(4);
            gameEngine.setLevel(Level.FINE);
            while (gameEngine.getRobots()[3].getFigures() > 0){
                gameEngine.updateRobotChoice(gameEngine.getRobots()[3]);
            }
            assertEquals(true,gameEngine.getRobots()[3].getFigures() == 0);
        }

    }

    /**
     * check if the robot places all the figures in hunting ("CHASSE")
     */
    @Test
    public void robotPlacesAllInChasse(){
        // tester si le robot peut placer tout ces figurines dans la zone de chasse .
        for(int i =0;i<500;i++){
            GameEngine gameEngine = new GameEngine(4);
                gameEngine.setLevel(Level.FINE);
            int numberFigurePlayer=5;
            Robot robot = gameEngine.getRobots()[3];
            robot.setFigures(numberFigurePlayer);
            gameEngine.getFieldByName("riviere").setFigureCapacity(0);
            gameEngine.getFieldByName("forêt").setFigureCapacity(0);
            gameEngine.getFieldByName("glaisière").setFigureCapacity(0);
            gameEngine.getFieldByName("carrière").setFigureCapacity(0);
            gameEngine.getFieldByName("champ").setFigureCapacity(0);
            gameEngine.getFirstBuildingFromHeap(0).setFigureCapacity(0);
            gameEngine.getFirstBuildingFromHeap(1).setFigureCapacity(0);
            gameEngine.getFirstBuildingFromHeap(2).setFigureCapacity(0);
            gameEngine.getFirstBuildingFromHeap(3).setFigureCapacity(0);
            gameEngine.getFirstBuildingFromHeap(0).setReserved(robot);
            gameEngine.getFirstBuildingFromHeap(1).setReserved(robot);
            gameEngine.getFirstBuildingFromHeap(2).setReserved(robot);
            gameEngine.getFirstBuildingFromHeap(3).setReserved(robot);

            int oldCapacityChasse = gameEngine.getFieldByName("chasse").getFigureCapacity();
            gameEngine.updateRobotChoice(robot);
            assertEquals(0,robot.getFigures());
            assertEquals(true,gameEngine.getFieldByName("chasse").getFigureCapacity()== oldCapacityChasse-numberFigurePlayer);
        }


    }

    /**
     * test if the robot increases its level of agriculture
     */
    @Test
    public void upgradeAgricultureTest(){
        GameEngine gameEngine = new GameEngine(4);
        gameEngine.setLevel(Level.FINE);
        Robot robot = gameEngine.getRobots()[0];
        robot.setFigures(1);
        int oldLevel = robot.getAgricultureLevel();
        gameEngine.getFieldByName("riviere").setFigureCapacity(0);
        gameEngine.getFieldByName("forêt").setFigureCapacity(0);
        gameEngine.getFieldByName("glaisière").setFigureCapacity(0);
        gameEngine.getFieldByName("carrière").setFigureCapacity(0);
        gameEngine.getFieldByName("chasse").setFigureCapacity(0);
        gameEngine.getFirstBuildingFromHeap(0).setFigureCapacity(0);
        gameEngine.getFirstBuildingFromHeap(1).setFigureCapacity(0);
        gameEngine.getFirstBuildingFromHeap(2).setFigureCapacity(0);
        gameEngine.getFirstBuildingFromHeap(3).setFigureCapacity(0);

        while (robot.getFigures() > 0){
            gameEngine.updateRobotChoice(robot);
        }
        gameEngine.updateRobotResource(robot);
        assertEquals(oldLevel + 1,robot.getAgricultureLevel());



    }


    /**
     * test if all the robots we choose a building or a field correctly.
     */
    @Test
    public void robotsChooseArea(){
        // tester si tout les robot on choisi une carte ou un champs correctement .
        GameEngine gameEngine = new GameEngine(4);
        gameEngine.setLevel(Level.FINE);
        boolean executed1 =  gameEngine.updateRobotChoice(gameEngine.getRobots()[0]);
        boolean executed2 =   gameEngine.updateRobotChoice(gameEngine.getRobots()[1]);
        boolean executed3 =   gameEngine.updateRobotChoice(gameEngine.getRobots()[2]);
        boolean executed4 =   gameEngine.updateRobotChoice(gameEngine.getRobots()[3]);
        assertEquals(true,gameEngine.getRobots()[0].getFigures() < gameEngine.getRobots()[0].getInitFigure());
        assertEquals(true,gameEngine.getRobots()[1].getFigures() < gameEngine.getRobots()[1].getInitFigure());
        assertEquals(true,gameEngine.getRobots()[2].getFigures() < gameEngine.getRobots()[2].getInitFigure());
        assertEquals(true,gameEngine.getRobots()[3].getFigures() < gameEngine.getRobots()[3].getInitFigure());
        assertEquals(true,executed1);
        assertEquals(true,executed2);
        assertEquals(true,executed3);
        assertEquals(true,executed4);

    }

    /**
     * test if the HunterBot chose the food field ("Chasse").
     */
    @Test
    public void HunterChooseOnlyChasse(){
        GameEngine gameEngine = new GameEngine(4);
        gameEngine.setLevel(Level.FINE);
        gameEngine.updateRobotChoice(gameEngine.getRobots()[1]);
        String areaName = gameEngine.getTmpAreas().getName();
        assertEquals("HunterBot",gameEngine.getRobots()[1].getName());
        assertEquals("chasse",areaName);
        assertEquals(true,gameEngine.getRobots()[1].getFigures() == 0);
    }

    /**
     * test if the SmartBot chooses the fields only 10 times during the games.
     */
    @Test

    public void WhenSmartBotUpgradeAgricultureLevel(){
        GameEngine gameEngine = new GameEngine(4);
        gameEngine.setLevel(Level.FINE);
        for(int i = 0;i<1000;i++) {

            int cpt = 0;
            int AgricultureLevel =10;
            Robot smartBot = gameEngine.getRobots()[0];
            smartBot.setAgricultureLevel(AgricultureLevel);
            while (smartBot.getFigures() > 0) {
                gameEngine.updateRobotChoice(smartBot);
                if (gameEngine.getTmpAreas().getName().equals("champ")) {
                    cpt += 1;
                }
            }

            assertEquals("SmartBot", smartBot.getName());
            assertEquals(0, cpt);
        }
    }

    /**
     * check if smartbot chooses a building if it has its price
     */
    @Test
    public void WhenSmartBotChooseBuildingCard(){
        GameEngine gameEngine = new GameEngine(4);
        gameEngine.setLevel(Level.FINE);
        for(int i = 0;i<1000;i++) {

            int cpt = 0;
            Robot smartBot = gameEngine.getRobots()[0];
            smartBot.getResources().updateResource(TypeResource.Bois,0);
            smartBot.getResources().updateResource(TypeResource.Argile,0);
            smartBot.getResources().updateResource(TypeResource.Pierre,0);
            smartBot.getResources().updateResource(TypeResource.Or,0);

            while (smartBot.getFigures() > 0) {
                gameEngine.updateRobotChoice(smartBot);
                if (gameEngine.getTmpAreas().getTypeArea().equals(TypeArea.BuildingCard)) {
                    cpt += 1;
                }
            }

            assertEquals("SmartBot", smartBot.getName());
            assertEquals(0, cpt);
        }
    }










}
