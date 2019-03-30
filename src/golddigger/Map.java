
package golddigger;

import golddigger.Objects.*;
/**
 *
 * @author Duc Nguyen 17974984
 */
public class Map {
    private int gameHeight;
    private int gameWidth;
    
    private GameObject[][] map;
    
    public Map(int height, int width)
    {
        this.setGameHeight(height);
        this.setGameWidth(width);
        map = new GameObject[gameHeight][gameWidth];
    }
    
    public void generateMap()
    {
        Types.generateMap();
        int chestWidth = Utils.getRand(0, gameWidth - 1);
        
        for (int height = 0; height < map.length; height++)
        {
            for (int width = 0; width < map[height].length; width++)
            {
                if (height < 3)
                {
                    map[height][width] = new Sky();
                }
                else if (width == chestWidth && height == map.length - 1)
                {
                    map[height][width] = new Chest();
                }
                else
                {
                    map[height][width] = generateBlock();
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
