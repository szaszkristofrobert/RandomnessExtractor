package gui;

import cards.*;
import file_manager.FileManager;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class App {
    public JFrame frame;
    public JPanel panelMain;
    private JPanel panelExtractor;
    private JPanel panelNIST;
    public JTextField textFieldExtractorInput;
    private JButton ExtractorInputBrowseButton;
    public JTextField textFieldExtractorOutput;
    private JButton ExtractorOutputBrowseButton;
    private JButton ExtractorRunButton;
    private JButton NISTRunButton;
    private JTextField textFieldNISTInput;
    private JTextField textFieldNISTOutput;
    private JButton NISTInputBrowseButton;
    private JButton NISTOutputBrowseButton;
    private JPanel ExtractorIFrame;
    private JPanel NISTIFrame;
    private JPanel panelExtractorCheckBoxes;
    private JCheckBox XORCheckBox;
    private JCheckBox ShiftXORCheckBox;
    private JCheckBox VNCheckBox;
    private JCheckBox IVNCheckBox;
    private JCheckBox NVNCheckBox;
    private JCheckBox HFCheckBox;
    private JCheckBox HashCheckBox;
    private JCheckBox SBCheckBox;
    JCheckBox[] extractorCheckBoxes;
    private JPanel panelNISTCheckBoxes;
    private JCheckBox FrequencyCheckBox;
    private JCheckBox BFCheckBox;
    private JCheckBox CuSumCheckbox;
    private JCheckBox RunsCheckBox;
    private JCheckBox LongestRunCheckBox;
    private JCheckBox RankCheckBox;
    private JCheckBox DFTCheckBox;
    private JCheckBox NTCheckBox;
    private JCheckBox OTCheckBox;
    private JCheckBox USCheckBox;
    private JCheckBox AECheckBox;
    private JCheckBox RECheckBox;
    private JCheckBox REVCheckBox;
    private JCheckBox SCheckBox;
    private JCheckBox LCCheckBox;
    private JCheckBox[] nistCheckBoxes;
    private boolean[] extractorTicks;

    public boolean[] extractorsReady;
    public boolean[] nistReady;
    private boolean[] nistTicks;

    Card[] nistCards = {new FrequencyCard(this), new BlockFrequencyCard(this), new CumulativeSumsCard(this), new RunsCard(this), new LongestRunCard(this), new RankCard(this), new DFTCard(this), new NTCard(this), new OTCard(this), new USCard(this), new AECard(this), new RECard(this), new REVCard(this), new SCard(this), new LCCard(this)};
    Card[] extractorCards = {new XORCard(this), new VNCard(this), new IVNCard(this), new NVNCard(this), new HFCard(this), new HashCard(this), new SBCard(this), new ShiftXORCard(this)};

    public App(){
        frame = new JFrame("App");

        frame.setSize(800, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(panelMain);
        setup();

        frame.setVisible(true);
    }

    public void setup(){
        extractorsReady = new boolean[]{true, true, true, true, true, true, true, true};
        nistReady = new boolean[]{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        setupExtractorCheckboxes();
        setupCheckBoxButton(panelExtractorCheckBoxes, extractorCheckBoxes, false);
        setupNISTCheckBoxes();
        setupCheckBoxButton(panelNISTCheckBoxes, nistCheckBoxes, true);
        setupExtractorRunButton();
        setupExtractorBrowseButtons();
        setupNISTRunButton();
        setupNISTBrowseButtons();
    }

    public void setupExtractorBrowseButtons(){
        ExtractorInputBrowseButton.addActionListener(e ->{
            JFileChooser chooser= new JFileChooser();
            chooser.setApproveButtonText("Choose file");

            int choice = chooser.showOpenDialog(null);

            if (choice != JFileChooser.APPROVE_OPTION) return;

            File chosenFile = chooser.getSelectedFile();

            textFieldExtractorInput.setText(chosenFile.getAbsolutePath());
        });

        ExtractorOutputBrowseButton.addActionListener(e -> {
            JFileChooser chooser= new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setApproveButtonText("Choose directory");



            int choice = chooser.showOpenDialog(null);

            if (choice != JFileChooser.APPROVE_OPTION) return;

            File chosenDirectory = chooser.getSelectedFile();

            textFieldExtractorOutput.setText(chosenDirectory.getAbsolutePath());
        });
    }

    public void setupNISTBrowseButtons(){
        NISTInputBrowseButton.addActionListener(e ->{
            JFileChooser chooser= new JFileChooser();
            chooser.setApproveButtonText("Choose file");

            int choice = chooser.showOpenDialog(null);

            if (choice != JFileChooser.APPROVE_OPTION) return;

            File chosenFile = chooser.getSelectedFile();

            textFieldNISTInput.setText(chosenFile.getAbsolutePath());
        });

        NISTOutputBrowseButton.addActionListener(e -> {
            JFileChooser chooser= new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setApproveButtonText("Choose directory");



            int choice = chooser.showOpenDialog(null);

            if (choice != JFileChooser.APPROVE_OPTION) return;

            File chosenDirectory = chooser.getSelectedFile();

            textFieldNISTOutput.setText(chosenDirectory.getAbsolutePath());
        });
    }

    public void setupExtractorCheckboxes(){
        panelExtractorCheckBoxes.setLayout(new BoxLayout(panelExtractorCheckBoxes, BoxLayout.Y_AXIS));
        extractorCheckBoxes = new JCheckBox[]{XORCheckBox, VNCheckBox, IVNCheckBox, NVNCheckBox, HFCheckBox, HashCheckBox, SBCheckBox, ShiftXORCheckBox};
        String[] labels = {"XOR", "Von Neumann", "Iterating VN", "N bit VN", "H function", "Hash", "S-box", "Shift XOR"};
        checkBoxSetup(panelExtractorCheckBoxes, extractorCheckBoxes, labels);
    }

    public void setupNISTCheckBoxes(){
        panelNISTCheckBoxes.setLayout(new BoxLayout(panelNISTCheckBoxes, BoxLayout.Y_AXIS));
        nistCheckBoxes = new JCheckBox[]{FrequencyCheckBox, BFCheckBox, CuSumCheckbox, RunsCheckBox, LongestRunCheckBox, RankCheckBox, DFTCheckBox, NTCheckBox, OTCheckBox, USCheckBox, AECheckBox, RECheckBox, REVCheckBox, SCheckBox, LCCheckBox,};
        String[] labels = {"Frequency", "Block Frequency", "Cumulative Sums", "Runs", "Longest Run of Ones", "Rank", "Discrete Fourier Transform", "Nonperiodic Template Matchings", "Overlapping Template Matchings", "Universal Statistical", "Approximate Entropy", "Random Excursions", "Random Excursions Variant", "Serial", "Linear Complexity"};
        checkBoxSetup(panelNISTCheckBoxes, nistCheckBoxes, labels);
    }

    public void checkBoxSetup(JPanel panel, JCheckBox[] checkBoxes, String[] labels){
        for (int i = 0; i < checkBoxes.length; i++){
            checkBoxes[i] = new JCheckBox(labels[i]);
            panel.add(checkBoxes[i]);
        }
    }

    public void setupCheckBoxButton(JPanel panel, JCheckBox[] checkBoxArray, boolean nist){
        JButton button = new JButton("Setup");
        panel.add(button);
        boolean[] ticks = new boolean[checkBoxArray.length];
        button.addActionListener(e -> {
            int i = 0;
            for (JCheckBox checkbox : checkBoxArray
                 ) {
                ticks[i++] = checkbox.isSelected();
            }
            System.out.println(Arrays.toString(ticks));
            if (nist) {
                nistTicks = ticks.clone();
                nistReady = ticks.clone();
            }
            else {
                extractorTicks = ticks.clone();
                extractorsReady = ticks.clone();
            }
            displayCards(nist);
        });
    }

    public void setupExtractorRunButton(){
        ExtractorRunButton.addActionListener(e -> {
            wipeLossLog();
            if (allFalse(extractorsReady)) {
                for (int i = 0; i < extractorCards.length; i++) {
                    if (extractorTicks[i]) {
                        extractorCards[i].execute();
                        System.out.println(extractorCards[i].label + " done!");
                    }
                }
                System.out.println("All extractors done!");
            }
            else
                System.out.println("Not all extractors were set up.");
        });
    }

    public void setupNISTRunButton(){
        NISTRunButton.addActionListener(e -> {
            try {
                FileManager fm = new FileManager();
                fm.setupNistRunner();
                fm.wipeNistRunner();
                int[] SettableTests = {1, 7, 8, 10, 13, 14};
                String nistTickString = "";
                for (int i = 0; i < nistTicks.length; i++) {
                    if (nistTicks[i])
                        nistTickString += "1";
                    else
                        nistTickString += "0";
                }
                fm.appendNistRunner(
                    "#!/bin/sh\n" +
                    "./assess 100000 <<EOF\n" +
                    "0\n" +
                    textFieldNISTInput.getText() + "\n" +
                    "0\n" +
                    nistTickString + "\n"
                );
                int counter = 1;
                if (allFalse(nistReady)) {
                   for (int i = 0; i < nistCards.length; i++) {
                       if (nistTicks[i]) {
                           if(contains(SettableTests, i)) {
                               fm.appendNistRunner(counter + "\n");
                               counter++;
                           }
                           nistCards[i].execute();
                           System.out.println(nistCards[i].label + " done!");
                       }
                   }
                   System.out.println("All tests done!");
                }
                else
                    System.out.println("Not all tests were set up");
                double bits = 8 * (double) Files.size(Paths.get(textFieldNISTInput.getText()));
                int bitStreams = (int) bits / 100000;

                fm.appendNistRunner(
                    "0\n" +
                    bitStreams + "\n" +
                    "1\n" +
                    "EOF"
                );
                fm.runNIST();
            }
            catch (IOException exception) {
                System.out.println("An error occurred.");
                exception.printStackTrace();
            }
        });
    }

    private boolean contains(int[] array, int number){
        for (int n : array
             ) {
            if (n == number) return true;
        }
        return false;
    }

    public void wipeLossLog(){
        File file = new File(textFieldExtractorOutput.getText() + "\\extractorLosses.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean allFalse(boolean[] array){
        for (boolean bool : array
             ) {
            if (bool) return false;
        }
        return true;
    }

    public void displayCards(boolean nist){
        if (nist)
            displayNextNISTCard(0);
        else
            displayNextExtractorCard(0);
    }
    public void displayNextExtractorCard(int startWith) {
        ExtractorIFrame.removeAll();
        ExtractorIFrame.revalidate();
        ExtractorIFrame.repaint();
        for (int i = startWith; i < extractorCards.length; i++){
            if (extractorTicks[i]) {
                ExtractorIFrame.add(extractorCards[i]);
                break;
            }
        }
    }

    public void displayNextNISTCard(int startWith){
        NISTIFrame.removeAll();
        NISTIFrame.revalidate();
        NISTIFrame.repaint();
        for (int i = startWith; i < nistCards.length; i++){
            if (nistTicks[i]) {
                NISTIFrame.add(nistCards[i]);
                break;
            }
        }
    }

    public static void main(String[] args) {
        App app = new App();
    }
}
