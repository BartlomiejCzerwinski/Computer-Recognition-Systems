package com.example.summarization;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}