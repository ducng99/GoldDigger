package InfoScreen;

import golddigger.GameDB;
import golddigger.GameMain;
import golddigger.Player;
import golddigger.Utils;
import java.util.Observable;

/**
 *
 * @author Duc Nguyen 17974984
 */
public class InfoModel extends Observable {
    public void CreateGame(String name)
    {
        Player player = GameDB.getPlayer(name);
        
        if (player != null)
        {
            setChanged();
            notifyObservers("NOTI:Your previous highscore is " + player.getScore());
            Utils.log("Your previous highscore is " + player.getScore());
        }
        else
            player = new Player(name);
        
        setChanged();
        notifyObservers("GAMESTART");
        
        //Initialize MVC
        GameMain game = new GameMain(player);
    }
    
    public boolean checkName(String name)
    {
        if (name.length() > 0 && name.length() < 17)
        {
            char[] nameC = name.toLowerCase().toCharArray();
            
            for (char c : nameC)
            {
                if ((c < 'a' || c > 'z') && (c < '0' || c > '9'))
                {
                    Utils.log(("Name is invalid!"));
                    setChanged();
                    notifyObservers("NOTI:Name is invalid!");
                    return false;
                }
            }
            
            return true;
        }
        
        Utils.log("Name can be up to 16 characters only!");
        setChanged();
        notifyObservers("NOTI:Name can be up to 16 characters only!");
        return false;
    }
}
