package cards;

import gui.App;

import javax.swing.*;
import java.awt.*;

public class OTCard extends NISTCard{
    private JTextField bField;
    public OTCard(App app){
        super(app);
        this.n = 9;
        this.label = "Overlapping Templates";
        JLabel label = new JLabel(this.label);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel bPanel = new JPanel();
        bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.X_AXIS));

        bPanel.add(new JLabel("block length: "));
        bField = new JTextField("9");
        bField.setMaximumSize(new Dimension(50, 20));
        bPanel.add(bField);

        settingsPanel.add(bPanel);

        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {}
}
