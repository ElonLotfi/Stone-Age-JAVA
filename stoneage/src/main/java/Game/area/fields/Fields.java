package Game.area.fields;

import Game.area.Areas;
import Game.resources.TypeResource;
import Game.robots.Robot;

import java.util.ArrayList;

public class Fields extends Areas {

    public Fields(String name, TypeResource typeResource, int figureCapacity, int nbrResource, int tax) {
        super(name, typeResource, figureCapacity, nbrResource, tax);
    }

    public Fields(String name, int figureCapacity) {
        super(name, figureCapacity);
    }

    /**
     * the robot can choose a resource field
     */
    public boolean addChoiceToRobot(Areas fieldChoice, Robot robot, int numberFigure ,int idField, ArrayList<Areas> listOfAreas,ArrayList<Areas> listFieldChoice,ArrayList <Areas> listNoCapacityFields ){

            robot.addChosenFieldsResource(fieldChoice,numberFigure);
            robot.setFigures(robot.getFigures()-numberFigure);
            Areas zoneChoosenByRobot = null;
            for(int i=0;i<listOfAreas.size();i++){
                if(listOfAreas.get(i).getName().equals(listFieldChoice.get(idField).getName())){
                    zoneChoosenByRobot = listOfAreas.get(i);
                    zoneChoosenByRobot.setFigureCapacity(zoneChoosenByRobot.getFigureCapacity()-numberFigure);
                }
            }
            if(zoneChoosenByRobot.getFigureCapacity()<=0){
                listNoCapacityFields.add(listFieldChoice.get(idField));
                listOfAreas.remove(zoneChoosenByRobot);
            }





            return true;



        }



}
