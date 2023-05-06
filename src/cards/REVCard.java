package cards;

import gui.App;

import javax.swing.*;

public class REVCard extends NISTCard{
    public REVCard(App app){
        super(app);
        this.n = 13;
        this.label = "Random Excursions Variant";
        JLabel label = new JLabel(this.label);
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {}
}
