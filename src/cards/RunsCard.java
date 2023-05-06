package cards;

import gui.App;

import javax.swing.*;

public class RunsCard extends NISTCard{
    public RunsCard(App app){
        super(app);
        this.n = 4;
        this.label = "Runs";
        JLabel label = new JLabel(this.label);
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {}
}
