package cards;

import gui.App;

import javax.swing.*;

public class RunsCard extends NISTCard{
    public RunsCard(App app){
        super(app);
        this.n = 4;
        JLabel label = new JLabel("Runs");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
