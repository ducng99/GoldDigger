package golddigger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Initialize Derby with player table. This class includes functions for extracting and getting player's info.
 * @author Duc Nguyen 17974984
 */
public class GameDB {
    private static final String url = "jdbc:derby:Digger-DB;create=true";
    
    private static Connection conn;
    
    public static void establishConnection()
    {
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("DB: connected!");
        }
        catch (SQLException e)
        {
            Utils.log(e.getMessage());
            System.out.println("Unable to connect to database!");
            System.exit(0);
        }
    }
    
    public static void initializeDB()
    {
        try {
            Statement statement = conn.createStatement();
            
            String createPlayerTable = "CREATE TABLE Player ("
                    + "Name varchar(16) NOT NULL PRIMARY KEY, Score int)";
            
            statement.executeUpdate(createPlayerTable);
            
            System.out.println("DB: initialized!");
        }
        catch (SQLException e)
        {
            Utils.log(e.getMessage());
            System.out.println("Table exists");
        }
    }
    
    public static void createPlayer(Player player)
    {
        try {
            Statement statement = conn.createStatement();
            
            String createPlayer = "INSERT INTO Player values("
                    + player.getName() +"', " + player.getScore()
                    + ")";
            
            statement.executeUpdate(createPlayer);
            
            System.out.println("DB: New Player created");
        }
        catch (SQLException e)
        {
            Utils.log(e.getMessage());
            System.out.println("Player may existed");
        }
    }
    
    public static void updatePlayer(Player player)
    {
        try {
            Statement statement = conn.createStatement();
            String updatePlayerInfo = "UPDATE Player SET "
                    + "Score=" + player.getScore()
                    + " WHERE Name=" + player.getName();
            statement.executeUpdate(updatePlayerInfo);
        }
        catch (SQLException e)
        {
            Utils.log(e.getMessage());
        }
    }
    
    public static Player getPlayer(String name)
    {
        ResultSet rs = null;
        try {
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            
            String readPlayerInfo = "SELECT * FROM Player WHERE Name=" + name;
            
            rs = statement.executeQuery(readPlayerInfo);
            rs.first();
            
            int score = rs.getInt("Score");
            Player player = new Player(name, score);
            
            return player;
        }
        catch (SQLException e)
        {
            System.out.println("Player not found or data corrupted");
            return null;
        }
    }
    
    public static ArrayList<Player> getAllPlayers()
    {
        ArrayList<Player> list = new ArrayList<>();
        
        ResultSet rs = null;
        try {
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            
            String readPlayerInfo = "SELECT * FROM Player";
            
            rs = statement.executeQuery(readPlayerInfo);
            while (!rs.isAfterLast())
            {
                Player p = new Player(rs.getString("Name"), rs.getInt("Score"));
                list.add(p);
                rs.next();
            }
        }
        catch (SQLException e)
        {
            System.out.println("Player not found or data corrupted");
        }
        
        return list;
    }
    
    public static void removePlayer(Player player)
    {
        try {
            Statement statement = conn.createStatement();
            String removePlayer = "DELETE FROM Player WHERE Name=" + player.getName();
            statement.executeUpdate(removePlayer);
        }
        catch (SQLException e)
        {
            Utils.log(e.getMessage());
        }
    }
}
