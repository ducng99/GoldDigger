
package golddigger;

import golddigger.Objects.*;
import java.util.Observable;
/**
 *
 * @author Duc Nguyen 17974984
 */
public class Map extends Observable {
    private int gameWidth;
    private int gameHeight;
    
    private GameObject[][] map;
    
    public Map(int width, int height)
    {
        this.setGameWidth(width);
        this.setGameHeight(height);
        map = new GameObject[gameWidth][gameHeight];
    }
    
    public void generateMap()
    {
        Types.generateMap();
        int chestWidth = Utils.getRand(0, gameWidth - 1);
        
        for (int width = 0; width < map.length; width++)
        {
            for (int height = 0; height < map[width].length; height++)
            {
                if (height < 3)
                {
                    map[width][height] = new Sky();
                }
                else if (width == chestWidth && height == map[width].length - 1)
                {
                    map[width][height] = new Chest();
                }
                else
                {
                    map[width][height] = generateBlock();
                }
            }
        }
    }
    
    /**
     * This method generate a random number and base on the number, it returns a {@link GameObject} object
     * @return a {@link GameObject} object
     */
    private GameObject generateBlock()
    {
        int random = Utils.getRand(1, 100);
        
        if (random < 1)
        {
            return new Diamond();
        }
        else if (random < 5)
        {
            return new Gold();
        }
        else if (random < 10)
        {
            return new Heart();
        }
        else if (random < 13)
        {
            return new Mine();
        }
        else
        {
            return new Dirt();
        }
    }
    
    public GameObject getBlock(int[] pos)
    {
        return map[pos[0]][pos[1]];
    }
    
    public void setBlock(int[] pos, GameObject object)
    {
        map[pos[0]][pos[1]] = object;
        setChanged();
        notifyObservers("BLOCK:" + pos[0] + "-" + pos[1]);
    }

    public boolean setGameHeight(int gameHeight) {
        if (gameHeight > 0)
        {
            this.gameHeight = gameHeight;
            return true;
        }
        else
        {
            Utils.log("gameHeight is invalid!");
            return false;
        }
    }

    public boolean setGameWidth(int gameWidth) {
        if (gameWidth > 0)
        {
            this.gameWidth = gameWidth;
            return true;
        }
        else
        {
            Utils.log("gameWidth is invalid!");
            return false;
        }
    }
}
