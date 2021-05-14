package ml.obertguo;
import hsa_ufa.Console;

import java.awt.*;

public class GameWindow {
    private final Snake snake;
    private final Food food;
    private final int delayMs = 50;
    private final Console console;
    private int score = 0;

    public GameWindow(Console console, Snake snake, Food food){
        this.console = console;
        this.snake = snake;
        this.food = food;
    }

    public void startGame() throws Exception{
        console.setBackgroundColor(Color.BLACK);
        console.clear();

        food.generate();
        for(int i = 0; i < 10; ++i) snake.grow(); //initialize snake body

        try{
            while(true){
                //Lock current thread so that the console clear and printing is
                //buffered before displaying to user (avoid flickers)
                synchronized (console){
                    snake.print();
                    food.print();

                    //Print score
                    console.setColor(Color.WHITE);
                    console.setFont(new Font("SansSerif", Font.BOLD, 18));
                    console.drawString("Score: " + score, 0, 14);
                }

                if(console.isKeyDown(Console.VK_LEFT) && snake.getDirection() != Snake.Direction.RIGHT) snake.setDirection(Snake.Direction.LEFT);
                if(console.isKeyDown(Console.VK_UP) && snake.getDirection() != Snake.Direction.DOWN) snake.setDirection(Snake.Direction.UP);
                if(console.isKeyDown(Console.VK_DOWN) && snake.getDirection() != Snake.Direction.UP) snake.setDirection(Snake.Direction.DOWN);
                if(console.isKeyDown(Console.VK_RIGHT) && snake.getDirection() != Snake.Direction.LEFT) snake.setDirection(Snake.Direction.RIGHT);

                if(snake.checkCollision()) throw new Exception(String.valueOf(score));
                if(food.checkCollision(snake.getHeadXPos(), snake.getHeadYPos(), snake.getSnakeWidth(), snake.getSnakeHeight())) {
                    snake.grow();
                    ++score;
                    food.generate();
                }

                snake.move();
                Thread.sleep(delayMs);
            }
        }
        catch(Exception e){
            throw e;
        }
    }
}
