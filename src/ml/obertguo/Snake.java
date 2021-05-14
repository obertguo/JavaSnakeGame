package ml.obertguo;
import hsa_ufa.Console;
import java.awt.*;

//Snake is based on a singly linked list
class Node{
    public int x;
    public int y;
    public Node next;

    public Node(int x, int y, Node next){
        this.x = x;
        this.y = y;
        this.next = next;
    }
}

public class Snake {
    private final int snakeWidth;
    private final int snakeHeight;
    private final int gameWidth;
    private final int gameHeight;
    private final Console console;

    private Node head;
    private Node tail;
    private Direction currentDirection;

    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public Snake(Console console, int snakeWidth, int snakeHeight){
        //Initialize snake head at pos (100,100)
        head = new Node(100, 100, null);
        tail = head;

        this.console = console;

        this.gameWidth = console.getDrawWidth();
        this.gameHeight = console.getDrawHeight();

        this.snakeWidth = snakeWidth;
        this.snakeHeight = snakeHeight;

        //Initialize snake dir
        currentDirection = Direction.DOWN;
    }

    //GETTERS
    public int getHeadXPos(){
        return head.x;
    }
    public int getHeadYPos(){
        return head.y;
    }
    public int getSnakeWidth(){
        return snakeWidth;
    }
    public int getSnakeHeight(){
        return snakeHeight;
    }
    public Direction getDirection(){
        return currentDirection;
    }

    //SETTERS
    public void setDirection(Direction d){
        currentDirection = d;
    }

    //Append a new head to snake - used to grow snake and move snake
    private void appendNewHead(){
        //New head locations
        int x = head.x;
        int y = head.y;

        //Point to old head
        Node temp = head;

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
        //Since Java's mod function returns negatives, checks are in place
        x = x % gameWidth < 0 ? x % gameWidth + gameWidth : x % gameWidth;
        y = y % gameHeight < 0 ? y % gameHeight + gameHeight : y % gameHeight;

        //Create new head and point to old head
        Node newHead = new Node(x, y, temp);
        //Update head reference to new head
        head = newHead;
    }

    //SNAKE METHODS
    public void grow(){
        appendNewHead();
    }

    public void move(){
        appendNewHead();

        Node temp;
        //Delete tail by traversing the linked list, stopping at the node before the tail
        temp = head;
        while(temp.next != tail) temp = temp.next;

        //Remove reference to old tail
        temp.next = null;
        //Update tail reference
        tail = temp;
    }

    public void print(){
        console.clear();

        //Print snake body
        console.setColor(Color.GREEN);
        Node temp = head;
        while(temp != null){
            console.drawRect(temp.x, temp.y, snakeWidth, snakeHeight);
            temp = temp.next;
        }

        //Color code head
        console.setColor(Color.GREEN);
        console.fillRect(head.x, head.y, snakeWidth, snakeHeight);
    }

    public boolean checkCollision(){
        Node temp = head.next;
        boolean collision = false;

        //Check collision by traversing linked list
        while(temp != null && !collision){
            //If snake head is within the boundaries of its own body, a collision has occured
            if(head.x < temp.x + snakeWidth
                    && head.y < temp.y + snakeHeight
                    && temp.x < head.x + snakeWidth
                    && temp.y < head.y + snakeHeight)
                collision = true;

            else temp = temp.next;
        }

        return collision;
    }
}