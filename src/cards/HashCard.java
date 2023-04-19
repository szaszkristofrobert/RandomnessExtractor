package cards;

import file_manager.FileManager;
import gui.App;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class HashCard extends ExtractorCard{
    public HashCard(App app){
        super(app);
        this.n = 6;
        this.label = "Hash";
        this.binFileNameEnding = "_hash.bin";
        JLabel label = new JLabel(this.label);
        this.add(label);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        FileManager fm = new FileManager();
        File input = fm.setupInput(inputField.getText());
        File output = fm.setupOutput(input.getName(), outputField.getText(), binFileNameEnding);
        try {
            FileInputStream fileInput = new FileInputStream(input);
            FileOutputStream fos = new FileOutputStream(output);

            File toHash = fm.setupOutput(input.getName(), outputField.getText(), "_toHash.bin");
            FileOutputStream toHashWriter = new FileOutputStream(toHash);

            int byteCounter = 0;
            int bitCounter = 0;
            int[] oneByte = new int[8];

            ArrayList<byte[]> outputBytes = new ArrayList<>();

            int r;
            while ((r = fileInput.read()) != -1) {
                char c = (char) r;

                //assemble byte from 8 bits
                oneByte[bitCounter] = c-48;
                bitCounter++;

                if (bitCounter == 8){
                    //write one byte into toHash file
                    byte byteToWrite = (byte)fm.funny_function(oneByte);
                    toHashWriter.write(byteToWrite);

                    byteCounter++;
                    bitCounter = 0;
                }

                if (byteCounter == 20){
                    //hash 20 bytes at a time
                    outputBytes.add(createSha1(toHash));

                    PrintWriter writer = new PrintWriter(toHash);
                    writer.print("");
                    writer.close();
                    byteCounter = 0;
                }
            }

            for (byte[] byteArray : outputBytes
                 ) {
                fos.write(byteArray);
            }

            toHashWriter.close();
            fileInput.close();
            fos.close();
            toHash.delete();
        }
        catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        logLosses();
    }

    private byte[] createSha1(File file) throws Exception  {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        InputStream fis = new FileInputStream(file);
        int n = 0;
        byte[] buffer = new byte[8192];
        while (n != -1) {
            n = fis.read(buffer);
            if (n > 0) {
                digest.update(buffer, 0, n);
            }
        }
        fis.close();
        return digest.digest();
    }
}
