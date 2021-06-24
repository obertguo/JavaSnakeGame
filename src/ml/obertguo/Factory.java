package ml.obertguo;
import ml.obertguo.Types.Difficulty;
import java.awt.*;

public class Factory {

    /**
     * Generates colors to be used for checkered background depending on difficulty selected
     * @param difficulty Selected game difficulty
     * @return An array of two colors
     */
   public static Color[] generateColor(Difficulty difficulty){
       //By default, the colors scheme will suit the easy difficulty unless specified otherwise in switch statement
       Color[] colors = new Color[]{
               //Green color with opacity
               new Color(186, 202, 68, 100),

               //Slightly white color with opacity
               new Color(238, 238, 210, 100)
       };

       //For when the game starts up and difficulty isn't selected yet (null)
       if(difficulty == null) return colors;

        switch(difficulty){
            case EASY:
                break;
            case MEDIUM:
                //Yellow color with opacity
                colors[0] = new Color(238, 220, 130, 100);
                break;
            case HARD:
               //Red color with opacity
                colors[0] = new Color(	238, 130, 148, 100);
                break;
        }

        return colors;
   }

    /**
     * Generates game delay in ms depending on difficulty selected
     * @param difficulty Selected game difficulty
     * @return Game delay in ms
     */
   public static int generateGameDelayMS(Difficulty difficulty){
       //By default, the game delay is 50ms to suit the easy level
       int gameDelayMS = 50;

       switch(difficulty){
           case EASY:
               break;
           case MEDIUM:
               gameDelayMS = 35;
               break;
           case HARD:
               gameDelayMS = 25;
               break;
       }
       return gameDelayMS;
   }

    /**
     * Generates the number of food to be created for the game depending on difficulty
     * @param difficulty Game difficulty selected
     * @return Number of food to be generated
     */
   public static int generateNumFood(Difficulty difficulty){
       //By default, the num food is 5 to suit the easy level
       int numFood = 5;

       switch(difficulty){
           case EASY:
               break;
           case MEDIUM:
               numFood = 3;
               break;
           case HARD:
               numFood = 1;
               break;
       }

       return numFood;
   }

    /**
     * Generates the number of obstacles to be created for the game depending on difficulty
     * @param difficulty Game difficulty selected
     * @return Number of obstacles to be generated
     */
   public static int generateNumObstacles(Difficulty difficulty){
       //By default, the num obstacles is 50ms to suit the easy level
       int numObstacles = 5;

       switch(difficulty){
           case EASY:
               break;
           case MEDIUM:
               numObstacles = 10;
               break;
           case HARD:
               numObstacles = 20;
               break;
       }

       return numObstacles;
   }
}
