package assignment2;


public class Driver {



    public static void main(String[] arg) {

        boolean testing = false;
        if(arg.length > 0 && arg[0].equals("1")) {
            testing = true;
        }

        Game.runGame(testing);


    }

}