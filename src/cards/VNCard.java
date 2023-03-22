package cards;

import gui.App;

import javax.swing.*;

public class VNCard extends ExtractorCard{
    public VNCard(App app){
        super(app);
        this.n = 2;
        JLabel label = new JLabel("VN");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
