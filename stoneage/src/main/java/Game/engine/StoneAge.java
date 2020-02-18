package Game.engine;


import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

public class StoneAge {


    public static void main(String[] args)  {

        int nbPlayer=4;
        if(args.length>=1){
            nbPlayer=Integer.parseInt(args[0]);
        }


        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        GameEngine gameEngine = new GameEngine(nbPlayer);
        gameEngine.setLevel(Level.INFO);
        gameEngine.startGame();





    }
}
