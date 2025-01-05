package hangmanjava;

import java.util.Scanner;

/**
 *
 * @author oscaro
 */
public class HangmanJava {

     private static String player;

    public static void main(String[] args) {

        Scanner tec = new Scanner(System.in);
        String secretWord;
        int maxTry = 10;
        boolean flag = true;
        String answer;

        System.out.println("***** Welcome to the Hangman Game *****");


        while (flag) {
            System.out.println("Please, introduce the word you want to use in the game:");
            secretWord = getValidEntrance(tec).toLowerCase();
            System.out.println("Secret Word introduced: " + secretWord);
            
            System.out.println("Please, introduce the name of the Player");
            player = getValidEntrance(tec);

            char[] guessedLetters = new char[secretWord.length()];

            for (int i = 0; i < guessedLetters.length; i++) {

                guessedLetters[i] = '_';

            }
            
            int opportunities = 0;
            boolean IsGuessed = false;

            while (!IsGuessed && opportunities < maxTry) {
                System.out.println("Word to be guessed: " + String.valueOf(guessedLetters) + " (" + secretWord.length() + " letters)");
                char letter = Character.toLowerCase(getValidLetter(tec));

                boolean rightLetter = false;

                for (int i = 0; i < secretWord.length(); i++) {

                    if (secretWord.charAt(i) == Character.toLowerCase(letter)) {
                        guessedLetters[i] = letter;
                        rightLetter = true;
                    }
                }

                if (!rightLetter) {
                    opportunities++;
                    System.out.println("Wrong, you're missing " + (maxTry - opportunities) + " opportunities.");
                }

                if (String.valueOf(guessedLetters).equals(secretWord)) {
                    IsGuessed = true;
                    System.out.println("Congratulations, you have guessed Secret Word: " + secretWord);
                }
                       


            }
            if (!IsGuessed) {
                System.out.println("It's a shame, you don't have more opportunities! GAME OVER");
            }


            System.out.print("Would you like to play again? (y/n): ");
            answer = tec.nextLine().toLowerCase();
            while (!answer.equals("y") && !answer.equals("n")) {
                System.out.println("Invalid Answer. Please, introduce 'y' or 'n'.");
                System.out.print("Would you like to play again? (y/n): ");
                answer = tec.nextLine().toLowerCase();
            }

            if (answer.equalsIgnoreCase("n")) {
                flag = false;
            }
        }
        tec.close();
        System.out.println("See you later " + player + " , It was a good game!!");

    }


    private static String getValidEntrance(Scanner tec) {
        String entrance = tec.nextLine();
        while (Character.isDigit(entrance.charAt(0)) || isOperator(entrance.charAt(0))) {
            System.out.println("Error!! You must Introduce a valid String, not numbers or Operators.");
            entrance = tec.nextLine();
        }
        return entrance;
    }

    private static char getValidLetter(Scanner tec) {
        char letter;
        while (true) {
            System.out.println(player + " ,Introduce a letter, please: ");
            String input = tec.nextLine();
            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                letter = input.charAt(0);
                break; // Valid letter, exit the loop
            } else {
                System.out.println("Error!! You must introduce a letter.");
            }
        }
        return letter;
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '.' || c == ',' || c == ';';
    }
}
