/**
@author Obert Guo
About: Snake Game, Part 2 Coding Assignment

Updates:
08 June 2021
    Refactored code such that methods do not invoke other methods to avoid stack overflow.

09 June 2021
    Food objs will not generate on top of each other or really close to each other.
    Obstacle objs will not generate on top of each other or really close to each other.

    However, it is possible that a food obj can spawn nearby an obstacle obj...
    Hopefully probability is on our side. If not, then call it a "feature" where this adds difficulty to the game

10 June 2021
    Game objects will no longer spawn on top of one another, or near each other (including food and obstacle objects)
    Refactored code and used a set to store object positions instead of an array,
        so that it takes O(1) rather than O(n) to remove the outdated positions

11 June 2021
    Added code documentation and refactored code by including a types folder to contain distinct custom data types

23 June 2021
    Added easy, medium, and hard difficulty settings

Sources used for the project:
Comparing objects in a HashSet: https://stackoverflow.com/questions/53438370/comparing-objects-in-hashset
HashSet: https://www.geeksforgeeks.org/hashset-in-java/
Abstract class: https://www.w3schools.com/java/java_abstract.asp
Javadoc: https://www.tutorialspoint.com/java/java_documentation.htm
 */

package ml.obertguo;
import hsa_ufa.Console;
import ml.obertguo.Types.Difficulty;
import ml.obertguo.Views.GameOverWindow;
import ml.obertguo.Views.GameWindow;
import ml.obertguo.Views.WelcomeWindow;
import java.awt.*;

public class Main {
    private static final int GAMEWIDTH = 700;
    private static final int GAMEHEIGHT = 700;
    private static final int FONTSIZE = 16;

    private static int GAMEDELAYMS;
    private static final int DELAYSTARTMS = 500;

    private static boolean gameOver = false;
    private static Difficulty difficulty;

    public static void main(String[] args) throws InterruptedException{
        //Initialize console.
        //The console will be injected into each of the views - WelcomeWindow, GameWindow, and GameOverWindow
        Console console = new Console(GAMEWIDTH, GAMEHEIGHT, FONTSIZE);
        console.setFont(new Font("SansSerif", Font.BOLD, FONTSIZE));

        WelcomeWindow welcomeWindow = new WelcomeWindow(console);
        String name = welcomeWindow.askName();
        difficulty = welcomeWindow.selectDifficulty();
        welcomeWindow.displayRules(name);

        GameWindow gameWindow = new GameWindow(console, difficulty);
        GAMEDELAYMS = Factory.generateGameDelayMS(difficulty);

        //Add a slight delay so that user is ready
        Thread.sleep(DELAYSTARTMS);

        while(true){
            gameWindow.processInput();
            gameWindow.updateGame();
            gameWindow.render();
            gameWindow.checkFoodCollision();
            gameOver = gameWindow.checkObstacleCollision() || gameWindow.checkSnakeCollision();

            Thread.sleep(GAMEDELAYMS);

            if(gameOver) {
                GameOverWindow gameOverWindow = new GameOverWindow(console, name, String.valueOf(gameWindow.getScore()));
                gameOverWindow.display();

                if(gameOverWindow.playAgain()){
                    //Allow user to change difficulty
                    difficulty = welcomeWindow.selectDifficulty();

                    //Update game delay to reflect difficulty chosen
                    GAMEDELAYMS = Factory.generateGameDelayMS(difficulty);

                    //Re-initialize game (restart) by assigning gameWindow to a new GameWindow reference
                    gameWindow = new GameWindow(console, difficulty);

                    //Add a slight delay so that user is ready
                    Thread.sleep(DELAYSTARTMS);
                }
                else System.exit(0);
            }
        }
    }

    /**
     * Draws a checkered background on a HSA_UFA console
     * @param console A reference to an initialized HSA_UFA console
     * @return void
     */
    public static void drawCheckeredBackground(Console console){
        //Size of each grid square
        final int size = 80;
        Color[] colors = Factory.generateColor(difficulty);

        int rowColor = 0; //An index that keeps track of the next row color to alternate to
        int colColor; //An index that keeps track of the next column color to alternate to

        for(int x = 0; x < console.getDrawWidth(); x += size){
            //Set beginning col color and then alternate row color for next row
            colColor = rowColor;
            rowColor = (rowColor + 1) % colors.length;

            for(int y = 0; y < console.getDrawHeight(); y += size){
                console.setColor(colors[colColor]);
                console.fillRect(x, y, size, size);

                //Alternate column colors for the next column in the row
                colColor = (colColor + 1) % colors.length;
            }
        }
    }
}