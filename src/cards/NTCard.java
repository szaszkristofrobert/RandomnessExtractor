package cards;

import gui.App;

import javax.swing.*;

public class NTCard extends NISTCard{
    public NTCard(App app){
        super(app);
        this.n = 8;
        JLabel label = new JLabel("Non-Overlapping Templates");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
