package Game.dice;

import Game.useful.ColorText;

import java.util.Random;

public class Dice {

    private final int nbFace = 6 ;
    private int face ;
    private String setOfValue ;
    private Random generator;

    public Dice(int numberFigurines){
        this.setOfValue = "";
        startDice(numberFigurines);
    }


    /***
     * return the chosen face
     * @return
     */
    public int getFace() {
        return face;
    }

    /***
     *  return the chosen face
     * @param value the number of figurines to place in the fields
     * @return
     */
    public int startDice(int value){
        generator = new Random();
        for(int i = 0 ;i<value;i++ ){
            int result = generator.nextInt(nbFace)+1;
            face +=  result ;
            this.setOfValue += ColorText.ANSI_RED + result + " " + ColorText.ANSI_WHITE;
        }
        return face;
    }

    /***
     * return chosen faces list
     * @return
     */
    public String getSetOfValue() {
        return setOfValue;
    }






}
