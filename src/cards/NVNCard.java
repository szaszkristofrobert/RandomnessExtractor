package cards;

import gui.App;

import javax.swing.*;

public class NVNCard extends ExtractorCard{
    public NVNCard(App app){
        super(app);
        this.n = 4;
        JLabel label = new JLabel("NVN");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
