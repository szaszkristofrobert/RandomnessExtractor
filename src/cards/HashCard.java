package cards;

import gui.App;

import javax.swing.*;

public class HashCard extends ExtractorCard{
    public HashCard(App app){
        super(app);
        this.n = 6;
        JLabel label = new JLabel("Hash");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
