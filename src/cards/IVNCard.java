package cards;

import file_manager.FileManager;
import gui.App;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;

public class IVNCard extends ExtractorCard{
    private JTextField iField;
    public IVNCard(App app){
        super(app);
        this.n = 3;
        this.label = "IVN";
        JLabel label = new JLabel(this.label);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel iPanel = new JPanel();
        iPanel.setLayout(new BoxLayout(iPanel, BoxLayout.X_AXIS));

        iPanel.add(new JLabel("iterations: "));
        iField = new JTextField("inf");
        iField.setMaximumSize(new Dimension(50, 20));
        iPanel.add(iField);

        settingsPanel.add(iPanel);

        this.add(label);
        this.add(settingsPanel);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        int iterations = -1;
        if (Integer.parseInt(iField.getText()) > 0) {
            iterations = Integer.parseInt(iField.getText());
        }

        FileManager fm = new FileManager();
        File input = fm.setupInput(inputField.getText());
        File output = fm.setupOutput(input.getName(), outputField.getText(), "_ivn.txt");

        File iteratingInput = fm.setupOutput(input.getName(), outputField.getText(), "_iterating_input.txt");
        File discarded = fm.setupOutput(input.getName(), outputField.getText(), "_discarded.txt");
        try {
            fm.copyContent(input, iteratingInput);
            FileWriter writer = new FileWriter(output, false);

            while (iteratingInput.length() > 0 && iterations > 0){
                FileWriter discardedWriter = new FileWriter(discarded, false);
                FileInputStream fileInput = new FileInputStream(iteratingInput);
                int r;
                boolean haveFirst = false;
                int n1 = 0;
                int n2 = 0;
                while ((r = fileInput.read()) != -1) {
                    char c = (char) r;
                    if (!haveFirst){
                        haveFirst = true;
                        n1 = c-48;
                    }
                    else{
                        haveFirst = false;
                        n2 = c-48;
                        if (n1 != n2){
                            writer.write(n1+48);
                        }
                        else{
                            discardedWriter.write(n1+48);
                        }
                    }
                }
                discardedWriter.close();
                fileInput.close();
                fm.copyContent(discarded, iteratingInput);
                iterations--;
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            discarded.delete();
            iteratingInput.delete();
        }
        catch (SecurityException e){
            e.printStackTrace();
        }
    }
}
