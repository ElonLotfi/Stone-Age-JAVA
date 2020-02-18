package Game.resources;



import java.util.HashMap;

public class Resources {


    private  HashMap<TypeResource,Integer> resourceTable;




    public Resources(){
        this.resourceTable = new HashMap<>();
        initResource();
    }


    /***
     *initialize the resources of a robot
     */
    private void initResource(){
        this.resourceTable.put(TypeResource.Or,0);
        this.resourceTable.put(TypeResource.Argile,0);
        this.resourceTable.put(TypeResource.Pierre,0);
        this.resourceTable.put(TypeResource.Bois,0);
        this.resourceTable.put(TypeResource.Nourriture,12);

    }

    /***
     * update the resource number of a type
     * @param typeResource the type of resource that will update its value
     * @param value the value we are going to add
     */
    public void updateResource(TypeResource typeResource,int value){
        this.resourceTable.put(typeResource,this.resourceTable.get(typeResource) + value);
    }


    /***
     * method to display the robot resource
     * @return  ...
     */
    @Override
    public String toString() {
        String display = "";
        for (TypeResource typeResource : this.resourceTable.keySet()) {
            display += typeResource + " -> " +  resourceTable.get(typeResource).toString() +"\n";
        }
        return display;
    }

    /**
     * return the number of resources of a specified type
     * @param typeResource
     * @return
     */


    public int nbrResourceViaType(TypeResource typeResource){
        return this.resourceTable.get(typeResource);
    }

    /**
     * return the resource table .
     * @return
     */

    public HashMap<TypeResource, Integer> getResourceTable() {
        return resourceTable;
    }




}
