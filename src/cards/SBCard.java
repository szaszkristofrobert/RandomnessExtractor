package cards;

import gui.App;

import javax.swing.*;

public class SBCard extends ExtractorCard{
    public SBCard(App app){
        super(app);
        this.n = 7;
        JLabel label = new JLabel("SB");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
