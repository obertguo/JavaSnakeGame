package ml.obertguo.Objects;
import hsa_ufa.Console;
import java.awt.*;

public class Obstacle extends AbstractGameObject {

    //Fixed val used to offset current x and y pos
    private final int moveOffset = 4;

    //# of times to offset by
    private final int maxNumOffsets = 4;

    //Current number of offsets before changing directions
    private int numOffsets = 0;

    /**
     * Initialize obstacle
     * @param console Reference to initialized HSA_UFA console
     * @param obstacleWidth int that represents the obstacle width to be passed to super constructor
     * @param obstacleHeight int that represents the obstacle height to be passed to super constructor
     */
    public Obstacle(Console console, int obstacleWidth, int obstacleHeight){
        super(console, obstacleWidth, obstacleHeight);
    }

    /**
     * {@inheritDoc}
     * @param
     * @return
     */
    @Override
    public void print(){
        this.console.setColor(Color.cyan);
        this.console.fillStar(this.moveX, this.moveY, this.objectWidth, this.objectHeight);
    }

    /**
     * Moves obstacle in the direction based on xDir and yDir in a cyclical manner
     * @param
     * @return
     */
    @Override
    public void move(){
        //Offset current x and y pos in current dir if current number of offsets does not exceed max offsets
        //xDir and yDir are automatically generated after calling the generate function derived from AbstractGameObject
        if(numOffsets < maxNumOffsets){
            this.moveX += moveOffset * xDir;
            this.moveY += moveOffset * yDir;
            ++numOffsets;
        }
        //Otherwise, change directions and reset current number of offsets
        else{
            numOffsets = 0;
            xDir *= -1;
            yDir *= -1;
        }
    }
}
