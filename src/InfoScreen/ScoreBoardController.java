package InfoScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Duc Nguyen 17974984
 */
public class ScoreBoardController implements ActionListener {
    private ScoreBoard model;
    
    public void addModel(ScoreBoard m)
    {
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        model.showBoard();
    }
    
}
