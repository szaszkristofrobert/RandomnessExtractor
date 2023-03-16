package gui;

import javax.swing.*;

public class App {
    public JFrame frame;
    public JPanel panelMain;
    private JPanel panelExtractor;
    private JPanel panelNIST;
    private JTextField textFieldExtractorInput;
    private JButton ExtractorInputBrowseButton;
    private JTextField textFieldExtractorOutput;
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
    private JCheckBox VNCheckBox;
    private JCheckBox IVNCheckBox;
    private JCheckBox NVNCheckBox;
    private JCheckBox HFCheckBox;
    private JCheckBox HashCheckBox;
    private JCheckBox SBCheckBox;
    private JButton buttonExtractorSetup;
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
    private JButton buttonNISTSetup;
    private JPanel XORCard = new JPanel();
    private JPanel VNCard = new JPanel();
    private JPanel IVNCard = new JPanel();
    private JPanel NVNCard = new JPanel();
    private JPanel HFCard = new JPanel();
    private JPanel HashCard = new JPanel();
    private JPanel SBCard = new JPanel();

    public App(){
        frame = new JFrame("App");

        frame.setSize(800, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(panelMain);
        setup();

        frame.setVisible(true);
    }

    public void setup(){
        setupExtractorCheckboxes();
        setupNISTCheckBoxes();
    }

    public void setupExtractorCheckboxes(){
        panelExtractorCheckBoxes.setLayout(new BoxLayout(panelExtractorCheckBoxes, BoxLayout.Y_AXIS));
        JCheckBox[] checkBoxes = {XORCheckBox, VNCheckBox, IVNCheckBox, NVNCheckBox, HFCheckBox, HashCheckBox, SBCheckBox};
        String[] labels = {"XOR", "Von Neumann", "Iterating VN", "N bit VN", "H function", "Hash", "S-box"};
        checkBoxSetup(panelExtractorCheckBoxes, checkBoxes, labels);

        buttonExtractorSetup = new JButton("Setup");
        panelExtractorCheckBoxes.add(buttonExtractorSetup);
    }

    public void setupNISTCheckBoxes(){
        panelNISTCheckBoxes.setLayout(new BoxLayout(panelNISTCheckBoxes, BoxLayout.Y_AXIS));
        JCheckBox[] checkBoxes = {FrequencyCheckBox, BFCheckBox, CuSumCheckbox, RunsCheckBox, LongestRunCheckBox, RankCheckBox, DFTCheckBox, NTCheckBox, OTCheckBox, USCheckBox, AECheckBox, RECheckBox, REVCheckBox, SCheckBox, LCCheckBox,};
        String[] labels = {"Frequency", "Block Frequency", "Cumulative Sums", "Runs", "Longest Run of Ones", "Rank", "Discrete Fourier Transform", "Nonperiodic Template Matchings", "Overlapping Template Matchings", "Universal Statistical", "Approximate Entropy", "Random Excursions", "Random Excursions Variant", "Serial", "Linear Complexity"};
        checkBoxSetup(panelNISTCheckBoxes, checkBoxes, labels);

        buttonNISTSetup = new JButton("Setup");
        panelNISTCheckBoxes.add(buttonNISTSetup);
    }

    public void checkBoxSetup(JPanel panel, JCheckBox[] checkBoxes, String[] labels){
        for (int i = 0; i < checkBoxes.length; i++){
            checkBoxes[i] = new JCheckBox(labels[i]);
            panel.add(checkBoxes[i]);
        }
    }

    public static void main(String[] args) {
        App app = new App();
    }
}
