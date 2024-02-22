package lastpencil;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        inputNumberPencils();
    }

    public static void inputNumberPencils(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many pencils would you like to use:");
        int numPencils = 0;
        boolean goodNumber = false;
        String input = "";
        while(!goodNumber) {
            input = scanner.nextLine().trim();
            if(!input.isEmpty()){
                try{
                    numPencils = Integer.parseInt(input);
                    if (numPencils <= 0){
                        System.out.println("The number of pencils should be positive");
                    }else {
                        goodNumber = true;
                    }
                }catch (NumberFormatException e){
                    System.out.println("The number of pencils should be numeric");
                }
            }else{
                System.out.println("The number of pencils should be numeric");
            }
        }
        boolean goodName = false;
        String firstPlayer = "";
        while(!goodName) {
            System.out.println("Who will be the first (John, Jack):");
           firstPlayer = scanner.nextLine();
            if(firstPlayer.equals("Jack") || firstPlayer.equals("John")) {
                goodName = true;
            }else{
                System.out.println("Choose between 'John' and 'Jack'");
                goodName = false;
            }
        }
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<numPencils; i++){
            builder.append("|");
        }
        String pencilOutput = builder.toString();
        System.out.println(pencilOutput);


        takeTurns(numPencils, firstPlayer);

        scanner.close();
    }
    public static void takeTurns(int pencils, String playerUp){
        Scanner scanner = new Scanner(System.in);
        Boolean playersTurn = false;
        if (playerUp.equals("John")) {
            playersTurn = true;
        }
        while(pencils > 0){
            System.out.println(playerUp + "'s turn!");
            Boolean goodNumber = false;
            int numPencilsTaken = 0;
            while(!goodNumber) {
                if (playersTurn) {
                    try {
                        numPencilsTaken = scanner.nextInt();
                        if (numPencilsTaken == 1 || numPencilsTaken == 2 || numPencilsTaken == 3) {
                            if ((pencils - numPencilsTaken) < 0) {
                                System.out.println("Too many pencils were taken");
                                goodNumber = false;
                            } else {
                                scanner.nextLine();
                                goodNumber = true;
                                playersTurn = false;
                            }
                        } else {
                            System.out.println("Possible values: '1','2',or '3'");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Possible values: '1','2',or '3'");
                        scanner.next();
                    }
                }else{
                    numPencilsTaken = botMove(pencils);
                    System.out.println(numPencilsTaken);
                    playersTurn = true;
                    goodNumber = true;
                }
            }
            pencils -= numPencilsTaken;
            if (pencils > 0) {
                StringBuilder builder = new StringBuilder();
                for(int i=0; i<pencils; i++){
                    builder.append("|");
                }
                System.out.println(builder.toString());
                if (playerUp.equals("John")){
                    playerUp = "Jack";
                }else{
                    playerUp = "John";
                }
            }else{
                if(playerUp.equals("John")){
                    System.out.println("Jack won!");
                }else{
                    System.out.println("John won!");
                }
                break;
            }

        }
        scanner.close();

    }
    public static int botMove(int pencilsRemaining){
        if(pencilsRemaining == 1){
            return 1;
        }
        if((pencilsRemaining % 4) == 0){
            return 3;
        }
        if(((pencilsRemaining + 1) % 4) == 0){
            return 2;
        }
        if(((pencilsRemaining + 2) % 4) == 0){
                return 1;
        }
        return new Random().nextInt(3) + 1;
    }
}
