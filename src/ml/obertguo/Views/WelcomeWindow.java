package ml.obertguo.Views;
import hsa_ufa.Console;
import ml.obertguo.Main;
import ml.obertguo.Types.Difficulty;

import java.awt.*;

public class WelcomeWindow {
    private final Console console;

    /**
     * Constructor
     * @param console reference to initialized console
     */
    public WelcomeWindow(Console console) {
        this.console = console;
    }

    /**
     * Prompts user for their name on console
     * @param
     * @return user's name as a string
     */
    public String askName(){
        console.setBackgroundColor(Color.LIGHT_GRAY);
        console.clear();
        Main.drawCheckeredBackground(console);

        console.setColor(Color.BLACK);
        console.drawString("What is your name?", 5, 20);

        //Ask user for name (Processing user input)
        console.setCursor(2, 0);
        return console.readLine();
    }

    /**
     *  Prompts user for snake game difficulty
     * @param
     * @return Difficulty enum value
     */
    public Difficulty selectDifficulty(){
        console.clear();
        Main.drawCheckeredBackground(console);
        console.setColor(Color.BLACK);

        console.println("Select your preferred difficulty for the game.\n");
        console.println("Enter e for easy, m for medium, or h for hard.");
        console.println("By default, if you press enter, the easy difficulty is selected.\n");

        console.println("The harder the difficulty, the quicker the gameplay, the more obstacles, and the less food.");

        char input = ' ';

        //Difficulty is initially set to easy
        Difficulty difficulty = Difficulty.EASY;

        //User will either need to enter e for east, m for medium, h for hard, or enter key
        while(!(input == 'e' || input == 'm' || input == 'h' || input == '\n')){
            input = Character.toLowerCase(console.getChar());
            switch (input){
                case 'e':
                    difficulty = Difficulty.EASY;
                    break;
                case 'm':
                    difficulty = Difficulty.MEDIUM;
                    break;
                case 'h':
                    difficulty = Difficulty.HARD;
                    break;
            }
        }

        return difficulty;
    }

    /**
     *  Displays game rules
     * @param name User's name as a string
     * @return
     */

    public void displayRules(String name){
        console.clear();
        Main.drawCheckeredBackground(console);

        console.setColor(Color.BLACK);
        console.drawString("Welcome " + name + " to the snake game", 5, 20);

        console.setCursor(2, 0);
        console.println(
                        "1. Use the arrow keys to move.\n" +
                        "2. Avoid crashing into yourself\n" +
                        "3. Eat the red food to grow\n" +
                        "4. Avoid crashing the snake head into blue star obstacles"
        );

        console.setColor(Color.RED);
        console.drawString("Press any key to start", 5, 200);

        console.getChar();
    }
}
