package cards;

import gui.App;

import javax.swing.*;

public class CumulativeSumsCard extends NISTCard{
    public CumulativeSumsCard(App app){
        super(app);
        this.n = 3;
        JLabel label = new JLabel("Cumulative Sums");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}