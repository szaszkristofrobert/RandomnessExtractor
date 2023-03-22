package cards;

import gui.App;

import javax.swing.*;

public class FrequencyCard extends NISTCard{
    public FrequencyCard(App app){
        super(app);
        this.n = 1;
        JLabel label = new JLabel("Frequency");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
