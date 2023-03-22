package cards;

import javax.swing.*;

import gui.App;

public abstract class Card extends JPanel {
    protected int n;
    App app;
    JButton buttonContinue;
    JTextField inputField;
    JTextField outputField;
    public Card(App app, JTextField inputField, JTextField outputField){
        super();
        this.app = app;
        this.inputField = inputField;
        this.outputField = outputField;
        buttonContinue = new JButton("Continue");
        buttonContinue.addActionListener(e -> {
            nextCard();
        });
    }
    public abstract void nextCard();
    public abstract void execute();
}
