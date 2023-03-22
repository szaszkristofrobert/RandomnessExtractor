package cards;

import gui.App;

import javax.swing.*;

public class LongestRunCard extends NISTCard{
    public LongestRunCard(App app){
        super(app);
        this.n = 5;
        JLabel label = new JLabel("Longest Run of Ones");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
