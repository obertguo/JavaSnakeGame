package ml.obertguo.Objects;
import hsa_ufa.Console;
import ml.obertguo.Types.Direction;
import ml.obertguo.Types.Node;
import java.awt.*;

public class Snake {
    private final int snakeWidth;
    private final int snakeHeight;
    private final int gameWidth;
    private final int gameHeight;
    private final Console console;

    //Snake is based on a singly linked list
    private Node head;
    private Node tail;
    private Direction currentDirection;

    /**
     * Initialize snake
     * @param console Reference to initialized HSA_UFA Console
     * @param snakeWidth int that represents the snake width in px on the console
     * @param snakeHeight int that represents the snake height in px on the console
     */
    public Snake(Console console, int snakeWidth, int snakeHeight){

        //Initialize snake head at pos (100,100) on the console - doesn't really matter what the initial pos is
        this.head = new Node(100, 100, null);
        this.tail = head;

        this.console = console;

        this.gameWidth = console.getDrawWidth();
        this.gameHeight = console.getDrawHeight();

        this.snakeWidth = snakeWidth;
        this.snakeHeight = snakeHeight;

        //Initialize snake dir - doesn't really matter what direction is picked
        currentDirection = Direction.DOWN;
    }

    //GETTERS

    /**
     * Get snake head x position
     * @param
     * @return int
     */
    public int getHeadXPos(){
        return head.x;
    }

    /**
     * Get snake head y position
     * @param
     * @return int
     */
    public int getHeadYPos(){
        return head.y;
    }

    /**
     * Get snake width in px
     * @param
     * @return int
     */
    public int getSnakeWidth(){
        return snakeWidth;
    }

    /**
     * Get snake height in px
     * @param
     * @return int
     */
    public int getSnakeHeight(){
        return snakeHeight;
    }

    /**
     * Get snake direction
     * @param
     * @return Direction
     */
    public Direction getDirection(){
        return currentDirection;
    }

    //SETTERS

    /**
     * Set snake direction
     * @param direction Represents an enum value from Direction
     * @return
     */
    public void setDirection(Direction direction){
        currentDirection = direction;
    }

    /**
     * Append a new head to snake - used to grow snake and move snake
     * @param
     * @return
     */
    private void appendNewHead(){
        //New head locations
        int x = head.x;
        int y = head.y;

        //Point to old head
        Node temp = head;

        //append head based on current snake direction
        //(selection structure, switch statement)
        switch(currentDirection){
            case UP:
                y -= snakeHeight;
                break;
            case DOWN:
                y += snakeHeight;
                break;
            case LEFT:
                x -= snakeWidth;
                break;
            case RIGHT:
                x += snakeWidth;
        }

        //Roll over x/y pos when snake goes out of frame
        //Since Java's mod function returns negatives, checks are in place to return a positive mod value
        x = x % gameWidth < 0 ? x % gameWidth + gameWidth : x % gameWidth;
        y = y % gameHeight < 0 ? y % gameHeight + gameHeight : y % gameHeight;

        //Create new head and point to old head
        Node newHead = new Node(x, y, temp);

        //Update head reference to new head
        head = newHead;
    }

    //SNAKE METHODS

    /**
     * Grows snake based on current direction
     * @param
     * @return
     */
    public void grow(){
        appendNewHead();
    }

    /**
     * Moves snake based on current direction
     * @param
     * @return
     */
    public void move(){
        //Move snake by appending a new head and removing the tail
        //(update head ref to new head, and update tail ref to 2nd last tail)
        appendNewHead();

        Node temp;
        //Delete tail by traversing the linked list, stopping at the node before the tail
        temp = head;

        //Traversing linked list, repetition structure (while loop)
        while(temp.next != tail) temp = temp.next;

        //Remove reference to old tail
        temp.next = null;
        //Update tail reference
        tail = temp;
    }

    /**
     * Print snake to console
     * @param
     * @return
     */
    public void print(){
        //Print snake body by traversing linked list and drawing rectangles at specified coords
        console.setColor(Color.GREEN);
        Node temp = head;

        //Traversing linked list, repetition structure (while loop)
        while(temp != null){
            console.drawRect(temp.x, temp.y, snakeWidth, snakeHeight);
            temp = temp.next;
        }

        //Fill in head to make it visibly distinct from the body
        console.setColor(Color.GREEN);
        console.fillRect(head.x, head.y, snakeWidth, snakeHeight);
    }

    /**
     * Check if snake has collided with itself
     * @param
     * @return boolean
     */
    public boolean checkCollision(){
        Node temp = head.next;

        //Check collision by traversing linked list, repetition structure (while loop)
        while(temp != null){
            //If snake head is within the boundaries of its own body, a collision has occurred
            if(head.x < temp.x + snakeWidth
                    && head.y < temp.y + snakeHeight
                    && temp.x < head.x + snakeWidth
                    && temp.y < head.y + snakeHeight)
                return true;

            else temp = temp.next;
        }
        //If the function has not returned true yet, then no collision has occurred
        return false;
    }
}