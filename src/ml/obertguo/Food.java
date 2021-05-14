package ml.obertguo;
import hsa_ufa.Console;
import java.awt.*;

public class Food {
    private int spawnX;
    private int spawnY;

    private final int foodWidth;
    private final int foodHeight;

    private final int spawnOffset = 10;

    private final Console console;
    private final int gameWidth;
    private final int gameHeight;
    public Food(Console console, int foodWidth, int foodHeight){
        this.console = console;

        this.gameWidth = console.getDrawWidth();
        this.gameHeight = console.getDrawHeight();

        this.foodWidth = foodWidth;
        this.foodHeight = foodHeight;
    }

    public void generate(){
        //generate random pts for food to spawn — make sure it doesnt spawn at the edge of the window
        spawnX = (int)(Math.random() * ((gameWidth-spawnOffset) - spawnOffset + 1)) + spawnOffset;
        spawnY = (int)(Math.random() * ((gameHeight-spawnOffset) - spawnOffset + 1)) + spawnOffset;
    }

    public void print(){
        console.clearRect(spawnX, spawnY, foodWidth, foodHeight);
        console.setColor(Color.RED);
        console.fillRect(spawnX, spawnY, foodWidth, foodHeight);
    }

    public boolean checkCollision(int snakeHeadXPos, int snakeHeadYPos, int snakeWidth, int snakeHeight){
        return snakeHeadXPos < spawnX + foodWidth &&
                spawnX < snakeHeadXPos + snakeWidth &&
                snakeHeadYPos < spawnY + foodHeight &&
                spawnY < snakeHeadYPos + snakeHeight;
    }
}
