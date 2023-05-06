package cards;

import gui.App;

import javax.swing.*;

public class LongestRunCard extends NISTCard{
    public LongestRunCard(App app){
        super(app);
        this.n = 5;
        this.label = "Longest Run of Ones";
        JLabel label = new JLabel(this.label);
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {}
}
