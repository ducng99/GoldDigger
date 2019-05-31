package InfoScreen;

import golddigger.GameDB;
import golddigger.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duc Nguyen 17974984
 */
public class ScoreBoard extends Observable {
    private ArrayList<Player> players;
    
    public ScoreBoard()
    {
        players = GameDB.getAllPlayers();
    }
    
    public void showBoard()
    {
        JFrame frame = new JFrame("Scoreboard of the Diggers");
        frame.getContentPane().setLayout(null);
        frame.setSize(300, 400);
        
        DefaultTableModel tableModel = new DefaultTableModel(new Object[] {"Name", "Score"}, players.size());
        
        for (Player p : players)
        {            
            tableModel.addRow(new Object[] {p.getName(), p.getScore()});
        }
        
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(300, 400);
        scrollPane.setLocation(0, 0);
        frame.add(scrollPane);
        
        frame.setVisible(true);
        
        /*setChanged();
        notifyObservers("NOTI:" + sb);*/
    }
}
