package Game.area.building;

import Game.area.Areas;
import Game.area.Heap;

import java.util.ArrayList;
import java.util.Random;

public class FactoryBuilding {


    private ArrayList<BuildingCard> buildings;
    private Heap heapA = new Heap(0,"heapA",null);
    private Heap heapB = new Heap(1,"heapB",null);
    private Heap heapC = new Heap(2,"heapC",null);
    private Heap heapD = new Heap(3,"heapD",null);
    private Random chooseBuilding = new Random();

    public FactoryBuilding() {
        initBuildings();
        initHeap();
    }

    /**
     *
     * Initialize the list of buildings
     */
    public ArrayList<BuildingCard> initBuildings(){
        // cette list contient qu'un seul type de batiment
        this.buildings = new ArrayList<>();
        buildings.add(new BuildingCard(0,0,1,1,1,12,null));
        buildings.add(new BuildingCard(1,0,1,1,1,12,null));
        buildings.add(new BuildingCard(2,0,2,0,1,11,null));
        buildings.add(new BuildingCard(3,1,0,0,2,12,null));
        buildings.add(new BuildingCard(4,0,0,1,2,11,null));
        buildings.add(new BuildingCard(5,0,1,0,2,10,null));
        buildings.add(new BuildingCard(6,1,0,2,0,16,null));
        buildings.add(new BuildingCard(7,1,1,1,0,15,null));
        buildings.add(new BuildingCard(8,1,1,1,0,15,null));
        buildings.add(new BuildingCard(9,0,1,2,0,14,null));
        buildings.add(new BuildingCard(10,1,2,0,0,10,null));
        buildings.add(new BuildingCard(11,0,2,1,0,13,null));
        buildings.add(new BuildingCard(12,1,0,1,1,14,null));
        buildings.add(new BuildingCard(13,1,0,1,1,14,null));
        buildings.add(new BuildingCard(14,0,0,2,1,13,null));
        buildings.add(new BuildingCard(15,1,1,0,1,13,null));
        buildings.add(new BuildingCard(16,1,1,0,1,13,null));
        buildings.add(new BuildingCard(17,1,1,0,1,13,null)); // replique
        buildings.add(new BuildingCard(18,1,1,0,1,13,null)); // replique
        buildings.add(new BuildingCard(19,1,1,0,1,13,null)); // replique
        return buildings;
    }

    /**
     * randomly fill a stack of buildings
     * @param heap stack of buildings
     * @param nbrBuildings Total number of buildings
     */
    private void heapUp(Heap heap,int nbrBuildings){
        for (int i = 0 ;i<nbrBuildings;i++){
            int random = chooseBuilding.nextInt(this.buildings.size());
            heap.pushBuilding(this.buildings.get(random));
            this.buildings.remove(random);
        }
    }


    /**
     * initialize and fill all building stacks
     */
    public void initHeap() {

        int nbrBuildings = this.buildings.size() / 4;
        heapUp(this.heapA,nbrBuildings);
        heapUp(this.heapB,nbrBuildings);
        heapUp(this.heapC,nbrBuildings);
        heapUp(this.heapD,nbrBuildings);
    }

    /**
     * flip the first buildings of a stack of buildings
     */
    public Areas getFirstBuilding(Heap heap){
        return heap.getBuildingList().get(0);
    }




    public Heap getHeapA() {
        return heapA;
    }

    public Heap getHeapB() {
        return heapB;
    }

    public Heap getHeapC() {
        return heapC;
    }

    public Heap getHeapD() {
        return heapD;
    }


}
