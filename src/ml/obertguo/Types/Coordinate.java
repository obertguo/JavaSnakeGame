package ml.obertguo.Types;
import java.util.Objects;

public class Coordinate{
    public final int x;
    public final int y;

    /**
     * Append a new head to snake - used to grow snake and move snake
     * @param x int that represents the x pos of obj
     * @param y int that represents the y pos of obj
     * @return
     */
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    //Hashcode and equals functions are overridden from the Object class as to compare game objects in a set, found in GameWindow and IGameObject
    //Comparing objects in a HashSet: https://stackoverflow.com/questions/53438370/comparing-objects-in-hashset
    //Overriding hashcode and equals methods: https://crunchify.com/how-to-override-equals-and-hashcode-method-in-java/
    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(o == this) return true;

        if(o instanceof Coordinate){
            Coordinate object = (Coordinate)o;
            return object.x == x && object.y == y;
        }
        return false;
    }
}