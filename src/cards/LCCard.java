package cards;

import file_manager.FileManager;
import gui.App;

import javax.swing.*;
import java.awt.*;

public class LCCard extends NISTCard{
    private JTextField bField;
    public LCCard(App app){
        super(app);
        this.n = 15;
        this.label = "Linear Complexity";
        JLabel label = new JLabel(this.label);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel bPanel = new JPanel();
        bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.X_AXIS));

        bPanel.add(new JLabel("block length: "));
        bField = new JTextField("500");
        bField.setMaximumSize(new Dimension(50, 20));
        bPanel.add(bField);

        settingsPanel.add(bPanel);

        this.add(label);
        this.add(settingsPanel);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        FileManager fm = new FileManager();
        fm.setupNistRunner();
        fm.appendNistRunner(bField.getText() + "\n");
    }
}