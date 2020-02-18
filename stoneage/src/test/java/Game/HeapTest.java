package Game;

import Game.engine.GameEngine;
import Game.resources.TypeResource;
import Game.robots.Robot;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeapTest {

    /**
     * test if a building stack among the 4 is empty; This is the condition for stopping games;
     * test if the game ends correctly.
     */
    @Test

    public void gameFinishedTest(){
        GameEngine gameEngine = new GameEngine(4);
        gameEngine.setLevel(Level.FINE);
        for(int i =0 ;i<gameEngine.getRobots().length;i++){
            gameEngine.getRobots()[i].setLevel(Level.FINE);
        }

        while(gameEngine.gameFinished() == false){
            gameEngine.updateGame();
        }
        Boolean checkEmpty = false;
        for(int i = 0;i<gameEngine.getHeaps().size();i++){
            if(gameEngine.getHeaps().get(i).isEmpty() == true){
                checkEmpty = true;
                break;
            }
        }
        assertEquals(true,checkEmpty);

    }

    /**
     * test if the building is removed after purchase.
     */
    @Test

    public void buildingsRemoveFromHeapTest(){
        GameEngine gameEngine = new GameEngine(4);
        gameEngine.getRobots()[3].setFigures(1);
        //
        int idHeap = 0;
        int nbrBuildings = gameEngine.getHeaps().get(idHeap).getSize();
        Robot robot = gameEngine.getRobots()[3];
        gameEngine.getFieldByName("riviere").setFigureCapacity(0);
        gameEngine.getFieldByName("forêt").setFigureCapacity(0);
        gameEngine.getFieldByName("glaisière").setFigureCapacity(0);
        gameEngine.getFieldByName("carrière").setFigureCapacity(0);
        gameEngine.getFieldByName("chasse").setFigureCapacity(0);
        gameEngine.getFieldByName("champ").setFigureCapacity(0);
        gameEngine.getFirstBuildingFromHeap(0).setFigureCapacity(1);
        gameEngine.getFirstBuildingFromHeap(1).setFigureCapacity(0);
        gameEngine.getFirstBuildingFromHeap(2).setFigureCapacity(0);
        gameEngine.getFirstBuildingFromHeap(3).setFigureCapacity(0);
        robot.getResources().updateResource(TypeResource.Bois,100);
        robot.getResources().updateResource(TypeResource.Argile,100);
        robot.getResources().updateResource(TypeResource.Or,100);
        robot.getResources().updateResource(TypeResource.Pierre,100);

        while (robot.getFigures() > 0){
            gameEngine.updateRobotChoice(robot);
        }
        assertEquals(1,robot.getReservedBuilding().size());
        gameEngine.buyBuildingCard(robot);
        assertEquals(0,robot.getFigures());
        assertEquals(nbrBuildings - 1 ,gameEngine.getHeaps().get(idHeap).getSize());



    }
}
