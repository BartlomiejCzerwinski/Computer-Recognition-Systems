package com.example.summarization;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class HelloController {
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
    private ComboBox<String> comboBoxSortBy;

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
    private TableView<Summary> tableView;
    @FXML
    private TableColumn<Summary, String> sentence;
    @FXML
    private TableColumn<Summary, Double> measureT1;
    @FXML
    private TableColumn<Summary, Double> measureT2;
    @FXML
    private TableColumn<Summary, Double> measureT3;
    @FXML
    private TableColumn<Summary, Double> measureT4;
    @FXML
    private TableColumn<Summary, Double> measureT5;
    @FXML
    private TableColumn<Summary, Double> measureT6;
    @FXML
    private TableColumn<Summary, Double> measureT7;
    @FXML
    private TableColumn<Summary, Double> measureT8;
    @FXML
    private TableColumn<Summary, Double> measureT9;
    @FXML
    private TableColumn<Summary, Double> measureT10;
    @FXML
    private TableColumn<Summary, Double> measureT11;
    @FXML
    private TableColumn<Summary, Double> measureT;

    private ArrayList<Double> measuresWeights = new ArrayList<Double>(Arrays.asList(
            0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09
    ));

    private Initializer initializer = new Initializer();


    @FXML
    public void initialize() {
        Quantifier quantifier = initializer.createQuantifier();

        comboBoxQuantifier.setItems(FXCollections.observableArrayList(
                quantifier.getLabelsNames()
        ));
        comboBoxQuantifier.setValue("----");

        comboBoxQualifier.setItems(FXCollections.observableArrayList(
                initializer.getLinguisticLabelsNamesList()
        ));
        comboBoxQualifier.setValue("----");

        comboBoxSummarizer.setItems(FXCollections.observableArrayList(
                initializer.getLinguisticLabelsNamesList()
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

        comboBoxSortBy.setItems(FXCollections.observableArrayList(
                Arrays.asList(
                        "T", "T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11"
                )
        ));


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

        SummaryGenerator summaryGenerator = new SummaryGenerator(kind, type,
                new ArrayList<Quantifier>(Arrays.asList(initializer.createQuantifier())),
                initializer.getAllLinguisticVariables(), initializer.getAllLinguisticVariables(), subject1, subject2,
                measuresWeights, quantifier, qualifier, summarizer);
        summaryGenerator.generateSummaries();
        ArrayList<Summary> summaries = summaryGenerator.getSummaries();
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

        System.out.println(isWeightsCorrect(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11));

        if (isWeightsCorrect(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11)) {

            BigDecimal sumBigDecimal = new BigDecimal(t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10 + t11).setScale(2, RoundingMode.HALF_UP);
            double sum = sumBigDecimal.doubleValue();

            if (sum == 0.0) {
                measuresWeights = new ArrayList<Double>(Arrays.asList(
                        0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09
                ));
            }
            else {
                measuresWeights = new ArrayList<Double>(Arrays.asList(
                        t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11
                ));
            }
        }
        else {
            // error msg
        }

    }

    public boolean isWeightsCorrect(double t1, double t2, double t3, double t4, double t5, double t6, double t7,
                                    double t8, double t9, double t10, double t11) {

        BigDecimal sumBigDecimal = new BigDecimal(t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10 + t11).setScale(2, RoundingMode.HALF_UP);
        double sum = sumBigDecimal.doubleValue();

        if ( sum == 0.0)
            return true;
        if ( sum == 1.0)
            return true;
        return false;
    }

}