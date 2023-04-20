package cards;

import file_manager.FileManager;
import gui.App;

import s_box.AES;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SBCard extends ExtractorCard{
    public SBCard(App app){
        super(app);
        this.n = 7;
        this.label = "SB";
        this.binFileNameEnding = "_sb.bin";
        JLabel label = new JLabel(this.label);
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        FileManager fm = new FileManager();
        File input = fm.setupInput(inputField.getText());
        File output = fm.setupOutput(input.getName(), outputField.getText(), binFileNameEnding);
        boolean gotKey = false;
        try {
            FileInputStream fileInput = new FileInputStream(input);
            FileWriter writer = new FileWriter(output, false);

            int halfByteCounter = 0;
            int bitCounter = 0;
            int[] halfByte = new int[4];

            StringBuilder key = new StringBuilder();
            StringBuilder message = new StringBuilder();

            int r;
            while ((r = fileInput.read()) != -1) {
                char c = (char) r;

                //assemble byte from 8 bits
                halfByte[bitCounter] = c-48;
                bitCounter++;

                if (bitCounter == 4){
                    //add byte to key or message
                    char a = fm.convertToHex(halfByte);
                    if (!gotKey) {
                        key.append(a);
                        halfByteCounter++;
                        if (halfByteCounter == 128) {
                            gotKey = true;
                            halfByteCounter = 0;
                        }
                    }
                    else {
                        message.append(a);
                        halfByteCounter++;
                    }

                    if (halfByteCounter == 32 && gotKey) {
                        AES aes = new AES(message.toString(), key.toString());

                        writer.write(compress(aes.encrypt()));

                        double size = (double) Files.size(Paths.get(fm.getFilePath(input.getName(), outputField.getText(), binFileNameEnding)));

                        message = new StringBuilder();
                        halfByteCounter = 0;
                    }
                    bitCounter = 0;
                }
            }
            writer.close();
            fileInput.close();
        }
        catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        logLosses();
    }

    private String compress (String Hex){
        StringBuilder ret = new StringBuilder();
        int a;
        int b;
        for (int i = 0; i < Hex.length()/2; i++){
            a = hextToInt(Hex.charAt(2*i));
            b = hextToInt(Hex.charAt((2*i)+1));

            char c = (char)(a + (b * 16));

            ret.append(c);
        }
        return ret.toString();
    }

    private int hextToInt(char a){
        if (a < 60) return a-48;
        else return a-55;
    }
}
