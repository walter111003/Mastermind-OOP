package assignment2;

import java.util.Scanner;
import java.util.Arrays;

public class Game {

    public static void runGame(boolean test){

        boolean testing = test;
        int guesses = GameConfiguration.guessNumber;
        int guesses_copy = guesses;

        System.out.print("Welcome to Mastermind. Here are the rules.\n" +"\n" +
                "This is a text version of the classic board game Mastermind.\n" +"\n"+
                "The computer will think of a secret code. The code consists of 4\n" +
                "colored pegs. The pegs MUST be one of six colors: blue, green,\n" +
                "orange, purple, red, or yellow. A color may appear more than once in\n" +
                "the code. You try to guess what colored pegs are in the code and\n" +
                "what order they are in. After you make a valid guess the result\n" +
                "(feedback) will be displayed.\n" +
                "The result consists of a black peg for each peg you have guessed\n" +"\n" +
                "exactly correct (color and position) in your guess. For each peg in\n" +
                "the guess that is the correct color, but is out of position, you get\n" +
                "a white peg. For each peg, which is fully incorrect, you get no\n" +
                "feedback.\n" + "\n" +
                "Only the first letter of the color is displayed. B for Blue, R for\n"+
                "Red, and so forth. When entering guesses you only need to enter the\n" +
                "first character of each color as a capital letter.\n" + " \n" + "You have " + guesses + " guesses to figure out the secret code or you lose the\n" +
                "game. Are you ready to play? (Y/N): ");


        Scanner user = new Scanner(System.in);
        String input = user.nextLine();
        String input2 = "TEST";
        int dt =0;
        int again = 0;
        int[] output;
        int his_count = 0;
        int[] color1 = new int[guesses+1];
        int k = 0;
        int flag = 0;
        int j = 0;
        int[] color2 = new int[guesses+1];
        String[] his = new String[guesses];
        int first = 1;
        int error = 0;  // first error has occurred if it is =1
        String secret= SecretCodeGenerator.getInstance().getNewSecretCode();
        //String secret = "OBPY";


        while(input.equals("Y") ){
            dt = 0;


            if(guesses != 0) {
                if(flag != 0 && (input2.compareTo("HISTORY") != 0)){
                    System.out.print("\nType in the characters for your guess and press enter.\nEnter Guess: ");
                    input2 = user.nextLine();


                }
                else if(input2.compareTo("HISTORY") == 0 ){

                    input2 = user.nextLine();


                }
                else if(first == 1 && error == 0 && (input2.isEmpty() == false)){

                    System.out.print("\n" + "Generating secret code ... \n" + "\n" + "You have " + guesses + " guesses left.\n" +
                            "What is your next guess?\n" +
                            "Type in the characters for your guess and press enter.\n" + "Enter guess: ");

                    input2 = user.nextLine();

                }

                else if(error == 1){
                    System.out.print("\nType in the characters for your guess and press enter.\n" + "Enter guess: ");
                    input2 = user.nextLine();

                }

            }
            else{
                System.out.print("\n" + "Sorry, you are out of guesses. You lose, boo-hoo.\n" + "\n"+ "Are you ready for another game (Y/N): ");
                input  = user.nextLine();

                Arrays.fill(color1,0);
                Arrays.fill(color2,0);
                Arrays.fill(his,null);
                k = 0;
                j =0;
                his_count = 0;

                if(input.equals("Y")){
                    guesses = guesses_copy;
                    dt = 0;
                    input2 = "TEST";
                    secret = SecretCodeGenerator.getInstance().getNewSecretCode();
                    System.out.print("\n" + "Generating secret code ... \n" + "\n" + "You have " + guesses + " guesses left.\n" +
                            "What is your next guess?");


                }
                else{
                    break;
                }
            }

            if(input2.compareTo("TEST") != 0) {
                output = Compare.compare(input2, secret, testing);
            }
            else{
                continue;
            }

            if(output[0] == 4 && (input2.compareTo("") !=0) ){
                System.out.print("\n"+input2 + " -> Result: "+ output[0]+"B_"+output[1]+"W - You win!! \n" + "\n" + "Are you ready for another game (Y/N): ");
                input = user.nextLine();
                his[his_count] = input2;
                his_count++;
                dt = 1;
                first = 0;

                Arrays.fill(color1,0);
                Arrays.fill(color2,0);
                Arrays.fill(his,null);
                k = 0;
                j =0;
                his_count = 0;

                if(input.equals("Y")){
                    guesses = guesses_copy;
                    dt = 0;
                    input2 = "TEST";
                    secret = SecretCodeGenerator.getInstance().getNewSecretCode();
                    System.out.print("\n" + "Generating secret code ... \n" + "\n" + "You have " + guesses + " guesses left.\n" +
                            "What is your next guess?");



                }
                else{
                    break;
                }


            }


            if(output[0] == -111){ //&& (input2.compareTo("") !=0)){
                if(input2.compareTo("HISTORY") != 0) {
                    System.out.print("\n"+input2 + " -> INVALID GUESS" + "\n"+ "\nWhat is your next guess? ");
                    dt = 1;
                    if(first == 1){
                        error = 1;
                    }

                }
            }

            if(dt != 1 && (input2.compareTo("") !=0) ){
                if(input2.compareTo("HISTORY") != 0) {
                    guesses -= 1;
                    output = Compare.compare(input2,secret,testing);
                    color1[k] = output[0];
                    color2[j] = output[1];
                    k++;
                    j++;
                    his[his_count] = input2;
                    his_count += 1;

                    if(guesses != 0) {
                        System.out.print("\n"+ input2 + " -> Result: " + output[0] + "B_" + output[1] + "W\n" + "\n" + "You have " + guesses + " guesses left.\n" + "What is your next guess?");
                        first = 0;
                        flag = 1;
                    }

                }
            }


            if(input2.equals("HISTORY") || input.equals("HISTORY")){
                HISTORY.log(his, color1, color2);
                System.out.print("\nYou have " + guesses + " guesses left.\n" + "What is your next guess?\nType in the characters for your guess and press enter.\nEnter guess:  ");
                flag = 1;



            }

        }














    }
}
