package InfoScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Duc Nguyen 17974984
 */
public class PlayButtonController implements ActionListener {
    private InfoModel model;
    private InfoView view;
    
    public void addView(InfoView v)
    {
        view = v;
    }
    
    public void addModel(InfoModel m)
    {
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (model.checkName(view.nameField.getText()))
            model.CreateGame(view.nameField.getText());
    }
    
}
