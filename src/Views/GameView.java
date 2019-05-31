package Views;

import Controllers.GameController;
import golddigger.GameMain;
import golddigger.Map;
import golddigger.Objects.GameObject;
import golddigger.Objects.Types;
import golddigger.Player;
import golddigger.Utils;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * View of the game GUI
 * @author Duc Nguyen 17974984
 */
public class GameView implements Observer {
    private static final int SIZE = 64;
    private Map map;
    private Player player;
    private JFrame frame = new JFrame("Gold Digger");
    private JPanel panel = new JPanel();
    private JLabel[][] blocks = new JLabel[GameMain.gameWidth][GameMain.gameHeight];
    private JLabel playerB = new JLabel();
    private JLabel closeButton = new JLabel();
    private JLabel scoreLabel = new JLabel();
    private JLabel lifeLabel = new JLabel();
    private static ImageIcon chestImg;
    private static ImageIcon diamondImg;
    private static ImageIcon dirtImg;
    private static ImageIcon goldImg;
    private static ImageIcon heartImg;
    private static ImageIcon mineImg;
    private static ImageIcon skyImg;
    private static ImageIcon playerImg;
    private static ImageIcon closeImg;
    
    public GameView(Map m, Player p)
    {
        try {
            chestImg = new ImageIcon("img/Chest.jpg");
            diamondImg = new ImageIcon("img/Diamond.jpg");
            dirtImg = new ImageIcon("img/Dirt.jpg");
            goldImg = new ImageIcon("img/Gold.jpg");
            heartImg = new ImageIcon("img/Heart.jpg");
            mineImg = new ImageIcon("img/Mine.jpg");
            skyImg = new ImageIcon("img/Sky.jpg");
            playerImg = new ImageIcon("img/Player.png");
            closeImg = new ImageIcon("img/CloseButton.png");
        } catch (Exception ex) {
            Utils.log("Cannot read images");
            return;
        }
        
        map = m;
        player = p;
        
        System.out.println("View: initialize");
        
        frame.setSize(SIZE * GameMain.gameWidth, SIZE * GameMain.gameHeight);
        frame.setLocation(300, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //There is a problem with windows top bar causing frame padding, so I remove it for the sake of beauty
        frame.setUndecorated(true);
        
        panel.setLayout(null);
        frame.setContentPane(panel);
        
        playerB.setIcon(playerImg);
        playerB.setSize(SIZE, SIZE);
        playerB.setLocation(SIZE * player.getPos()[0], SIZE * player.getPos()[1]);
        panel.add(playerB);
        panel.setComponentZOrder(playerB, 0);
        
        closeButton.setIcon(closeImg);
        closeButton.setSize(SIZE, SIZE);
        closeButton.setLocation(SIZE * (GameMain.gameWidth - 1), 0);
        closeButton.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent me) {
                        System.exit(0);
                    }                    
                });
        panel.add(closeButton);
        panel.setComponentZOrder(closeButton, 1);
        
        scoreLabel.setSize(SIZE * 5, SIZE);
        scoreLabel.setLocation(0, 0);
        scoreLabel.setText("Score:" + player.getScore());
        scoreLabel.setFont(new Font("Courier New", Font.BOLD, 32));
        scoreLabel.setForeground(Color.YELLOW);
        panel.add(scoreLabel);
        panel.setComponentZOrder(scoreLabel, 2);
        
        lifeLabel.setSize(SIZE * 2, SIZE);
        lifeLabel.setLocation(SIZE * (GameMain.gameWidth / 2), 0);
        lifeLabel.setFont(new Font("Courier New", Font.BOLD, 32));
        String lifeCount = "";
        for (int life = 0; life < player.getLife(); life++)
            lifeCount += "♥";
        lifeLabel.setText(lifeCount);
        lifeLabel.setForeground(Color.YELLOW);
        panel.add(lifeLabel);
        panel.setComponentZOrder(lifeLabel, 3);
        
        for (int width = 0; width < blocks.length; width++)
        {
            for (int height = 0; height < blocks[width].length; height++)
            {
                blocks[width][height] = new JLabel();
                blocks[width][height].setIcon(getBlockType(width, height));
                blocks[width][height].setSize(SIZE, SIZE);
                blocks[width][height].setLocation(width * SIZE, height * SIZE);
                panel.add(blocks[width][height]);
                panel.setComponentZOrder(blocks[width][height], 4);
            }
        }
        
        frame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object obj) {
        String noti = (String)obj;
        if (noti.startsWith("BLOCK:"))
        {
            String[] posS = ((String)obj).substring("BLOCK:".length()).split("-");
            int[] pos = new int[] {Integer.valueOf(posS[0]), Integer.valueOf(posS[1])};
            
            blocks[pos[0]][pos[1]].setIcon(getBlockType(pos[0], pos[1]));
        }
        else if (noti.startsWith("SCORE:"))
        {
            String score = noti.substring("SCORE:".length());
            scoreLabel.setText(score);
        }
        else if (noti.startsWith("LIFE:"))
        {
            int life = Integer.valueOf(noti.substring("LIFE:".length()));
            String lifeCount = "";
            for (int i = 0; i < life; i++)
                lifeCount += "♥";
            lifeLabel.setText(lifeCount);
        }
        else
        {
            playerB.setLocation(SIZE * player.getPos()[0], SIZE * player.getPos()[1]);
        }
    }
    
    public void addController(GameController controller)
    {
        Utils.log("View: adding controllers");
        
        if (controller.getClass().equals(GameController.class))
        {
            frame.addKeyListener(controller);
        }
    }
    
    private ImageIcon getBlockType(int width, int height)
    {
        GameObject block = map.getBlock(new int[] {width, height});
        Types blockType = block.getType();
        
        if (blockType == Types.SKY)
            return skyImg;
        
        if (!block.isDiscovered())
        {
            switch (blockType)
            {
                case DIAMOND:
                    return diamondImg;
                case GOLD:
                    return goldImg;
                case HEART:
                    return heartImg;
                case MINE:
                    return mineImg;
                case DIRT:
                    return dirtImg;
                case CHEST:
                    return chestImg;
                default:
                    return null;
            }
        }
        else
            return dirtImg;
    }
}
