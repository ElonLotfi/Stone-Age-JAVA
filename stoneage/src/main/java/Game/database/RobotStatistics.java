package Game.database;




public class RobotStatistics {

    private int score ;
    private int wonGames;
    private int nbrLosses;




    public RobotStatistics(){
        this.score = 0;
        this.wonGames = 0;
        this.nbrLosses = 0;
    }

    /**
     * update robot score
     */
    public void updateScore(int score){
        this.score += score;
    }

    /**
     * update the number of robot win games
     */
    public void setWonGames(){
        this.wonGames += 1;
    }

    /**
     * return total robot score
     */
    public int getTotalScore() {
        return score;
    }

    /**
     * return the number of games won
     */
    public int getWonGames() {
        return wonGames;
    }

    /**
     * return the number of game lost
     */
    public int getNbrLosses() {
        return nbrLosses;
    }

    /**
     * update the number of lost games
     */
    public void setNbrLosses() {
        this.nbrLosses += 1;
    }








}
