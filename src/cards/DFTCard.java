package cards;

import gui.App;

import javax.swing.*;

public class DFTCard extends NISTCard{
    public DFTCard(App app){
        super(app);
        this.n = 7;
        JLabel label = new JLabel("Discrete Fourier Transform");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
