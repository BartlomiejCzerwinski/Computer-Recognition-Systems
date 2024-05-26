package com.example.summarization;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

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
    public void initialize() {
        Initializer initializer = new Initializer();
        Quantifier quantifier = initializer.createQuantifier();
        initializer.createLinguisticVariables();
        comboBoxQuantifier.setItems(FXCollections.observableArrayList(
                quantifier.getLabelsNames()
        ));
        comboBoxQualifier.setItems(FXCollections.observableArrayList(
                "-"
        ));
        comboBoxSummarizer.setItems(FXCollections.observableArrayList(
                "-"
        ));
        comboBoxSubject1.setItems(FXCollections.observableArrayList(
                "-"
        ));
        comboBoxSubject2.setItems(FXCollections.observableArrayList(
                "-"
        ));
        comboBoxSummarizerMany.setItems(FXCollections.observableArrayList(
                "-"
        ));
        comboBoxSingleOrMany.setItems(FXCollections.observableArrayList(
                "single subject"
        ));
        comboBoxType.setItems(FXCollections.observableArrayList(
                "1",
                "2"
        ));
        SummaryGenerator summaryGenerator = new SummaryGenerator("single", 1, new ArrayList<>(Arrays.asList(initializer.createQuantifier())),
                "subject1", "subject2", initializer.createLinguisticVariables(), new ArrayList<>(Arrays.asList(0.0)),
                new ArrayList<>(Arrays.asList("none")), initializer.createLinguisticVariables());
        summaryGenerator.generateSummaries();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}