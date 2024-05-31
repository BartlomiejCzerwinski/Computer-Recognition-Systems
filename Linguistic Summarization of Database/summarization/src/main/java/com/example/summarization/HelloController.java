package com.example.summarization;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox<String> comboBoxQuantifier;
    @FXML
    private ComboBox<String> comboBoxQualifier;
    @FXML
    private ComboBox<String> comboBoxSummarizer;
    @FXML
    private ComboBox<String> comboBoxSubject1;
    @FXML
    private ComboBox<String> comboBoxSubject2;
    @FXML
    private ComboBox<String> comboBoxSummarizerMany;
    @FXML
    private ComboBox<String> comboBoxSingleOrMany;
    @FXML
    private ComboBox<String> comboBoxType;

    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    private TextField t3;

    @FXML
    private TextField t4;

    @FXML
    private TextField t5;

    @FXML
    private TextField t6;

    @FXML
    private TextField t7;

    @FXML
    private TextField t8;

    @FXML
    private TextField t9;

    @FXML
    private TextField t10;

    @FXML
    private TextField t11;

    @FXML
    private Button generateSummary;

    @FXML
    private Button confirmWeights;


    @FXML
    public void initialize() {
        Initializer initializer = new Initializer();
        Quantifier quantifier = initializer.createQuantifier();
        initializer.createLinguisticVariables();
        SummaryGenerator summaryGenerator = new SummaryGenerator("single", 1, new ArrayList<>(Arrays.asList(initializer.createQuantifier())),
                "credits", "subject2", initializer.createLinguisticVariables(), new ArrayList<>(Arrays.asList(0.0)),
                new ArrayList<>(Arrays.asList("none")), initializer.createLinguisticVariables());

        comboBoxQuantifier.setItems(FXCollections.observableArrayList(
                quantifier.getLabelsNames()
        ));
        comboBoxQuantifier.setValue("----");

        comboBoxQualifier.setItems(FXCollections.observableArrayList(
                summaryGenerator.getLinguisticLabelsNamesList()
        ));
        comboBoxQualifier.setValue("----");

        comboBoxSummarizer.setItems(FXCollections.observableArrayList(
                summaryGenerator.getLinguisticLabelsNamesList()
        ));
        comboBoxSummarizer.setValue("----");

        comboBoxSubject1.setItems(FXCollections.observableArrayList(
                "credit"
        ));
        comboBoxSubject1.setValue("credit");

        comboBoxSubject2.setItems(FXCollections.observableArrayList(
                "-"
        ));
        comboBoxSubject2.setValue("-");

        comboBoxSummarizerMany.setItems(FXCollections.observableArrayList(
                "-"
        ));
        comboBoxSummarizerMany.setValue("-");

        comboBoxSingleOrMany.setItems(FXCollections.observableArrayList(
                "single subject"
        ));
        comboBoxSingleOrMany.setValue("single subject");

        comboBoxType.setItems(FXCollections.observableArrayList(
                "1",
                "2"
        ));
        comboBoxType.setValue("1");

        t1.setText("0");
        t2.setText("0");
        t3.setText("0");
        t4.setText("0");
        t5.setText("0");
        t6.setText("0");
        t7.setText("0");
        t8.setText("0");
        t9.setText("0");
        t10.setText("0");
        t11.setText("0");

        summaryGenerator.generateSummaries();


    }

    @FXML
    public void onGenerateSummaryClick() {
        String quantifier = comboBoxQuantifier.getValue().toString();
        String qualifier = comboBoxQualifier.getValue().toString();
        String summarizer = comboBoxSummarizer.getValue().toString();
        String subject1 = comboBoxSubject1.getValue().toString();
        String subject2 = comboBoxSubject2.getValue().toString();
        String summarizerMany = comboBoxSummarizerMany.getValue().toString();
        String kind = comboBoxSingleOrMany.getValue().toString();
        int type = Integer.valueOf(comboBoxType.getValue().toString());

        System.out.println("Quantifier: " + quantifier);
        System.out.println("Qualifier: " + qualifier);
        System.out.println("Summarizer: " + summarizer);
        System.out.println("Subject 1: " + subject1);
        System.out.println("Subject 2: " + subject2);
        System.out.println("Summarizer Many: " + summarizerMany);
        System.out.println("Kind: " + kind);
        System.out.println("Type: " + type);

    }

    @FXML
    public void onConfirmWeightsClick() {
        double t1 = Double.parseDouble(this.t1.getCharacters().toString());
        double t2 = Double.parseDouble(this.t2.getCharacters().toString());
        double t3 = Double.parseDouble(this.t3.getCharacters().toString());
        double t4 = Double.parseDouble(this.t4.getCharacters().toString());
        double t5 = Double.parseDouble(this.t5.getCharacters().toString());
        double t6 = Double.parseDouble(this.t6.getCharacters().toString());
        double t7 = Double.parseDouble(this.t7.getCharacters().toString());
        double t8 = Double.parseDouble(this.t8.getCharacters().toString());
        double t9 = Double.parseDouble(this.t9.getCharacters().toString());
        double t10 = Double.parseDouble(this.t10.getCharacters().toString());
        double t11 = Double.parseDouble(this.t11.getCharacters().toString());

        if (isWeightsCorrect(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11)) {
            // save weights
        }
        else {
            // error msg
        }

    }

    public boolean isWeightsCorrect(double t1, double t2, double t3, double t4, double t5, double t6, double t7,
                                    double t8, double t9, double t10, double t11) {
        if ((t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10 + t11) == 1.0)
            return true;
        return false;
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}