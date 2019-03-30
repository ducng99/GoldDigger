package golddigger;

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
    
    public static final int gameHeight = 9;
    public static final int gameWidth = 15;
    
    private static boolean gameInProgress;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        Utils.log("What is your name?");
        String name = scan.nextLine();
        
        player = new Player(name);
        map = new Map(gameHeight, gameWidth);
        map.generateMap();
        
        gameInProgress = true;
        printInstructions();
        printHelp();
        Utils.log(player.toString());
        
        timer.start();
        View.drawMap(map, player.getPos());
        
        while (gameInProgress)
        {
            System.out.print("\nEnter command: ");
            String command = scan.nextLine();
            
            switch(command)
            {
                case "left":
                    player.moveLeft();
                    checkBlock();
                    break;
                case "right":
                    player.moveRight();
                    checkBlock();
                    break;
                case "down":
                    player.moveDown();
                    checkBlock();
                    break;
                case "help":
                    printHelp();
                    break;
                case "ins":
                    printInstructions();
                    break;
                case "exit":
                    gameInProgress = false;
                    timer.stopTimer();
                    break;
                default:
                    Utils.log("Invalid command");
                    break;
            }
            Utils.log(player.toString() + " Time: " + timer.getTime());
            View.drawMap(map, player.getPos());
        }
        timer.stopTimer();
        Utils.log("Your Final Score is " + calculateFinalScore());
        Utils.log("Game ended!");
    }
    
    private static void checkBlock()
    {
        GameObject curBlock = map.getBlock(player.getPos());
        Types blockType = curBlock.getType();
        
        if (!curBlock.isDiscovered())
        {
            curBlock.setDiscovered(true);
            
            switch (blockType)
            {
                case DIAMOND:
                case GOLD:
                    player.setScore(player.getScore() + curBlock.getValue());
                    Utils.log("Player added " + curBlock.getValue() + " scores!");
                    
                    map.setBlock(player.getPos(), curBlock);
                    break;
                case HEART:
                    player.setLife(player.getLife() + curBlock.getValue());
                    Utils.log("Player added " + curBlock.getValue() + " life");
                    
                    map.setBlock(player.getPos(), curBlock);
                    break;
                case MINE:
                    player.setLife(player.getLife() - 1);
                    player.setScore(player.getScore() - curBlock.getValue());
                    Utils.log("Player steps on a mine. Player lost 1 life and " + curBlock.getValue() + " scores");
                    
                    map.setBlock(player.getPos(), curBlock);
                    break;
                case CHEST:
                    player.setScore(player.getScore() + curBlock.getValue());
                    Utils.log("Player added " + curBlock.getValue() + " scores!");
                    
                    map.setBlock(player.getPos(), curBlock);
                    
                    gameInProgress = false;
                case SKY:
                case DIRT:
                default:
                    break;
            }
        }
    }
    
    private static int calculateFinalScore()
    {
        return player.getScore() - (int)(timer.getTime() / 5.);
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
