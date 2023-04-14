package cards;

import file_manager.FileManager;
import gui.App;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ShiftXORCard extends ExtractorCard{
    private JTextField mField;
    public ShiftXORCard(App app){
        super(app);
        this.n = 8;
        this.label = "Shift XOR";
        this.fileNameEnding = "_shiftxor.txt";
        JLabel label = new JLabel(this.label);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.X_AXIS));

        mPanel.add(new JLabel("m: "));
        mField = new JTextField("20");
        mField.setMaximumSize(new Dimension(50, 20));
        mPanel.add(mField);

        settingsPanel.add(mPanel);

        this.add(label);
        this.add(settingsPanel);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        int m = Integer.parseInt(mField.getText());

        int[] mCache = new int[m];
        int i = 0;

        FileManager fm = new FileManager();
        File input = fm.setupInput(inputField.getText());
        File output = fm.setupOutput(input.getName(), outputField.getText(), fileNameEnding);
        try {
            FileInputStream fileInput = new FileInputStream(input);
            FileWriter writer = new FileWriter(output, false);

            int r;
            while ((r = fileInput.read()) != -1) {
                char c = (char) r;

                if (i >= m){
                    writer.write((mCache[i%m] ^ c-48) + 48);
                }
                mCache[i%m] = c-48;
                i++;
            }
            fileInput.close();
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        logLosses();
    }
}