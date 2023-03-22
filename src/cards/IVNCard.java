package cards;

import gui.App;

import javax.swing.*;

public class IVNCard extends ExtractorCard{
    public IVNCard(App app){
        super(app);
        this.n = 3;
        JLabel label = new JLabel("IVN");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        //TODO
    }
}
