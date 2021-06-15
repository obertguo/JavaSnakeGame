package ml.obertguo.Types;

public class Node{
    public int x;
    public int y;
    public Node next;

    /**
     * Initialize node
     * @param x int that represents the x pos of the node
     * @param y int that represents the y pos of the node
     * @param next reference to next node or null
     */
    public Node(final int x, final int y, Node next){
        this.x = x;
        this.y = y;
        this.next = next;
    }
}