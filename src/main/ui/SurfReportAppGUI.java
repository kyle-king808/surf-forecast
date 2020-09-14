package ui;

import exceptions.HeightWrongOrderException;
import model.Beach;
import model.SurfForecast;

import javax.swing.*;
import persistence.Reader;
import persistence.Writer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class SurfReportAppGUI {
    private static final String BEACHES_FILE = "./data/beaches.txt";
    private Scanner input;
    private JLabel title;
    private JButton addBeachButton;
    private JTextField beachName;
    private JPanel panel;
    private JTextField beachDirection;
    private JLabel beachNameLabel;
    private JLabel beachDirectionLabel;
    private JLabel currentBeachTitle;
    private JLabel heightLabel;
    private JLabel beachPeriodLabel;
    private JLabel maxWaveHeight;
    private JLabel minWaveHeight;
    private JLabel wavePeriodVariable;
    private JButton addSwell;
    private JLabel swellMaxHeight;
    private JLabel swellMinHeight;
    private JLabel swellPeriodLabel;
    private JLabel swellDirectionLabel;
    private JTextField maxSwellHeightText;
    private JTextField minSwellHeightText;
    private JTextField swellPeriodText;
    private JTextField swellDirectionText;
    private JLabel yourBeachesLabel;
    private JList<Beach> beachList; //= new JList<>();
    private JButton deleteButton;
    private JButton saveButton;
    private JButton loadBeachesButton;
    private JLabel beachDirectionLabelOutPut;
    private JLabel currentBeachDirectionOutPut;
    private SurfForecast surfForecast = new SurfForecast();
    DefaultListModel<Beach> model = new DefaultListModel<>();

    //GUI Constructor
    public SurfReportAppGUI() {
        beachList.setModel(model);
        addBeachButton.addActionListener(e -> model.addElement(addNewBeachHelper()));
        addSwell.addActionListener(e -> SurfReportAppGUI.this.updateSwellPanelHelper());
        beachList.addListSelectionListener(e -> displayCurrentBeach(beachList.getSelectedValue()));
        deleteButton.addActionListener(e -> {
            String s = currentBeachTitle.getText();
            deleteButtonHelper(s);
        });
        saveButton.addActionListener(e -> saveButtonHelper());
        loadBeachesButton.addActionListener(e -> loadButtonHelper());
    }

    // EFFECTS: Plays "Into Yesterday" song
    public void playMusic(String filePath) {
        InputStream music;

        try {
            music = new FileInputStream(new File(filePath));
            AudioStream audioStream = new AudioStream(music);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: Saves current  in beachList with their info to text file
    public void saveButtonHelper() {
        try {
            Writer writer = new Writer(new File(BEACHES_FILE));

            for (Beach b : surfForecast.getBeachList()) {
                writer.write(b);
            }
            writer.close();
            //beaches saved
        } catch (FileNotFoundException e) {
            //beaches not saved
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // EFFECTS: Loads Beaches from text file if any
    public void loadButtonHelper() {
        try {
            List<Beach> tempBeachList = Reader.readBeaches(new File(BEACHES_FILE));

            for (Beach b : tempBeachList) {
                surfForecast.addBeach(b);
                model.addElement(b);
            }

        } catch (IOException e) {
            //file not read;
        }
        updateSwellPanelHelper();
    }


    // EFFECTS: Deletes selected Beach
    public void deleteButtonHelper(String name) {
        for (Beach b : surfForecast.getBeachList()) {
            if (b.getName() == name) {
                model.removeElement(b);
            }
        }

        displayNoBeach();

        for (int i = surfForecast.getBeachList().size() - 1; i >= 0; i--) {
            if (surfForecast.getBeachList().get(i).getName() == name) {
                surfForecast.getBeachList().remove(i);
            }
        }
    }

    // EFFECTS: Adds a new beach
    public Beach addNewBeachHelper() {
        Beach newBeach = new Beach(beachName.getText(), Integer.parseInt(beachDirection.getText()));
        surfForecast.addBeach(newBeach);
        updateSwellPanelHelper();
        displayCurrentBeach(newBeach);
        return newBeach;
    }

    // EFFECTS: updates swell information in surfForecast, plays music if first time updating
    public void updateSwellPanelHelper() {
        try {
            surfForecast.updateSwell(Integer.parseInt(swellDirectionText.getText()),
                    Integer.parseInt(swellPeriodText.getText()),
                    Integer.parseInt(minSwellHeightText.getText()),
                    Integer.parseInt(maxSwellHeightText.getText()));
        } catch (HeightWrongOrderException e) {
            updateSwellWrongOrderFix();
            updateSwellPanelHelper();
        }
        if (currentBeachTitle.getText() == "DefaultBeach") {
            playMusic("Audio/Music Video Surf's Up Sugar Ray - Into Yesterday.wav");
        }
        beachList.clearSelection();
        displayNoBeach();
    }

    // EFFECTS: Switches text in swell OneThird and OneTenth Heigh boxes
    public void updateSwellWrongOrderFix() {
        String oldMin = minSwellHeightText.getText();
        String oldMax = maxSwellHeightText.getText();

        minSwellHeightText.setText(oldMax);
        maxSwellHeightText.setText(oldMin);
    }


    // EFFECTS: Displays given beach
    public void displayCurrentBeach(Beach b) {
        if (b != null) {
            currentBeachTitle.setText(b.getName());
            maxWaveHeight.setText(Integer.toString(b.getOneTenthHeight()));
            minWaveHeight.setText(Integer.toString(b.getOneThirdHeight()));
            wavePeriodVariable.setText(Integer.toString(surfForecast.getSwellPeriod()));
            currentBeachDirectionOutPut.setText(Integer.toString(b.getDirection()));
        } else {
            displayNoBeach();
        }

    }

    // EFFECTS: Displays blank information
    public void displayNoBeach() {
        currentBeachTitle.setText("");
        maxWaveHeight.setText("");
        minWaveHeight.setText("");
        wavePeriodVariable.setText("");
        currentBeachDirectionOutPut.setText("");
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("SurfReportAppGUI");
        frame.setContentPane(new SurfReportAppGUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
