package golddigger;

import golddigger.Objects.GameObject;
import golddigger.Objects.Types;

/**
 *
 * @author Duc Nguyen 17974984
 */
public class View {
    
    public static void drawMap(Map map, int[] pos)
    {
        drawBorder();
        for (int height = 0; height < GameMain.gameHeight; height++)
        {
            if (map.getBlock(new int[] {height, 0}).getType() != Types.SKY)
                drawLine();
            else
            {
                System.out.print("|");
                for (int width = 0; width < GameMain.gameWidth; width++)
                {
                    if (pos[0] == height && pos[1] == width)
                        System.out.print("• ");
                    else
                    {
                        if (width == GameMain.gameWidth - 1)
                            System.out.print(" ");
                        else
                            System.out.print("  ");
                    }
                }
                System.out.println("|");
                continue;
            }
            
            for (int width = 0; width < GameMain.gameWidth; width++)
            {
                if (pos[0] == height && pos[1] == width)
                {
                    System.out.print("|•");
                    continue;
                }
                GameObject curBlock = map.getBlock(new int[] {height, width});
                Types blockType = curBlock.getType();
                
                switch (blockType)
                {
                    case DIAMOND:
                        if (!curBlock.isDiscovered())
                            System.out.print("|D");
                        else
                            System.out.print("| ");
                        break;
                    case GOLD:
                        if (!curBlock.isDiscovered())
                            System.out.print("|G");
                        else
                            System.out.print("| ");
                        break;
                    case HEART:
                        if (!curBlock.isDiscovered())
                            System.out.print("|H");
                        else
                            System.out.print("| ");
                        break;
                    case MINE:
                        if (!curBlock.isDiscovered())
                            System.out.print("|B");
                        else
                            System.out.print("| ");
                        break;
                    case CHEST:
                        if (!curBlock.isDiscovered())
                            System.out.print("|C");
                        else
                            System.out.print("| ");
                        break;
                    default:
                        System.out.print("| ");
                        break;
                }
            }
            System.out.println("|");
        }
        drawBorder();
    }
    
    private static void drawLine()
    {
        System.out.print("|");
        for (int i = 0; i < GameMain.gameWidth * 2 - 1; i++)
        {
            System.out.print("-");
        }
        System.out.println("|");
    }
    
    private static void drawBorder()
    {      
        System.out.print("+");
        for (int i = 0; i < GameMain.gameWidth * 2 - 1; i++)
        {
            System.out.print("-");
        }
        System.out.println("+");
    }
}
