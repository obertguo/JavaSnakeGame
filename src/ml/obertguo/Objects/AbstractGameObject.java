package ml.obertguo.Objects;
import hsa_ufa.Console;
import ml.obertguo.Types.Coordinate;
import java.util.HashSet;

//Abstract class: https://www.w3schools.com/java/java_abstract.asp
abstract class AbstractGameObject {
    //Keep track of original spawn pos
    private int spawnX;
    private int spawnY;

    //Keep track of where obs will move to
    protected int moveX;
    protected int moveY;

    protected final int objectWidth;
    protected final int objectHeight;
    protected final Console console;

    private final int spawnOffset = 60;

    private final int gameWidth;
    private final int gameHeight;

    //Used to help move obj in a particular direction
    protected int xDir;
    protected int yDir;

    /**
     * Superclass constructor that initializes variables required for the object
     * @param console Reference to an initialized HSA_UFA console
     * @param objectWidth An int that represents the width of the object
     * @param objectHeight AN int that represents the height of the object
     */
    public AbstractGameObject(Console console, int objectWidth, int objectHeight){
        this.console = console;

        this.gameWidth = console.getDrawWidth();
        this.gameHeight = console.getDrawHeight();

        this.objectWidth = objectWidth;
        this.objectHeight = objectHeight;
    }

    /**
     * Generate spawn positions for obj while making sure they are distanced from the snake head and other objs.
     * @param objectsSet A hashset that contains coordinate positions of all game objects
     * @param snake A reference to initialized snake object
     * @return
     */
    public void generate(HashSet<Coordinate> objectsSet, Snake snake){

        //Keep track of whether or not the generated spawn pos is valid near the snake head, and near other objects.
        boolean validSpawnNearHead;
        boolean validSpawnNearObjects;

        //Remove old coordinates
        objectsSet.remove(getSpawnCoordinates());

        //Using a do/while loop to generate valid positions is not exactly the best way of approaching things
        //But I suppose it works nicely for now... scaling to more objects or shrinking the game size wil make this solution inefficient and inadequate
        do{
            //Assume the obj has a valid spawn location
            validSpawnNearHead = true;
            validSpawnNearObjects = true;

            //generate random pts for object to spawn — make sure it doesnt spawn at the edge of the window (hence the offset)
            //Arithmetic operators
            spawnX = (int)(Math.random() * ((gameWidth-spawnOffset) - spawnOffset + 1)) + spawnOffset;
            spawnY = (int)(Math.random() * ((gameHeight-spawnOffset) - spawnOffset + 1)) + spawnOffset;

            //Set initial pos for when obj moves
            moveX = spawnX;
            moveY = spawnY;

            //Check if the obj spawns near the head - if so, then the spawn location is invalid
            if(spawnX > snake.getHeadXPos() - spawnOffset &&
                    spawnX < snake.getHeadXPos() + snake.getSnakeWidth() + spawnOffset &&
                    spawnY > snake.getHeadYPos() - spawnOffset &&
                    spawnY < snake.getHeadYPos() + snake.getSnakeHeight() + spawnOffset){
                    validSpawnNearHead = false;
            }

            //Check if the obj spawns near other obs by iterating through the hashset
            for(Coordinate coordinate : objectsSet){
                int currentX = coordinate.x;
                int currentY = coordinate.y;

                if(spawnX > currentX - spawnOffset &&
                        spawnX < currentX + objectWidth + spawnOffset &&
                        spawnY > currentY - spawnOffset &&
                        spawnY < currentY + objectHeight + spawnOffset) {
                    validSpawnNearObjects = false;
                }
            }

        //Generate new spawn pos if obj spawn points are too close to the snake head or other objs
        } while(!(validSpawnNearObjects && validSpawnNearHead));

        //Add new coordinates to set
        objectsSet.add(getSpawnCoordinates());

        //Auto generate a random direction for the object to move
        //where 0 is stationary, and 1 represents movement along the respective axis
        xDir = Math.random() < 0.5 ?  0 :  1;
        yDir = Math.random() < 0.5 ?  0 :  1;
    }

    /**
     * Getter that retuns the object's spawn coordinates
     * @param
     * @return Coordinate
     */
    public Coordinate getSpawnCoordinates(){
        return new Coordinate(spawnX, spawnY);
    }

    //Each game object will have different implementations oh how it appears on the console
    /**
     * Print object to HSA_UFA console
     * @param
     * @return
     */
    abstract public void print();

    //Each game object will have different implementations oh how it will move
    /**
     * Move object
     * @param
     * @return
     */
    abstract public void move();

    /**
     * Checks if there is a collision between the object and snake
     * @param snake Reference to the game's snake object
     * @return boolean
     */
    public boolean checkCollision(Snake snake){
        //collision checking, boolean and comparison operators used
        return snake.getHeadXPos() < spawnX + objectWidth &&
                spawnX < snake.getHeadXPos() + snake.getSnakeWidth() &&
                snake.getHeadYPos() < spawnY + objectHeight &&
                spawnY < snake.getHeadYPos() + snake.getSnakeHeight();
    }
}
