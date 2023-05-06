package cards;

import gui.App;

import javax.swing.*;

public class USCard extends NISTCard{
    public USCard(App app){
        super(app);
        this.n = 10;
        this.label = "Universal Statistical";
        JLabel label = new JLabel(this.label);
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {}
}
