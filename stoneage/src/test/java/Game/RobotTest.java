package Game;

import Game.area.building.BuildingCard;
import Game.engine.GameEngine;
import Game.resources.TypeResource;
import Game.robots.RandomBot;
import Game.robots.Robot;
import Game.robots.SmartBot;
import org.junit.jupiter.api.Test;
import java.util.logging.Level;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RobotTest {

    /**
     * check if the robot earns points if it buys buildings
     */
    @Test
    public void updateScore(){
        Robot robot = new RandomBot(0);
        robot.setLevel(Level.FINE);
        robot.getResources().updateResource(TypeResource.Bois,14);
        robot.getResources().updateResource(TypeResource.Argile,14);
        robot.getResources().updateResource(TypeResource.Pierre,14);
        robot.getResources().updateResource(TypeResource.Or,14);
        BuildingCard card1 = new  BuildingCard(6,1,0,2,0,16,null);
        BuildingCard card2 = new  BuildingCard(7,1,0,2,0,13,null);
        BuildingCard card3 = new  BuildingCard(8,1,0,2,0,14,null);
        int sumEarnedPoints = card1.getEarnedPoint() + card2.getEarnedPoint() + card3.getEarnedPoint();
        robot.buyBuilding(card1);
        robot.buyBuilding(card2);
        robot.buyBuilding(card3);

        assertEquals(sumEarnedPoints,robot.getScore());

    }

    /**
     * test if the robot can feed all its figures.
     */
    @Test
    public void feedFiguresTest(){
        SmartBot smartBot = new SmartBot (0);
        smartBot.setInitFigure(10);
        smartBot.setLevel(Level.FINE);
        int score = smartBot.getScore();
        smartBot.getResources().updateResource(TypeResource.Nourriture,10);
        smartBot.feedFigures();
        assertEquals(true,smartBot.getScore() == score);

        RandomBot randomBot = new RandomBot (0);
        randomBot.setInitFigure(10);
        smartBot.setLevel(Level.FINE);
        score = randomBot.getScore();
        randomBot.getResources().updateResource(TypeResource.Nourriture,10);
        randomBot.feedFigures();
        assertEquals(true,randomBot.getScore() == score);

    }

    /**
     * test if the robot can feed all the figurines in the absence of a Food-type Resource.
     */
    @Test
    public void feedFiguresFromOtherResources(){
        RandomBot randomBot = new RandomBot(1);
        randomBot.setLevel(Level.FINE);
        int score = randomBot.getScore();
        randomBot.getResources().updateResource(TypeResource.Bois,10);
        randomBot.getResources().updateResource(TypeResource.Argile,12);
        randomBot.getResources().updateResource(TypeResource.Or,13);
        randomBot.getResources().updateResource(TypeResource.Nourriture,0);
        randomBot.getResources().updateResource(TypeResource.Pierre,14);
        randomBot.feedFigures();
        assertEquals(true,randomBot.getScore() == score);

    }


    /**
     * test if the robot loses 10 points in case of insufficient resources!
     */
    @Test
    public void robotLostPoints(){
        RandomBot randomBot = new RandomBot(1);
        randomBot.setLevel(Level.FINE);
        int score = randomBot.getScore();
        randomBot.getResources().updateResource(TypeResource.Bois,0);
        randomBot.getResources().updateResource(TypeResource.Argile,0);
        randomBot.getResources().updateResource(TypeResource.Or,0);
        randomBot.getResources().updateResource(TypeResource.Nourriture,-11); //=> 1 nourriture
        randomBot.getResources().updateResource(TypeResource.Pierre,0);
        randomBot.feedFigures();
        assertEquals(true,randomBot.getScore() == score - 10);

    }

    /**
     * test if the robot refund resources to the reserve
     */
    @Test
    public void refundResources(){
        RandomBot randomBot = new RandomBot(1);
        randomBot.setLevel(Level.FINE);
        int oldCapacity = 1;
        randomBot.getResources().updateResource(TypeResource.Bois,oldCapacity);
        randomBot.getResources().updateResource(TypeResource.Argile,oldCapacity);
        randomBot.getResources().updateResource(TypeResource.Or,oldCapacity);
        randomBot.getResources().updateResource(TypeResource.Nourriture,-11); // => 1 nourriture
        randomBot.getResources().updateResource(TypeResource.Pierre,0);
        randomBot.feedFigures();
        assertEquals(oldCapacity,randomBot.getResources().nbrResourceViaType(TypeResource.Bois));
        assertEquals(oldCapacity,randomBot.getResources().nbrResourceViaType(TypeResource.Argile));
        assertEquals(oldCapacity,randomBot.getResources().nbrResourceViaType(TypeResource.Or));
        assertEquals(oldCapacity,randomBot.getResources().nbrResourceViaType(TypeResource.Nourriture));

    }

    /**
     * test if the robot can feed all the figurines in the absence of a Food-type Resource.
     */
    @Test
    public void feedFiguresFromOtherResouce(){
        RandomBot randomBot = new RandomBot(1);
        randomBot.setLevel(Level.FINE);
        int oldCapacity = 20;
        randomBot.getResources().updateResource(TypeResource.Bois,oldCapacity);
        randomBot.getResources().updateResource(TypeResource.Nourriture,-12); // => 0 nourriture
        randomBot.feedFigures();
        assertEquals(oldCapacity -  randomBot.getFigures(),randomBot.getResources().nbrResourceViaType(TypeResource.Bois));


    }

    /**
     * test if the robot can buy a building.
     */
    @Test

    public void buildingsPurchasedTest(){
        // tester si le robot peut acheter un batiment .
        RandomBot randomBot = new RandomBot(1);
        randomBot.setLevel(Level.FINE);
        int value = 1;
        BuildingCard buildingCard = new BuildingCard(13,1,0,1,1,14,null);
        randomBot.getResources().updateResource(TypeResource.Bois,value);
        randomBot.getResources().updateResource(TypeResource.Argile,value);
        randomBot.getResources().updateResource(TypeResource.Or,value);
        randomBot.getResources().updateResource(TypeResource.Pierre,value);
        boolean buy = randomBot.buyBuilding(buildingCard);
        int buildingsPurchased = randomBot.getBuildingList().size();
        assertEquals(true,buy);
        assertEquals(1,buildingsPurchased);
        assertEquals(value-buildingCard.getNbrBois(),randomBot.getResources().nbrResourceViaType(TypeResource.Bois));
        assertEquals(value-buildingCard.getNbrArgile(),randomBot.getResources().nbrResourceViaType(TypeResource.Argile));
        assertEquals(value-buildingCard.getNbrOr(),randomBot.getResources().nbrResourceViaType(TypeResource.Or));
        assertEquals(value-buildingCard.getNbrPierre(),randomBot.getResources().nbrResourceViaType(TypeResource.Pierre));
    }


    /**
     *test if the robot reserves a building correctly
     */
    @Test

    public void buildingsReservation(){
        GameEngine gameEngine = new GameEngine(4);
        gameEngine.setLevel(Level.FINE);
        int numberFigurePlayer=1;
        gameEngine.getRobots()[0].setFigures(numberFigurePlayer);
        gameEngine.getFieldByName("riviere").setFigureCapacity(0);
        gameEngine.getFieldByName("forêt").setFigureCapacity(0);
        gameEngine.getFieldByName("glaisière").setFigureCapacity(0);
        gameEngine.getFieldByName("carrière").setFigureCapacity(0);
        gameEngine.getFieldByName("champ").setFigureCapacity(0);
        gameEngine.getFieldByName("chasse").setFigureCapacity(0);
        gameEngine.getFirstBuildingFromHeap(0).setFigureCapacity(0);
        gameEngine.getFirstBuildingFromHeap(1).setFigureCapacity(1);
        gameEngine.getFirstBuildingFromHeap(2).setFigureCapacity(0);
        gameEngine.getFirstBuildingFromHeap(3).setFigureCapacity(0);
        while (gameEngine.getRobots()[0].getFigures() != 0) {
            gameEngine.updateRobotChoice(gameEngine.getRobots()[0]);
        }
        assertEquals(true,gameEngine.getRobots()[0].getReservedBuilding().get(0).getReserved().equals(gameEngine.getRobots()[0]));
        assertEquals(true,gameEngine.getFirstBuildingFromHeap(1).getReserved() != null);
        assertEquals(0,gameEngine.getRobots()[0].getFigures());


    }




}
