package Views;

import Controllers.GameController;
import golddigger.GameMain;
import golddigger.Map;
import golddigger.Player;
import golddigger.Utils;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * View of the game GUI
 * @author Duc Nguyen 17974984
 */
public class GameView implements Observer {
    private static final int SIZE = 64;
    private Map map;
    private Player player;
    private JFrame frame = new JFrame("Gold Digger");
    private JLabel[][] blocks = new JLabel[GameMain.gameWidth][GameMain.gameHeight];
    private JLabel playerB = new JLabel("Player");
    
    public GameView(Map m, Player p)
    {
        map = m;
        player = p;
        
        System.out.println("View: initialize");
        
        frame.setLayout(null);
        frame.setSize(SIZE * GameMain.gameWidth, SIZE * GameMain.gameHeight);
        frame.setLocation(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        for (int width = 0; width < blocks.length; width++)
        {
            for (int height = 0; height < blocks[width].length; height++)
            {
                blocks[width][height] = new JLabel(width + ":" + height);
                blocks[width][height].setText(getBlockType(width, height));
                //blocks[width][height].setIcon(new ImageIcon());
                blocks[width][height].setSize(SIZE, SIZE);
                blocks[width][height].setLocation(width * SIZE, height * SIZE);
                frame.add(blocks[width][height]);
            }
        }
        
        playerB.setSize(SIZE, SIZE);
        playerB.setLocation(SIZE * player.getPos()[0], SIZE * player.getPos()[1]);
        frame.add(playerB);
        
        frame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addController(GameController controller)
    {
        Utils.log("View: adding controllers");
        
        if (controller.getClass().equals(GameController.class))
        {
            frame.addKeyListener(controller);
        }
    }
    
    private String getBlockType(int width, int height)
    {
        switch (map.getBlock(new int[] {width, height}).getType())
        {
            case SKY:
                return "Sky";
            case DIAMOND:
                return "Diamond";
            case GOLD:
                return "Gold";
            case HEART:
                return "Heart";
            case MINE:
                return "Mine";
            case DIRT:
                return "Dirt";
            case CHEST:
                return "Chest";
            default:
                return "";
        }
    }
}
