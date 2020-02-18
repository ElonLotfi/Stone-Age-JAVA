package Game;

import Game.database.StatisticEngine;
import org.junit.jupiter.api.Test;
import java.util.logging.Level;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticEngineTest {

    /**
     * check if the sum of victory of all the robots equals the number of games
     */
    @Test
    public void sumNumberWins(){
        int nbrGame = 500;
        StatisticEngine statisticEngine = new StatisticEngine(nbrGame,4);
        statisticEngine.setLevel(Level.FINE);
        statisticEngine.startStat();
        int cpt = 0;
        for (int i = 0 ;i<statisticEngine.getRobots().length;i++){
            cpt +=statisticEngine.getNumberWin(i);
        }
        assertEquals(nbrGame,cpt);
    }

    /**
     * check if the sum of loss and win of each robot equals the number of games
     */
    @Test
    public void sumVictoryLoss(){
        int nbrGame = 500;
        StatisticEngine statisticEngine = new StatisticEngine(nbrGame,4);
        statisticEngine.setLevel(Level.FINE);
        statisticEngine.startStat();
        int nbrWin = statisticEngine.getNumberWin(3);
        int nbrLosses = statisticEngine.getNbrLosses(3);
        assertEquals(nbrGame,nbrWin + nbrLosses);

    }
}
