package ml.obertguo;
import hsa_ufa.Console;

import java.awt.*;

public class WelcomeWindow {
    private final String name;

    public WelcomeWindow(Console console){
        console.setBackgroundColor(Color.LIGHT_GRAY);
        console.setColor(Color.BLACK);
        console.clear();

        console.println("What is your name?");
        this.name = console.readLine();
        console.clear();

        console.println("Use the arrow keys to move.\nAvoid crashing into yourself and eat the food to grow.\n");

        console.setColor(Color.RED);
        console.println("Press any key to start");
        console.getChar();
    }

    public String getName(){
        return name;
    }
}
