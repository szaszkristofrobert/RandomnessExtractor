package cards;

import gui.App;

import javax.swing.*;

public class REVCard extends NISTCard{
    public REVCard(App app){
        super(app);
        this.n = 13;
        JLabel label = new JLabel("Random Excursions Variant");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
