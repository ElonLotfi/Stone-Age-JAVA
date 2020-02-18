package Game.database;

import Game.engine.GameEngine;
import Game.robots.Robot;
import Game.useful.ColorText;
import Game.useful.GameMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatisticEngine {


    private DataBase dataBase ;
    private int nbrGame ;
    private Robot[] robots;
    private static Logger loggerStatisticEngine = Logger.getLogger(StatisticEngine.class.getName());
    private Level level;





    public StatisticEngine(int nbrGame, int nbrRobot){
        this.nbrGame = nbrGame;
        this.dataBase = new DataBase(nbrRobot,nbrGame);
        this.level = Level.INFO;


    }

    /**
     * start the statistical part
     */
    public void startStat(){
        loggerStatisticEngine.log(level, ColorText.ANSI_BLACK + "Loading ...\n" + ColorText.ANSI_WHITE);
        for(int i = 0;i<nbrGame;i++){
            GameEngine gameEngine = new GameEngine(4);
            gameEngine.setLevel(Level.FINE);
            gameEngine.getRobots()[0].setLevel(Level.FINE);
            gameEngine.getRobots()[1].setLevel(Level.FINE);
            gameEngine.getRobots()[2].setLevel(Level.FINE);
            gameEngine.getRobots()[3].setLevel(Level.FINE);
            gameEngine.startGame();
            this.dataBase.updateWinner(gameEngine.getIdWinner());
            updateLosses(gameEngine.getRobots(),gameEngine.getIdWinner());
            updateAllRobotStat(gameEngine.getRobots());
            getRobots(gameEngine.getRobots());
        }
        displayStat();
    }

    /**
     * update robot statistic
     * @param robot
     */
    public void updateRobotStat(Robot robot){
        dataBase.updateRobotStat(robot.getRobotId(),robot.getScore());
    }

    /**
     * update the statistics of all robots
     * @param robots
     */
    public void updateAllRobotStat(Robot [] robots){
        for(int i = 0;i<robots.length;i++){
            updateRobotStat(robots[i]);
        }
    }

    public void getRobots(Robot[] robots){
        this.robots = robots;
    }

    /**
     * display the results of the statistical part
     */
    public void displayStat(){

        loggerStatisticEngine.log(level,GameMessage.messageStatistic(robots,this.dataBase));

    }


    public void setLevel(Level level) {
        this.level = level;
    }
    /**
     * return the number of games won
     */
    public int getNumberWin(int id){
        return this.dataBase.getNumberWin(id);
    }
    /**
     * return the number of game lost
     */
    public int getNbrLosses(int id){
        return this.dataBase.getNbrLosses(id);
    }
    public Robot[] getRobots() {
        return robots;
    }

    /**
     * update the lost parts of all robots

     */
    public void updateLosses(Robot[] robots,int idWinner){
        for(int i=0;i<robots.length;i++){
            if(robots[i].getRobotId() != idWinner){
             this.dataBase.getData().get(i).setNbrLosses();
            }
        }
    }

}
