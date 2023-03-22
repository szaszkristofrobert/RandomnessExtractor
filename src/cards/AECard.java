package cards;

import gui.App;

import javax.swing.*;

public class AECard extends NISTCard{
    public AECard(App app){
        super(app);
        this.n = 11;
        JLabel label = new JLabel("Approximate Entropy");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
