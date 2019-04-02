package golddigger;

import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Duc Nguyen 17974984
 */
public class ScoreBoard {
    private Map<String,Integer> players;
    
    public ScoreBoard(Map<String,Integer> map)
    {
        players = map;
    }
    
    public void showBoard()
    {
        Utils.log("\nSCORE BOARD");
        Utils.log("+================+=======+\n"
                + "|      Name      | Score |\n"
                + "+================+=======+");
        for (Entry<String,Integer> entry : players.entrySet())
        {
            System.out.print("|" + entry.getKey());
            for (int i = 0; i < 16 - entry.getKey().length(); i++)
            {
                System.out.print(" ");
            }
            System.out.print("|" + entry.getValue());
            for (int i = 0; i < 7 - entry.getValue().toString().length(); i++)
            {
                System.out.print(" ");
            }
            Utils.log("|");
        }
        Utils.log("+================+=======+");
    }
}
