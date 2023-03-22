package cards;

import gui.App;

import javax.swing.*;

public class SCard extends NISTCard{
    public SCard(App app){
        super(app);
        this.n = 14;
        JLabel label = new JLabel("Serial");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
