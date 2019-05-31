package Controllers;

import golddigger.Map;
import golddigger.Objects.GameObject;
import golddigger.Objects.Types;
import golddigger.Player;
import golddigger.Utils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * One controller for both player and map. This is to avoid duplicating checkBlock function
 * @author Duc Nguyen 17974984
 */
public class GameController extends KeyAdapter {
    protected static Map map;
    protected static Player player;

    public static void addPlayer(Player p) {
        player = p;
    }
    
    public static void addMap(Map m)
    {
        map = m;
    }
        
    /**
     * Check which type of block player is on and display info attached to each type
     */
    private static boolean checkBlock()
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
                    
                    Utils.log("\nYOU ARE NOW OFFICIALLY A\n" +
                            "   ______                             __\n" +
                            "  /      |                           /  |\n" +
                            " /       |_________________________ /   |\n" +
                            "|        |___G O L D D I G G E R___|    |\n" +
                            " \\       |                          \\   |\n" +
                            "  \\______|                           \\__|");
                    return true;
                case SKY:
                case DIRT:
                default:
                    break;
            }
        }
        
        return false;
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                player.moveLeft();
                map.isEnded(checkBlock());
                break;
            case KeyEvent.VK_RIGHT:
                player.moveRight();
                map.isEnded(checkBlock());
                break;
            case KeyEvent.VK_DOWN:
                player.moveDown();
                map.isEnded(checkBlock());
                break;
            default:
                break;
        }
    }
}
