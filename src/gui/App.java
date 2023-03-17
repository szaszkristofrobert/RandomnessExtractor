package gui;

import javax.swing.*;
import java.util.Arrays;

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
    JCheckBox[] extractorCheckBoxes;
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
    private JCheckBox[] nistCheckBoxes;
    private JButton buttonNISTSetup;
    boolean[] extractorTicks;
    boolean[] nistTicks;

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
        setupCheckBoxButton(panelExtractorCheckBoxes, buttonExtractorSetup, extractorCheckBoxes, false);
        setupNISTCheckBoxes();
        setupCheckBoxButton(panelNISTCheckBoxes, buttonNISTSetup, nistCheckBoxes, true);
    }

    public void setupExtractorCheckboxes(){
        panelExtractorCheckBoxes.setLayout(new BoxLayout(panelExtractorCheckBoxes, BoxLayout.Y_AXIS));
        extractorCheckBoxes = new JCheckBox[]{XORCheckBox, VNCheckBox, IVNCheckBox, NVNCheckBox, HFCheckBox, HashCheckBox, SBCheckBox};
        String[] labels = {"XOR", "Von Neumann", "Iterating VN", "N bit VN", "H function", "Hash", "S-box"};
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

    public void setupCheckBoxButton(JPanel panel, JButton button, JCheckBox[] checkBoxArray, boolean nist){
        button = new JButton("Setup");
        panel.add(button);
        boolean[] ticks = new boolean[checkBoxArray.length];
        button.addActionListener(e -> {
            int i = 0;
            for (JCheckBox checkbox : checkBoxArray
                 ) {
                ticks[i++] = checkbox.isSelected();
            }
            System.out.println(Arrays.toString(ticks));
            if (nist)
                nistTicks = ticks;
            else
                extractorTicks = ticks;
            displayCards(nist);
        });
    }

    public void displayCards(boolean nist){
        if (nist)
            //handleNistCards();
            System.out.println("TODO: handle nist cards");
        else
            displayNextExtractorCard(0);
    }
    public void displayNextExtractorCard(int startWith) {
        XORCard xorCard = new XORCard();
        VNCard vnCard = new VNCard();
        IVNCard ivnCard = new IVNCard();
        NVNCard nvnCard = new NVNCard();
        HFCard hfCard = new HFCard();
        HashCard hashCard = new HashCard();
        SBCard sbCard = new SBCard();
        Card[] cards = {xorCard, vnCard, ivnCard, nvnCard, hfCard, hashCard, sbCard};
        ExtractorIFrame.removeAll();
        ExtractorIFrame.revalidate();
        ExtractorIFrame.repaint();
        for (int i = startWith; i < cards.length; i++){
            if (extractorTicks[i]) {
                ExtractorIFrame.add(cards[i]);
                break;
            }
        }
    }

    public static void main(String[] args) {
        App app = new App();
    }

    public abstract static class Card extends JPanel{
        JButton buttonContinue;
        public Card(){
            super();
            buttonContinue = new JButton("Continue");
            buttonContinue.addActionListener(e -> {
                nextCard();
            });
        }
        public abstract void nextCard();
    }
    public class XORCard extends Card{
        public XORCard(){
            super();
            JLabel label = new JLabel("XOR");
            this.add(label);
            this.add(buttonContinue);
        }
        @Override
        public void nextCard(){
            displayNextExtractorCard(1);
        }
    }
    public class VNCard extends Card{
        public VNCard(){
            super();
            JLabel label = new JLabel("VN");
            this.add(label);
            this.add(buttonContinue);
        }
        @Override
        public void nextCard(){
            displayNextExtractorCard(2);
        }
    }
    public class IVNCard extends Card{
        public IVNCard(){
            super();
            JLabel label = new JLabel("IVN");
            this.add(label);
            this.add(buttonContinue);
        }
        @Override
        public void nextCard(){
            displayNextExtractorCard(3);
        }
    }
    public class NVNCard extends Card{
        public NVNCard(){
            super();
            JLabel label = new JLabel("NVN");
            this.add(label);
            this.add(buttonContinue);
        }
        @Override
        public void nextCard(){
            displayNextExtractorCard(4);
        }
    }
    public class HFCard extends Card{
        public HFCard(){
            super();
            JLabel label = new JLabel("HF");
            this.add(label);
            this.add(buttonContinue);
        }
        @Override
        public void nextCard(){
            displayNextExtractorCard(5);
        }
    }
    public class HashCard extends Card{
        public HashCard(){
            super();
            JLabel label = new JLabel("Hash");
            this.add(label);
            this.add(buttonContinue);
        }
        @Override
        public void nextCard(){
            displayNextExtractorCard(6);
        }
    }
    public class SBCard extends Card{
        public SBCard(){
            super();
            JLabel label = new JLabel("SB");
            this.add(label);
            this.add(buttonContinue);
        }
        @Override
        public void nextCard(){
            displayNextExtractorCard(7);
        }
    }
}
