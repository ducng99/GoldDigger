package golddigger.Objects;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maxhyt
 */
public enum Types {
    DIAMOND(1),
    GOLD(2),
    HEART(3),
    MINE(4),
    SKY(5),
    DIRT(6),
    CHEST(7);
    
    private static Map map = new HashMap<>();
    
    private int ID;
    
    private Types(int ID)
    {
        this.setID(ID);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    /**
     * Generate values including a Types variable and its ID for map variable
     */
    public static void generateMap()
    {
        for (Types type : Types.values())
        {
            map.put(type, type.getID());
        }
    }
    
    public static Types valueOf(int ID)
    {
        return (Types)map.get(ID);
    }
    
    public static int getMax()
    {
        return map.size();
    }
}
