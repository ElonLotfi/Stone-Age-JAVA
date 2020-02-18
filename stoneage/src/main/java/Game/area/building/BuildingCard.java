package Game.area.building;

import Game.area.Areas;
import Game.robots.Robot;

import java.util.ArrayList;

public class BuildingCard extends Areas {
    public BuildingCard(int idBuilding, int nbrOr, int nbrArgile, int nbrPierre, int nbrBois, int earnedPoint, Robot reserved) {
        super(idBuilding, nbrOr, nbrArgile, nbrPierre, nbrBois, earnedPoint, reserved);
    }

    /**
     * the robot can reserve a building
     */

    @Override
    public boolean addChoiceToRobot(Areas choice, Robot robot, int numberFigure, int idField, ArrayList<Areas> listOfAreas, ArrayList<Areas> listFieldChoice, ArrayList<Areas> listNoCapacityFields) {
        if (choice.getReserved() == null) {
            choice.setReserved(robot);
            robot.addReservedBuilding((BuildingCard) choice);
            robot.setFigures(robot.getFigures() - numberFigure);
            return true;

        }
        return false;

    }


}
