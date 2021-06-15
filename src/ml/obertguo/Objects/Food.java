package ml.obertguo.Objects;
import hsa_ufa.Console;
import java.awt.*;

public class Food extends AbstractGameObject {

    /**
     * Initialize obstacle
     * @param console Reference to initialized HSA_UFA console
     * @param foodWidth int that represents the food width to be passed to super constructor
     * @param foodHeight int that represents the food height to be passed to super constructor
     */
    public Food(Console console, int foodWidth, int foodHeight){
        super(console, foodWidth, foodHeight);
    }

    /**
     * {@inheritDoc}
     * @param
     * @return
     */
    @Override
    public void print(){
        this.console.setColor(Color.RED);
        this.console.fillRect(this.moveX, this.moveY, this.objectWidth, this.objectHeight);
    }

    //No need for food to move
    @Override
    public void move(){}
}
