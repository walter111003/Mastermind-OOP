package assignment2;

public class HISTORY {

    public static void log(String[] input, int[] peg, int[] peg2) {




        for(int i = 0; i< input.length; i++){
            if(input[i] != null )
            System.out.print("\n"+input[i]+"\t\t"+ peg[i]+"B_"+peg2[i]+"W\n" );

        }




    }

}
