package cards;

import gui.App;

import javax.swing.*;

public class RankCard extends NISTCard{
    public RankCard(App app){
        super(app);
        this.n = 6;
        JLabel label = new JLabel("Rank");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
