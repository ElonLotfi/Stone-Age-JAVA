package Game.database;


import java.util.HashMap;

public  class DataBase {
    private HashMap<Integer,RobotStatistics> data;
    private int nbrGame;

    public DataBase(int nbrRobots,int nbrGame){
        this.data = new HashMap<>();
        initDataBase(nbrRobots);
        this.nbrGame = nbrGame;
    }

    /**
     *
     *  initialize the database
     */
    public void initDataBase(int nbrRobots){
        for (int i = 0 ;i<nbrRobots;i++){
            this.data.put(i,new RobotStatistics());
        }
    }

    /**
     *  update robot score
     */
    public void updateRobotStat(int idRobot,int score){
        this.data.get(idRobot).updateScore(score);
    }

    /**
     * update the winning game counter of a robot
     */

    public void updateWinner(int idRobot){
        this.data.get(idRobot).setWonGames();
    }

    /**
     *
     * @return the database which contains the statistics of each robot
     */
    public HashMap<Integer, RobotStatistics> getData() {
        return data;
    }

    /**
     *
     * @return the number of games .
     */
    public int getNbrGame() {
        return nbrGame;
    }

    /**
     * return the number of games won
     */

    public int getNumberWin(int id){
        return this.data.get(id).getWonGames();
    }

    /**
     * return the number of games lost
     */
    public int getNbrLosses(int id){
        return this.data.get(id).getNbrLosses();
    }

    /**
     * return the average score of each robot
     */
    public double scoreAverage(int id){
        return (double) this.data.get(id).getTotalScore()/nbrGame;
    }




}
