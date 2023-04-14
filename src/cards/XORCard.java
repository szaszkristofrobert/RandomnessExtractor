package cards;

import gui.App;
import file_manager.FileManager;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class XORCard extends ExtractorCard{
    public XORCard(App app){
        super(app);
        this.n = 1;
        this.label = "XOR";
        this.fileNameEnding = "_xor.txt";
        JLabel label = new JLabel(this.label);
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        FileManager fm = new FileManager();
        File input = fm.setupInput(inputField.getText());
        File output = fm.setupOutput(input.getName(), outputField.getText(), fileNameEnding);
        try {
            FileInputStream fileInput = new FileInputStream(input);
            FileWriter writer = new FileWriter(output, false);

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
                    r = n1 ^ n2;
                    writer.write(r+48);
                }
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
