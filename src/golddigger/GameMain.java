package golddigger;

import Controllers.*;
import Views.GameView;
import golddigger.Objects.*;
import java.util.Scanner;
/**
 * 
 * @author Duc Nguyen 17974984
 */
public class GameMain {
    private static Player player;
    private static Map map;
    private static Timer timer = new Timer();
    
    public static final int gameWidth = 9;
    public static final int gameHeight = 15;
    
    public GameMain()
    {
        GameView gameView = new GameView(map, player);
        
        GameController.addMap(map);
        GameController.addPlayer(player);
        
        GameController gameController = new GameController();
        
        map.addObserver(gameView);
        player.addObserver(gameView);
        
        gameView.addController(gameController);
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String name;
        boolean nameOK = false;
        
        GameDB.establishConnection();
        GameDB.initializeDB();
        
        map = new Map(gameWidth, gameHeight);
        map.generateMap();
        
        do
        {
            Utils.log("What is your name?");
            name = scan.nextLine();
            nameOK = checkName(name);
        }
        while (!nameOK);
        
        player = GameDB.getPlayer(name);
        
        if (player != null)
        {
            Utils.log("Your previous highscore is " + player.getScore());
        }
        else
            player = new Player(name);
        
        //Initialize MVC
        GameMain game = new GameMain();
        
        
        printInstructions();
        printHelp();
        Utils.log(player.toString());
        
        timer.start();
        
        Utils.log(player.toString() + " Time: " + timer.getTime());
        
        endGame();
    }
    
    /**
     * Display information at end game and save the information to file
     */
    private static void endGame()
    {
        timer.stopTimer();
        int finalScore = calculateFinalScore();
        //FileHandler.saveData(player.getName(), finalScore);
        Utils.log("Your Final Score is " + finalScore);
        Utils.log("Game ended!");
    }
    
    /**
     * Calculate final score using given equation
     * @return final score
     */
    private static int calculateFinalScore()
    {
        int score = player.getScore() - (int)(timer.getTime() / 5.);
        if (score < 0)
            score = 0;
        return score;
    }
    
    private static boolean checkName(String name)
    {
        if (name.length() > 0 && name.length() < 17)
        {
            char[] nameC = name.toLowerCase().toCharArray();
            
            for (char c : nameC)
            {
                if ((c < 'a' || c > 'z') && (c < '0' || c > '9'))
                {
                    Utils.log(("Name is invalid!"));
                    return false;
                }
            }
            
            return true;
        }
        
        Utils.log("Name can be up to 16 characters only!");
        return false;
    }
    
    private static void printHelp()
    {
        Utils.log("\nLIST OF COMMANDS:\n"
                + "left: player moves left 1 block\n"
                + "right: player moves right 1 block\n"
                + "down: player moves down 1 block\n"
                + "help: shows this help\n"
                + "ins: show game instructions");
    }
    
    private static void printInstructions()
    {
        Utils.log("\nGAME INTRUCTIONS:\n"
                + "You as a player need to collect Golds and Diamonds under the ground to get points, beware of Mines lying around! You will have 3 lives to play, there are Hearts that will give you 1 life. The game only ends when you reach the treasure chest. The longer you play the game, the less points you get, so RUN!!\n"
                + "\nNOTES:\n"
                + "D - Diamond\n"
                + "G - Gold\n"
                + "H - Heart\n"
                + "M - Mine\n"
                + "C - Treasure Chest");
    }
}
