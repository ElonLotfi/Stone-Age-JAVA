package Game.robots;

import Game.area.Areas;

import java.util.ArrayList;

public class HunterBot extends Robot {
    public HunterBot(int id) {
        super(id, "HunterBot", 5);
    }



    /**
     * to force the robot to choose the hunting resource field
     */
    @Override
    public int chooseField(ArrayList<Areas> areas) {
        int idChasse = 0;
        for(int i=0;i<areas.size();i++){
            if(areas.get(i).getName().equals("chasse")){
                idChasse = i ;
                break;
            }
        }
        return idChasse;
    }
    /**
     *  return the number of figures used
     */
    @Override
    public int chooseNumberUsedFigures(ArrayList<Areas> areas) {
        return this.getFigures();
    }
}
