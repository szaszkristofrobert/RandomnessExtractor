package cards;

import file_manager.FileManager;
import gui.App;

import javax.swing.*;
import java.io.*;

public class HFCard extends ExtractorCard{
    public HFCard(App app){
        super(app);
        this.n = 5;
        this.label = "HF";
        this.txtFileNameEnding = "_hf.txt";
        this.binFileNameEnding = "_hf.bin";
        JLabel label = new JLabel(this.label);
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        FileManager fm = new FileManager();
        File input = fm.setupInput(inputField.getText());
        File txtOutput = fm.setupOutput(input.getName(), outputField.getText(), txtFileNameEnding);
        File binOutput = fm.setupOutput(input.getName(), outputField.getText(), binFileNameEnding);
        try {
            FileInputStream fileInput = new FileInputStream(input);
            FileWriter txtWriter = new FileWriter(txtOutput, false);
            FileOutputStream binFos = new FileOutputStream(binOutput);

            int r;
            int[] byteOne = new int[8];
            int[] byteTwo = new int[8];
            int bitCounter = 0;

            while ((r = fileInput.read()) != -1) {
                char c = (char) r;

                if (bitCounter >= 0 && bitCounter < 8) {
                    byteOne[bitCounter] = c-48;
                    bitCounter++;
                }
                else if (bitCounter < 16) {
                    byteTwo[bitCounter-8] = c - 48;
                    bitCounter++;
                }

                if (bitCounter == 16) {
                    int[] byteOutput = hFunction(byteOne, byteTwo);

                    for (int bit : byteOutput
                         ) {
                        txtWriter.write(bit+48);
                    }

                    binFos.write(fm.funny_function(byteOutput));

                    bitCounter = 0;
                }

            }
            fileInput.close();
            txtWriter.close();
            binFos.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        logLosses();
    }

    private int[] hFunction(int[] byteOne, int[] byteTwo){
        int[] output = new int[8];

        output[0] = byteOne[0] ^ byteTwo[7];

        for(int i = 1; i < 8; i++){
            output[i] = byteOne[i] ^ byteTwo[i-1];
        }

        return output;
    }
}
