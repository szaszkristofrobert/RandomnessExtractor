package cards;

import file_manager.FileManager;
import gui.App;

import javax.swing.*;
import java.awt.*;

public class FrequencyCard extends NISTCard{
    public FrequencyCard(App app){
        super(app);
        this.n = 1;
        this.label = "Frequency";
        JLabel label = new JLabel(this.label);
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {}
}
