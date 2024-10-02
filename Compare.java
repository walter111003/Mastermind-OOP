package assignment2;


public class Compare {



//THIS COMPARE CLASS IS TO COMPARE THE USERS INPUT CODE WITH THE SECRET CODE



    //public static String secret_code = SecretCodeGenerator.getInstance().getNewSecretCode();

    public static int[] compare(String user_input, String secret_code, boolean test_mode) {

        int[] result = {0, 0};
        int B = 0;
        int W = 0;
        int first = 1;
        char[] char_array = user_input.toCharArray();
        char[] char_code = secret_code.toCharArray();
        int count = 0;
        int length =  GameConfiguration.pegNumber;  //GETS THE VALID LENGTH OF HOW MANY COLORS CAN BE GUESSED IN THE GAME.
        String[] colors_string = GameConfiguration.colors;  //PUTTING THE AVAILABLE COLOR PEGS IN A STRING ARRAY
        char[] colors_char= new char[colors_string.length];  //CONVERTING IT INTO A CHAR ARRAY SO THAT I CAN COMPARE IT TO THE USERS CHAR ARRAY, MAKES THINGS SIMPLER IF BOTH ARE CHAR ARRAYS


        if(user_input.isEmpty()){  //CHECKS IF USERS INPUT IS EMPTY
            result[0] = -111;
            return result;
        }

        if (char_array.length != length  ) {
            result[0] = -111;  //checks to see if the input length is not whatever length is valid
            return result;
        }

        for (int i = 0; i < colors_string.length; i++) {
            colors_char[i] = colors_string[i].charAt(0); // Get the first character of each string

        }


        for(int i =0; i<char_array.length; i++){        //CHECKS IF USERS INPUT DOES NOT MATCH ONE OF THE VALID COLORS FOR THE GAME
            for(int j =0; j<colors_char.length;j++){
                if(char_array[i]== colors_char[j]){
                    count+=1;

                }
            }
            if(count == 0){
                result[0] =-111;
                return result;
            }
            count = 0;
        }


        if (secret_code.equals(user_input)) {
            result[0] = 4;
            return result;
        }



        for(int i = 0, j =0; i<char_code.length; i++, j++){
            if(char_code[i] == char_array[j]){
                B+=1;
                char_code[i] = '0';
                char_array[j]='0';

            }
        }


        for (int i = 0; i < char_code.length; i++) {
            for (int j = 0; j < char_array.length; j++) {
                if ((char_code[i] == char_array[j]) && (char_code[i] != '0') && (char_array[j]!='0') ) {
                    W += 1;
                    char_code[i] = '0';
                    char_array[j] = '0';

                }


            }

        }
            result[0] = B;
            result[1] = W;


            if(test_mode){
                System.out.print(secret_code);
            }

            return result;

        }





}