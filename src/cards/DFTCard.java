package cards;

import gui.App;

import javax.swing.*;

public class DFTCard extends NISTCard{
    public DFTCard(App app){
        super(app);
        this.n = 7;
        this.label = "Discrete Fourier Transform";
        JLabel label = new JLabel(this.label);
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {}
}
