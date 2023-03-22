package cards;

import gui.App;

import javax.swing.*;

public class BlockFrequencyCard extends NISTCard{
    public BlockFrequencyCard(App app){
        super(app);
        this.n = 2;
        JLabel label = new JLabel("BlockFrequency");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}