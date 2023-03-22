package cards;

import gui.App;

import javax.swing.*;

public class RECard extends NISTCard{
    public RECard(App app){
        super(app);
        this.n = 12;
        JLabel label = new JLabel("Random Excursions");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}