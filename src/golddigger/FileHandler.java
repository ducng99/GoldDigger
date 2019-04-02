package golddigger;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class used to handle File related functions
 * @author Duc Nguyen 17974984
 */
public class FileHandler {
    private static String dataFileName = "scores.txt";
    private static File dataFile = new File(dataFileName);
    private static Map<String,Integer> players = new HashMap<String,Integer>();
    
    /**
     * Create score file if it doesn't exist
     */
    private static void createFile()
    {
        try {
            dataFile.createNewFile();   //Create data file if not existed
        } catch (IOException ex) { }
    }
    
    /**
     * Load players\' names and scores from file
     * @return a HashMap contains players\' names and scores
     */
    public static Map<String,Integer> loadData()
    {
        try {
            createFile();
            Scanner scanner = new Scanner(dataFile);
            
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] playerData = line.split(" : ");
                if (playerData.length == 2)
                    players.put(playerData[0], Integer.valueOf(playerData[1]));
            }
            
            scanner.close();
            
            return players;
        } catch (FileNotFoundException ex) {
            return null;
        }
    }
    
    /**
     * Add player\'s name and score to the HashMap and save as file
     * @param name
     * @param score 
     */
    public static void saveData(String name, int score)
    {
        players.put(name, score);
        try
        {
            FileWriter fileWriter = new FileWriter(dataFile);
            
            for (Entry<String,Integer> entry : players.entrySet())
            {
                fileWriter.write(entry.getKey() + " : " + entry.getValue());
            }
            
            fileWriter.close();
        }
        catch (IOException e)
        {
            Utils.log("Cannot access data file!");
        }
    }
    
    /**
     * Check whether given name exists
     * @param name
     * @return true if exists, false otherwise
     */
    public static boolean checkPlayer(String name)
    {
        return players.containsKey(name);
    }
    
    public static int getScore(String name)
    {
        return players.get(name);
    }
}
