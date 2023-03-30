package cards;

import javax.swing.*;

import gui.App;

import java.awt.*;

public abstract class Card extends JPanel {
    protected int n;
    App app;
    JButton buttonContinue;
    JTextField inputField;
    JTextField outputField;
    JPanel settingsPanel;
    public Card(App app, JTextField inputField, JTextField outputField){
        super();
        this.app = app;
        this.inputField = inputField;
        this.outputField = outputField;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        settingsPanel = new JPanel();
        settingsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonContinue = new JButton("Continue");
        buttonContinue.addActionListener(e -> {
            nextCard();
        });
        buttonContinue.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    public abstract void nextCard();
    public abstract void execute();
}
