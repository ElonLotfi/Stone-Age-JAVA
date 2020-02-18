package Game.engine;


import Game.database.StatisticEngine;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class StoneAgeStat {





    public static void main(String[] args)  {

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StatisticEngine statisticEngine = new StatisticEngine(500,4);
        statisticEngine.startStat();



    }
}
