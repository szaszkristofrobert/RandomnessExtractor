package cards;

import gui.App;

import javax.swing.*;

public class RECard extends NISTCard{
    public RECard(App app){
        super(app);
        this.n = 12;
        this.label = "Random Excursions";
        JLabel label = new JLabel(this.label);
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {}
}