package cards;

import file_manager.FileManager;
import gui.App;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;

public class IVNCard extends ExtractorCard{
    public IVNCard(App app){
        super(app);
        this.n = 3;
        this.label = "IVN";
        JLabel label = new JLabel(this.label);
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        FileManager fm = new FileManager();
        File input = fm.setupInput(inputField.getText());
        File output = fm.setupOutput(input.getName(), outputField.getText(), "_ivn.txt");

        File iteratingInput = fm.setupOutput(input.getName(), outputField.getText(), "_iterating_input.txt");
        File discarded = fm.setupOutput(input.getName(), outputField.getText(), "_discarded.txt");
        try {
            fm.copyContent(input, iteratingInput);
            FileWriter writer = new FileWriter(output, false);

            while (iteratingInput.length() > 0){
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
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
