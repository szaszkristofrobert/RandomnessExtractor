package cards;

import javax.swing.*;

import file_manager.FileManager;
import gui.App;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public abstract class Card extends JPanel {
    protected int n;
    public String label;
    protected String txtFileNameEnding;
    protected String binFileNameEnding;
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

    protected void logLosses(){
        FileManager fm = new FileManager();
        try {
            File input = new File(inputField.getText());
            double originalSize = (double) Files.size(Paths.get(inputField.getText()));
            double sizeAfterExtractor = (double) Files.size(Paths.get(fm.getFilePath(input.getName(), outputField.getText(), binFileNameEnding)));
            double efficiency = (sizeAfterExtractor * 8) / originalSize;
            Files.write(
                    Paths.get(outputField.getText() + "\\extractorLosses.txt"),
                    (this.label + ": " + efficiency + "\n").getBytes(),
                    StandardOpenOption.APPEND);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
