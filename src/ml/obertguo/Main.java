package ml.obertguo;

import hsa_ufa.Console;

public class Main {
    private static final int gameWidth = 400;
    private static final int gameHeight = 400;
    private static final int fontSize = 16;

    private static final int foodWidth = 10;
    private static final int foodHeight = 10;

    private static final int snakeWidth = 7;
    private static final int snakeHeight = 7;

    public static void main(String[] args){
        //Initialize console
        Console console = new Console(gameWidth, gameHeight, fontSize);
        WelcomeWindow welcomeWindow = new WelcomeWindow(console);

        //Retrieve player name
        String name = welcomeWindow.getName();

        startGame(console, name);
    }

    public static void startGame(Console console, String name){
        try {
            //Create new snake and food
            Snake snake = new Snake(console, snakeWidth, snakeHeight);
            Food food = new Food(console, foodWidth, foodHeight);
            GameWindow gameWindow  = new GameWindow(console, snake, food);
            //Add a slight delay so that user is ready
            Thread.sleep(500);

            gameWindow.startGame();
        }
        catch(Exception e){
            new GameOverWindow(console, name, e.getMessage());
        }
    }
}

