package InfoScreen;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Sub window to display some info before game starts
 * @author Duc Nguyen 17974984
 */
public class InfoView implements Observer {
    private JFrame frame = new JFrame("Gold Digger");
    private JLabel nameLabel = new JLabel("Name:");
    private JLabel instructions = new JLabel();
    public JTextField nameField = new JTextField();
    private JButton scoreboardB = new JButton("View scoreboard");
    private JButton playButton = new JButton("Play");
    
    public InfoView()
    {
        frame.getContentPane().setLayout(null);
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        instructions.setSize(280, 300);
        instructions.setLocation(10, 10);
        //HTML for wrapping text
        instructions.setText("<html>" + "GAME INTRUCTIONS:<br>"
                + "You as a player need to collect Golds and Diamonds under the ground to get points, beware of Mines lying around! You will have 3 lives to play, there are Hearts that will give you 1 life. The game only ends when you reach the treasure chest. The longer you play the game, the less points you get, so RUN!!<br>"
                + "<br>Objects:<br>"
                + "Diamond<br>"
                + "Gold<br>"
                + "Heart<br>"
                + "Mine<br>"
                + "Treasure Chest" + "</html>");
        frame.add(instructions);
        
        nameLabel.setSize(100, 10);
        nameLabel.setLocation(10, 310);
        frame.add(nameLabel);
        
        nameField.setLocation(10, 330);
        nameField.setSize(265, 30);
        frame.add(nameField);
        
        playButton.setSize(130, 30);
        playButton.setLocation(10, 400);
        frame.add(playButton);
        
        scoreboardB.setSize(130, 30);
        scoreboardB.setLocation(145, 400);
        frame.add(scoreboardB);
        
        frame.setVisible(true);
    }
    
    public void addSBController(ScoreBoardController sbc)
    {
        scoreboardB.addActionListener(sbc);
    }
    
    public void addPlayController(PlayButtonController pbc)
    {
        playButton.addActionListener(pbc);
    }

    @Override
    public void update(Observable o, Object obj) {
        String info = (String) obj;
        
        if (info.startsWith("NOTI:"))
        {
            JOptionPane.showMessageDialog(frame, info.substring("NOTI:".length()), "Gold Digger", JOptionPane.PLAIN_MESSAGE);
        }
        else if (info.equals("GAMESTART"))
        {
            frame.setVisible(false);
        }
    }
    
}
