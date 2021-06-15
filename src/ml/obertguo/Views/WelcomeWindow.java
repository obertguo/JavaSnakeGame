package ml.obertguo.Views;
import hsa_ufa.Console;
import ml.obertguo.Main;
import java.awt.*;

public class WelcomeWindow {
    private final Console console;

    public WelcomeWindow(Console console) {
        this.console = console;
    }

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

    public void displayRulesAndOptions(String name){
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
