package cards;

import file_manager.FileManager;
import gui.App;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class NVNCard extends ExtractorCard{
    private JTextField nField;
    private int nValue;
    public NVNCard(App app){
        super(app);
        this.n = 4;
        this.label = "NVN";
        JLabel label = new JLabel(this.label);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel nPanel = new JPanel();
        nPanel.setLayout(new BoxLayout(nPanel, BoxLayout.X_AXIS));

        nPanel.add(new JLabel("n: "));
        nField = new JTextField("2");
        nField.setMaximumSize(new Dimension(50, 20));
        nPanel.add(nField);

        settingsPanel.add(nPanel);

        this.add(label);
        this.add(settingsPanel);
        this.add(buttonContinue);
    }
    @Override
    public void execute() {
        nValue = Integer.parseInt(nField.getText());

        FileManager fm = new FileManager();
        File input = fm.setupInput(inputField.getText());
        File output = fm.setupOutput(input.getName(), outputField.getText(), "_nvn.txt");

        try {
            FileInputStream fileInput = new FileInputStream(input);
            FileWriter writer = new FileWriter(output, false);

            int r;
            int filled = 0;
            int[] nValues = new int[nValue];
            int[] sGroups = new int[nValue+1];

            for (int k = 0; k < nValue+1; k++){
                sGroups[k] = nChooseK(nValue, k);
            }

            while ((r = fileInput.read()) != -1) {
                char c = (char) r;

                nValues[filled] = c-48;
                filled++;

                if (filled == nValue){
                    writer.write(evaluate(nValues, sGroups));

                    filled = 0;
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

    private int nChooseK(int n, int k) {
        return fact(n) / (fact(n-k) * fact(k));
    }
    private int fact(int n){
        int ret = 1;
        for (int i = 2; i <= n; i++){
            ret = ret*i;
        }
        return ret;
    }

    private String evaluate(int[] nValues, int[] sGroups){
        int k = 0;
        int number = 0;
        for (int i = 0; i < nValue; i++){
            k += nValues[i];
            if (nValues[i] == 1)
                number += Math.pow(2, i);
        }
        if (k == 0 || k == nValue)
            return "";
        //Sk tagja a bitsorozat
        //Sk elemszama sGroups[k]

        int maxIterations = 1;
        int outputLength = 0;

        while(maxIterations <= sGroups[k]){
            maxIterations *= 2;
            outputLength++;
        }

        outputLength--;
        maxIterations /= 2;

        int[] powersOf2 = new int[k];
        for (int i = 0; i < k; i++){
            powersOf2[i] = k-i-1;
        }
        for (int i = 0; i < maxIterations; i++){
            if (numberFromPowers(powersOf2) == number)
                return addBuffer(Integer.toBinaryString(i), outputLength);
            else{
                powersOf2 = iteratePowers(powersOf2);
            }
        }
        return "";
    }

    private String addBuffer(String binaryString, int outputLength){
        String ret = binaryString;
        while(ret.length() < outputLength){
            ret = "0" + ret;
        }
        return ret;
    }

    private int numberFromPowers(int[] powers){
        int ret = 0;
        for (int i = 0; i < powers.length; i++){
            ret += Math.pow(2, powers[i]);
        }
        return ret;
    }

    private int[] iteratePowers(int[] powers){
        int[] ret = powers.clone();
        int maxindex = powers.length-1;
        for(int i = 0; i < maxindex; i++){
            if (ret[maxindex-i-1] - ret[maxindex-i] == 2){
                ret[maxindex-i]++;
                return ret;
            }
        }
        ret[0]++;
        return ret;
    }
}
