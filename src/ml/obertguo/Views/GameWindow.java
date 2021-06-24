package ml.obertguo.Views;
import hsa_ufa.Console;
import ml.obertguo.Factory;
import ml.obertguo.Objects.Food;
import ml.obertguo.Main;
import ml.obertguo.Objects.Obstacle;
import ml.obertguo.Objects.Snake;
import ml.obertguo.Types.Coordinate;
import ml.obertguo.Types.Difficulty;
import ml.obertguo.Types.Direction;
import java.awt.*;
import java.util.HashSet;

public class GameWindow {
    private static final int SNAKEWIDTH = 7;
    private static final int SNAKEHEIGHT = 7;

    private static final int FOODWIDTH = 10;
    private static final int FOODHEIGHT = 10;
    private static int NUMFOOD;

    private static final int OBSTACLEWIDTH = 20;
    private static final int OBSTACLEHEIGHT = 20;
    private static int NUMOBSTACLES;

    private final Snake snake;
    private final Food[] foods;
    private final Obstacle[] obstacles;

    private final HashSet<Coordinate> objectsSet;

    private final Console console;
    private int score = 0;

    /**
     * Constructor to initialize game objects
     * @param console A reference to an already initialized HSA_UFA console
     */
    public GameWindow(Console console, Difficulty difficulty){
        this.console = console;
        console.setBackgroundColor(Color.BLACK);
        console.clear();

        NUMOBSTACLES = Factory.generateNumObstacles(difficulty);
        NUMFOOD = Factory.generateNumFood(difficulty);

        //Initialize new snake, and create foods + obstacles
        this.snake = new Snake(console, SNAKEWIDTH, SNAKEHEIGHT);

        //Initialize obstacle and food pos in a set of coordinates
        this.objectsSet = new HashSet<Coordinate>();

        //initialize obstacle and food array
        this.obstacles = new Obstacle[NUMOBSTACLES];
        this.foods = new Food[NUMFOOD];

        //Initialize snake body (repetition structure - for loop)
        for(int i = 0; i < 10; ++i)
            snake.grow();

        //Generate obstacles
        for(int i = 0; i < NUMOBSTACLES; ++i){
            obstacles[i] = new Obstacle(console, OBSTACLEWIDTH, OBSTACLEHEIGHT);
            obstacles[i].generate(objectsSet, snake);

            //add pos to objects set
            objectsSet.add(obstacles[i].getSpawnCoordinates());
        }

        //Generate food
        for(int i = 0; i < NUMFOOD; ++i){
            foods[i] = new Food(console, FOODWIDTH, FOODHEIGHT);
            foods[i].generate(objectsSet, snake);

            //add pos to objects set
            objectsSet.add(foods[i].getSpawnCoordinates());
        }
    }

    /**
     * Renders game objects on HSA_UFA console
     * @param
     * @return
     */
    public void render(){
        //Lock current thread so that the console clear and printing is
        //buffered before displaying to user (avoid flickers)
        synchronized (console){
            console.clear();
            Main.drawCheckeredBackground(console);

            //Iterate through obstacles and foods array and print to console
            snake.print();
            for(int i = 0; i < foods.length; ++i)
                foods[i].print();

            for(int i = 0; i < obstacles.length; ++i)
                obstacles[i].print();

            //Print score
            console.setColor(Color.WHITE);
            console.setFont(new Font("SansSerif", Font.BOLD, 18));
            console.drawString("Score: " + score, 0, 14);
        }
    }

    /**
     * Process user input by changing snake direction based on arrow keys pressed
     * @param
     * @return
     */
    public void processInput(){
        //Move snake based on direction of arrow keys while also making sure the snake does not collide into itself
        //(e,g, snake moving left and then the right arrow key is pressed)

        if(console.isKeyDown(Console.VK_LEFT)){
            if(snake.getDirection() != Direction.RIGHT) snake.setDirection(Direction.LEFT);
        }
        else if(console.isKeyDown(Console.VK_UP)){
            if(snake.getDirection() != Direction.DOWN) snake.setDirection(Direction.UP);
        }
        else if(console.isKeyDown(Console.VK_DOWN)) {
            if (snake.getDirection() != Direction.UP) snake.setDirection(Direction.DOWN);
        }
        else if(console.isKeyDown(Console.VK_RIGHT)){
            if(snake.getDirection() != Direction.LEFT) snake.setDirection(Direction.RIGHT);
        }
    }

    /**
     * Update game state by moving snake and obstacle positions
     * @param
     * @return
     */
    public void updateGame(){
        snake.move();

        for(int i = 0; i < obstacles.length; ++i)
            obstacles[i].move();
    }

    /**
     * Returns a boolean to determine if the snake has collided into an obstacle
     * @param
     * @return
     */
    public boolean checkObstacleCollision(){
        //Iterate over obstacles to check if a collision has occurred
        for(int i = 0; i < obstacles.length; ++i){
            if(obstacles[i].checkCollision(snake))
                return true;
        }

        return false;
    }

    /**
     * Returns a boolean to determine if the snake has collided into itself
     * @param
     * @return boolean
     */
    public boolean checkSnakeCollision(){
        return snake.checkCollision();
    }

    /**
     * Handle food collision (if any)
     * @param
     * @return
     */
    public void checkFoodCollision(){
        //Iterate through the foods array and checking if the snake has collided with any
        for(int i = 0; i < foods.length; ++i){
            if(foods[i].checkCollision(snake)){
                //Grow snake if snake collides with food, and increment score
                snake.grow();
                ++score;

                //Generate new spawn positions for food
                foods[i].generate(objectsSet, snake);

                //Generate new obstacle layout
                for(int j = 0; j < obstacles.length; ++j)
                    obstacles[j].generate(objectsSet, snake);
            }
        }
    }

    /**
     * Returns user's current score based on how many food they've collected
     * @param
     * @return
     */
    public int getScore(){
        return score;
    }
}
