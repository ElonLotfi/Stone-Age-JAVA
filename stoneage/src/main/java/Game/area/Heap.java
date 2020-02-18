package Game.area;

import Game.area.building.BuildingCard;
import Game.robots.Robot;
import java.util.Stack;

public class Heap {


    private int heapId ;
    private String name ;
    private Stack<BuildingCard> buildingList ;
    private Robot robot;
    private int size;


    public Heap(int heapId, String name, Robot reserved) {
        this.heapId = heapId;
        this.name = name;
        this.buildingList = new Stack<>();
        this.robot = reserved;
    }


    /**
     * return the list of buildings
     */
    public Stack<BuildingCard> getBuildingList() {
        return buildingList;
    }


    /**
     * add a building to the stack
     */
    public void pushBuilding(BuildingCard buildingCard){
        this.buildingList.push(buildingCard);
    }

    /**
     * remove a building from the stack
     */
    public void removeBuilding(BuildingCard buildingCard){
        if(this.getBuildingList().size() >0){
            this.buildingList.remove(buildingCard);
        }
    }

    /**
     * check if the stack of buildings is empty
     */
    public boolean isEmpty(){
       return this.buildingList.isEmpty();
    }

    /**
     * reserve the stack head of buildings by a robot
     */
    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    /**
     *
     * return the stack id
     */
    public int getHeapId() {
        return heapId;
    }

    /**
     *
     * flip the first buildings of a stack
     */
    public BuildingCard getFirstBuilding(){
        if(this.getBuildingList().isEmpty() == false){
            return this.getBuildingList().peek();

        }
        return null;
    }

    /**
     *
     * @return the size of buildings stack
     */
    public int getSize() {
        return this.buildingList.size();
    }

}
