package Game.robots;




import Game.area.Areas;
import Game.area.TypeArea;

import java.util.ArrayList;
import java.util.Random;

public class RandomBot extends Robot {

    private Random choose = new Random();

    public RandomBot(int id) {
        super(id,"RandomBot",5);
    }






    /**
     * choose a field randomly
     * @param areas : area that exists .
     * @return
     */

    @Override
    public int chooseField(ArrayList<Areas> areas) {
        if(areas.size() == 1 && areas.get(0).getName().equals("chasse")){
            return 0;
        }
        else{
            return choose.nextInt(areas.size());
        }
    }

    /**
     * choose a random number of figures between 1 and the figurines of the player
     */

    @Override
    public int chooseNumberUsedFigures(ArrayList<Areas> areas) {
        if(areas.size() == 1 && areas.get(0).getName().equals("chasse")){
            return this.getFigures();
        }
        if(areas.size() == 1 && areas.get(0).getTypeArea().equals(TypeArea.BuildingCard)){
            return areas.get(0).getFigureCapacity();
        }
        else{

            return choose.nextInt((getFigures()-1)+1)+1;}
    }


}
