package cards;

import gui.App;

import javax.swing.*;

public class LCCard extends NISTCard{
    public LCCard(App app){
        super(app);
        this.n = 15;
        JLabel label = new JLabel("Linear Complexity");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}