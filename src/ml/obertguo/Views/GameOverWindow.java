package ml.obertguo.Views;
import hsa_ufa.Console;
import ml.obertguo.Main;
import java.awt.*;

public class GameOverWindow {
    private final Console console;
    private final String name;
    private final String score;

    public GameOverWindow(Console console, String name, String score){
        this.console = console;
        this.name = name;
        this.score = score;
    }

    public void display(){
        console.setBackgroundColor(Color.LIGHT_GRAY);
        console.clear();
        Main.drawCheckeredBackground(console);

        console.setColor(Color.RED);
        console.drawString("GAME OVER", 5, 20);

        console.setColor(Color.black);
        console.drawString(name + "'s final score was " + score, 5, 40);

        console.setColor(Color.BLACK);
        console.setCursor(3, 0);
        console.println("Press y to play again\nPress n to exit");
    }

    public boolean playAgain(){
        //Processing user input (y to play again, n to exit)
        char input = ' ';
        while(!(input == 'y' || input == 'n')){
            input = Character.toLowerCase(console.getChar());
        }

        return input == 'y';
    }
}
