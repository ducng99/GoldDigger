package golddigger;

import InfoScreen.*;
import Controllers.*;
import Views.GameView;
/**
 * 
 * @author Duc Nguyen 17974984
 */
public class GameMain {
    private static Map map;
    private static Timer timer = new Timer();
    
    public static final int gameWidth = 9;
    public static final int gameHeight = 15;
    
    public GameMain(Player player)
    {
        GameView gameView = new GameView(map, player, timer);
        
        GameController.addMap(map);
        GameController.addPlayer(player);
        
        GameController gameController = new GameController();
        
        map.addObserver(gameView);
        player.addObserver(gameView);
        
        gameView.addController(gameController);
    }
    
    public static void main(String[] args) {        
        GameDB.establishConnection();
        GameDB.initializeDB();
        
        map = new Map(gameWidth, gameHeight);
        map.generateMap();
        
        InfoScreen();
        
        printInstructions();
        printHelp();
        
        //endGame();
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
    
    private static void InfoScreen()
    {
        InfoView view = new InfoView();
        InfoModel model = new InfoModel();
        ScoreBoard sb = new ScoreBoard();
        ScoreBoardController sbc = new ScoreBoardController();
        PlayButtonController pbc = new PlayButtonController();
        
        sbc.addModel(sb);
        
        view.addSBController(sbc);
        view.addPlayController(pbc);
        
        pbc.addModel(model);
        pbc.addView(view);
        model.addObserver(view);
        sb.addObserver(view);
    }
}
