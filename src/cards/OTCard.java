package cards;

import gui.App;

import javax.swing.*;

public class OTCard extends NISTCard{
    public OTCard(App app){
        super(app);
        this.n = 9;
        JLabel label = new JLabel("Overlapping Templates");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
