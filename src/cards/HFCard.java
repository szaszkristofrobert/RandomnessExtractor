package cards;

import gui.App;

import javax.swing.*;

public class HFCard extends ExtractorCard{
    public HFCard(App app){
        super(app);
        this.n = 5;
        JLabel label = new JLabel("HF");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
