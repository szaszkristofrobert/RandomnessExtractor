package cards;

import gui.App;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class XORCard extends ExtractorCard{
    public XORCard(App app){
        super(app);
        this.n = 1;
        JLabel label = new JLabel("XOR");
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        try {
            File input = new File(inputField.getText());
            String fileName = input.getName();
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
                fileName = fileName.substring(0, dotIndex);
            }
            String path = outputField.getText() + "\\" + fileName + "_xor.txt";
            System.out.println(path + " created!");
            File output = new File(path);

            if (!output.exists()) {
                output.createNewFile();
            }

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
                    writer.write(r);
                }
            }
            fileInput.close();
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
