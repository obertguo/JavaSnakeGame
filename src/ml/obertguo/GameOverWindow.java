package ml.obertguo;
import hsa_ufa.Console;
import java.awt.*;

public class GameOverWindow {
    public GameOverWindow(Console console, String name, String score){
        console.setBackgroundColor(Color.LIGHT_GRAY);
        console.clear();

        console.setColor(Color.RED);
        console.println("GAME OVER\n" + name + "'s final score was " + score + "\n");

        console.setColor(Color.BLACK);
        console.println("Press y to play again\nPress n to exit");

        char input = ' ';
        while(input != 'y' && input != 'n'){
            input = Character.toLowerCase(console.getChar());
        }

        if(input == 'y') Main.startGame(console, name);

        else System.exit(0);
    }
}
