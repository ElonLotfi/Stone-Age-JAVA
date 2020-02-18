package Game.useful;

import Game.area.Areas;
import Game.area.building.BuildingCard;
import Game.database.DataBase;
import Game.resources.TypeResource;
import Game.robots.Robot;

public class GameMessage {

    private GameMessage(){

    }

    static {
    }



    public static String messageWhenGameStart(int nbrRobot){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append ("The game will begin with "+nbrRobot+" Robots ...\n");
        stringBuffer.append("The game started... !\n");

        return stringBuffer.toString();
    }

    public static String messageWhenFieldChoosen(Robot robot, Areas fields,int value)
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        if(robot.getName().equals("SmartBot")){
            stringBuffer.append(ColorText.ANSI_GREEN + "♨ The " +robot.getName() + ColorText.ANSI_PURPLE+ " "+robot.getRobotId()+ColorText.ANSI_WHITE+" choose to play "+ColorText.ANSI_BLUE + value + ColorText.ANSI_WHITE + " Figures in " +ColorText.ANSI_RED+fields.getName()+"\n" +ColorText.ANSI_WHITE);
        }
        else{
            stringBuffer.append(ColorText.ANSI_RED + "♨ The " +robot.getName() + ColorText.ANSI_PURPLE+ " "+robot.getRobotId()+ColorText.ANSI_WHITE+" choose to play "+ColorText.ANSI_BLUE + value + ColorText.ANSI_WHITE + " Figures in " +ColorText.ANSI_RED+fields.getName()+"\n" +ColorText.ANSI_WHITE);
        }
        return stringBuffer.toString();
    }

    public static String messageGetResource(Robot robot)
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append("The resources of "+ robot.getName() + " " + ColorText.ANSI_GREEN +robot.getRobotId() + ColorText.ANSI_WHITE);
        stringBuffer.append(ColorText.ANSI_CYAN + robot.getResources().toString() + ColorText.ANSI_WHITE);

        return stringBuffer.toString();

    }
    public static String  messageWhenRoundStart(int idRound){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append("\n");
        stringBuffer.append("                   ▁ ▂ ▃ ▄ ▅ ▆ ▇  "+  ColorText.ANSI_BLUE + "Round " + ColorText.ANSI_GREEN + idRound + ColorText.ANSI_WHITE +" █ ▇ ▆ ▅ ▄ ▂ ▁");
        stringBuffer.append("\n");
        stringBuffer.append("\n");

        return stringBuffer.toString();
    }


    public static String messageWhenRobotGetResource(Robot robot,Areas fields){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append(ColorText.ANSI_RED + "► " +robot.getName() + " " + robot.getRobotId() + ColorText.ANSI_PURPLE + " earn " +ColorText.ANSI_YELLOW + robot.getResourceAdded() + " " + ColorText.ANSI_BLUE + fields.getTypeResource().toString() +" ◄ \n"+ColorText.ANSI_WHITE );
        return stringBuffer.toString();
    }
    public static String simpleMessage(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append(ColorText.ANSI_CYAN + "㊈㊉㊊㊋㊌㊍㊎㊏㊐㊑㊒㊓㊔㊕㊖㊗㊈㊉㊊㊋㊌㊍㊎㊏㊐㊑㊒㊓㊔㊕㊖㊗㊈㊉㊊㊋㊌㊍㊎㊏㊐㊑㊒㊓㊔㊕㊖㊗㊕㊖㊗" +ColorText.ANSI_WHITE);
        return stringBuffer.toString() ;
    }
    public static String messageActionPoseFigures(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append(ColorText.ANSI_CYAN + "㊈㊉㊊㊋㊌㊍㊎㊏㊈㊉㊊ "+ColorText.ANSI_BLACK +" Robots pose their figures on the board " + ColorText.ANSI_CYAN+ " ㊈㊉㊊㊋㊌㊍㊎㊏㊐㊑㊒㊓㊔㊕㊖" +ColorText.ANSI_WHITE);
        return stringBuffer.toString() ;
    }

    public static String messageActionRealizeAction(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append(ColorText.ANSI_CYAN + "㊈㊉㊊㊋㊌㊍㊎㊏ "+ColorText.ANSI_BLACK +" The robots realize the actions with the figurines " + ColorText.ANSI_CYAN+ " ㊈㊉㊊㊋㊌㊍㊎㊏㊐㊑㊒㊓㊔" +ColorText.ANSI_WHITE);
        return stringBuffer.toString() ;
    }


    public static String messageActionFeed(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append(ColorText.ANSI_CYAN + "㊈㊉㊊㊋㊌㊍㊎㊏㊐㊑㊒㊓㊔㊕㊖㊗ "+ColorText.ANSI_BLACK +" The robots feed their figures " + ColorText.ANSI_CYAN+ " ㊈㊉㊊㊋㊌㊍㊎㊏㊐㊑㊒㊓㊔㊕㊖㊗" +ColorText.ANSI_WHITE);
        return stringBuffer.toString() ;
    }



    public static String messageWhenFeedFigures(Robot robot,int nbrFigure, TypeResource typeResource){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append(ColorText.ANSI_RED + robot.getName() + " " + robot.getRobotId() + ColorText.ANSI_BLACK + " gives " +typeResource +" for "+ nbrFigure + " figures !" + ColorText.ANSI_WHITE);
        return stringBuffer.toString();
    }
    public static String messageWhenLostPoint(Robot robot,int point){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append(ColorText.ANSI_RED + robot.getName() + " " + robot.getRobotId() + ColorText.ANSI_WHITE + " lost "+ ColorText.ANSI_YELLOW +point + ColorText.ANSI_WHITE +" point!");
        return stringBuffer.toString();
    }


    public static String displayMessage(String string){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append(string);
        return stringBuffer.toString() ;
    }

    public static String messageWhenUpgradeAgriculture(Robot robot){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append(ColorText.ANSI_RED + "► " +robot.getName() + " " + ColorText.ANSI_BLUE +  robot.getRobotId() +ColorText.ANSI_BLACK + " upgrade agriculture level to " + ColorText.ANSI_PURPLE + robot.getAgricultureLevel() + " ... !" + ColorText.ANSI_WHITE);
        return stringBuffer.toString() ;


    }


    public static String messageWhenBuildingChoosen(Robot robot, Areas fields,int value)
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        if(robot.getName().equals("SmartBot")){
            stringBuffer.append(ColorText.ANSI_GREEN + "♨ The " +robot.getName() + ColorText.ANSI_PURPLE+ " "+robot.getRobotId()+ColorText.ANSI_WHITE+" choose to play "+ColorText.ANSI_BLUE + value + ColorText.ANSI_WHITE + " Figures in Building card " +ColorText.ANSI_RED+fields.getName()+"\n" +ColorText.ANSI_WHITE);

        }else {
            stringBuffer.append(ColorText.ANSI_RED + "♨ The " + robot.getName() + ColorText.ANSI_PURPLE + " " + robot.getRobotId() + ColorText.ANSI_WHITE + " choose to play " + ColorText.ANSI_BLUE + value + ColorText.ANSI_WHITE + " Figures in Building card " + ColorText.ANSI_RED + fields.getName() + "\n" + ColorText.ANSI_WHITE);
        }
            return stringBuffer.toString();
    }

    public static String messageWhenRobotBuyBuilding(Robot robot, BuildingCard buildingCard)
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append(ColorText.ANSI_BLACK + "♨ The " +robot.getName() + ColorText.ANSI_PURPLE+ " "+robot.getRobotId()+ColorText.ANSI_WHITE+" buys a card "+ColorText.ANSI_BLUE + ColorText.ANSI_WHITE + buildingCard.getBuildingName() + ColorText.ANSI_WHITE +"\n");
        return stringBuffer.toString();
    }
    public static String messageWhenGetPoint(Robot robot,int point){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append(ColorText.ANSI_BLACK + robot.getName() + " " + robot.getRobotId() + ColorText.ANSI_CYAN + " earn "+ ColorText.ANSI_YELLOW +point + ColorText.ANSI_GREEN +" points ... !"+ColorText.ANSI_WHITE);
        return stringBuffer.toString();
    }

    public static String messageStatistic(Robot[] robots,DataBase dataBase){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append("\n");

        stringBuffer.append(ColorText.ANSI_GREEN + "                                                            STATISTICS      " + ColorText.ANSI_WHITE +"\n");
        stringBuffer.append("↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔\n");
        stringBuffer.append(ColorText.ANSI_BLUE +"Robot type                   ");
        for(int i = 0;i<robots.length;i++){
            stringBuffer.append(ColorText.ANSI_RED + robots[i].getName() + " " + ColorText.ANSI_BLACK + robots[i].getRobotId() + "           " + ColorText.ANSI_WHITE );
        }
        stringBuffer.append("\n");
        stringBuffer.append("↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔ \n");

        stringBuffer.append(ColorText.ANSI_BLUE + "Number of wins      ");

        for(int i = 0;i<robots.length;i++){
            stringBuffer.append(ColorText.ANSI_BLACK  +"            " + (dataBase.getData().get(robots[i].getRobotId()).getWonGames()  + "★        " + ColorText.ANSI_WHITE ));
        }

        stringBuffer.append("\n");
        stringBuffer.append("↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔ \n");

        stringBuffer.append(ColorText.ANSI_BLUE +"Average points   ");

        for(int i = 0;i<robots.length;i++){
            stringBuffer.append(ColorText.ANSI_BLACK  +"              " + dataBase.scoreAverage(i) + "   " + ColorText.ANSI_WHITE );
        }
        stringBuffer.append("\n");

        stringBuffer.append("↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔↔ \n");

        return stringBuffer.toString();
    }






    public static String messageWhenGameEnd(Robot[] robots,int idWinner){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append("\n");

        stringBuffer.append(ColorText.ANSI_GREEN + "                                                                  ScoreBoard         " + ColorText.ANSI_WHITE +"\n");
        stringBuffer.append(ColorText.ANSI_BLACK + "۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩ \n"+ ColorText.ANSI_WHITE);
        stringBuffer.append(ColorText.ANSI_BLUE +"Robot type                        ");
        for(int i = 0;i<robots.length;i++){
            stringBuffer.append(ColorText.ANSI_CYAN + robots[i].getName() + " " + ColorText.ANSI_BLACK + robots[i].getRobotId() + "             " + ColorText.ANSI_WHITE );
        }
        stringBuffer.append("\n");
        stringBuffer.append(ColorText.ANSI_BLACK + "۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩ \n"+ ColorText.ANSI_WHITE);

        stringBuffer.append(ColorText.ANSI_BLUE + "Game result           ");

        for(int i = 0;i<robots.length;i++){
            if(i == idWinner){
                stringBuffer.append(ColorText.ANSI_RED  +"            WR " +ColorText.ANSI_BLACK  + (robots[i].getScore()  + "★       " + ColorText.ANSI_WHITE ));

            }else {
                stringBuffer.append(ColorText.ANSI_BLACK  +"              " + (robots[i].getScore()  + "★       " + ColorText.ANSI_WHITE ));

            }
        }

        stringBuffer.append("\n");
        stringBuffer.append(ColorText.ANSI_BLACK + "۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩ \n"+ ColorText.ANSI_WHITE);

        stringBuffer.append(ColorText.ANSI_BLUE +"Final number of workers     ");

        for(int i = 0;i<robots.length;i++){
            stringBuffer.append(ColorText.ANSI_BLACK  +"         " + robots[i].getFigures() + "              " + ColorText.ANSI_WHITE );
        }
        stringBuffer.append("\n");

        stringBuffer.append(ColorText.ANSI_BLACK + "۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩ \n"+ ColorText.ANSI_WHITE);

        stringBuffer.append(ColorText.ANSI_BLUE +"Number of buildings constructed     ");

        for(int i = 0;i<robots.length;i++){
            stringBuffer.append(ColorText.ANSI_BLACK  +" " + robots[i].getBuildingList().size() + "                      " + ColorText.ANSI_WHITE );
        }
        stringBuffer.append("\n");

        stringBuffer.append(ColorText.ANSI_BLACK + "۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩ \n"+ ColorText.ANSI_WHITE);

        stringBuffer.append(ColorText.ANSI_BLUE +"Final farming level   ");

        for(int i = 0;i<robots.length;i++){
            stringBuffer.append(ColorText.ANSI_BLACK  +"              " + robots[i].getAgricultureLevel() + "         " + ColorText.ANSI_WHITE );
        }
        stringBuffer.append("\n");

        stringBuffer.append(ColorText.ANSI_BLACK + "۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩۩ \n"+ ColorText.ANSI_WHITE);
        stringBuffer.append("\n");

        stringBuffer.append("The Winner " + ColorText.ANSI_GREEN + robots[idWinner].getName() + " " + ColorText.ANSI_BLUE + robots[idWinner].getRobotId() +     ColorText.ANSI_RED+" WR "+ " ... \n");
        stringBuffer.append(ColorText.ANSI_RED  + "the game is over... !");
        return stringBuffer.toString();

    }





}
