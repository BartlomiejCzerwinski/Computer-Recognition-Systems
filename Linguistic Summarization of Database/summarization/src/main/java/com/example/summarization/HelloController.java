package com.example.summarization;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.DoubleStringConverter;

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
    private Button modeButton;

    @FXML
    private AnchorPane advancedView;

    @FXML
    private TableView<ObservableList<Object>> summaryTable;
    @FXML
    private TableColumn<ObservableList<String>, String> sentence;
    @FXML
    private TableColumn<ObservableList<String>, Double> measureT1;
    @FXML
    private TableColumn<ObservableList<String>, Double> measureT2;
    @FXML
    private TableColumn<ObservableList<String>, Double> measureT3;
    @FXML
    private TableColumn<ObservableList<String>, Double> measureT4;
    @FXML
    private TableColumn<ObservableList<String>, Double> measureT5;
    @FXML
    private TableColumn<ObservableList<String>, Double> measureT6;
    @FXML
    private TableColumn<ObservableList<String>, Double> measureT7;
    @FXML
    private TableColumn<ObservableList<String>, Double> measureT8;
    @FXML
    private TableColumn<ObservableList<String>, Double> measureT9;
    @FXML
    private TableColumn<ObservableList<String>, Double> measureT10;
    @FXML
    private TableColumn<ObservableList<String>, Double> measureT11;
    @FXML
    private TableColumn<ObservableList<String>, Double> measureT;

    @FXML
    private ComboBox<String> variableNameComboBox;

    @FXML
    private ComboBox<String> membershipFunctionTypeComboBox;

    @FXML
    private AnchorPane formTrapezoidalFunction;


    @FXML
    private AnchorPane formGaussianFunction;
    @FXML
    private ComboBox quantifierTypeComboBox;

    @FXML
    private TextField formLabelName;
    @FXML
    private TextField formA;
    @FXML
    private TextField formB;
    @FXML
    private TextField formC;
    @FXML
    private TextField formD;
    @FXML
    private TextField formMu;
    @FXML
    private TextField formOmega;


    private ArrayList<Double> measuresWeights = new ArrayList<Double>(Arrays.asList(
            0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09
    ));

    private Initializer initializer = new Initializer();


    @FXML
    public void initialize() {
        comboBoxQuantifier.setItems(FXCollections.observableArrayList(
                getQuantifiersLabels(initializer.createQuantifiers())
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
                "credit",
                "credit card",
                "car",
                "small business",
                "debt consolidation",
                "home impovement",
                "major purchase",
                "medical"
        ));
        comboBoxSubject1.setValue("credit");

        comboBoxSubject2.setItems(FXCollections.observableArrayList(
                "----",
                "credit card",
                "car",
                "small business",
                "debt consolidation",
                "home impovement",
                "major purchase",
                "medical"
        ));
        comboBoxSubject2.setValue("----");

        comboBoxSummarizerMany.setItems(FXCollections.observableArrayList(
                "-"
        ));
        comboBoxSummarizerMany.setValue("-");

        comboBoxSingleOrMany.setItems(FXCollections.observableArrayList(
                "single subject",
                "multiple subjects"
        ));
        comboBoxSingleOrMany.setValue("single subject");

        comboBoxType.setItems(FXCollections.observableArrayList(
                "1",
                "2",
                "3",
                "4"
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

        ArrayList<String> variablesNames = new ArrayList<>();
        for (ColumnVariableEnum columnVariableEnum : ColumnVariableEnum.values()) {
            variablesNames.add(columnVariableEnum.getValue());
        }

        variableNameComboBox.setItems(FXCollections.observableArrayList(variablesNames));
        variableNameComboBox.setValue(variablesNames.get(0));

        membershipFunctionTypeComboBox.setItems(FXCollections.observableArrayList(
                "Trapezoidalna",
                "Gaussowska"
        ));

        membershipFunctionTypeComboBox.setValue("Trapezoidalna");

        quantifierTypeComboBox.setItems(FXCollections.observableArrayList(
                "absolutny",
                "względny"
        ));
        quantifierTypeComboBox.setValue("absolutny");

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

        SummaryGenerator summaryGenerator = new SummaryGenerator(kind, type, initializer.createQuantifiers(),
                initializer.getAllLinguisticVariables(), initializer.getAllLinguisticVariables(), subject1, subject2,
                measuresWeights, quantifier, qualifier, summarizer);
        summaryGenerator.getCreditsPurposeInfo();
        summaryGenerator.generateSummaries();
        initResultTable();
        ArrayList<Summary> summaries = summaryGenerator.getSummaries();
        ArrayList<Summary> filteredSummaries = filterSummaries(summaries, quantifier, qualifier, summarizer, subject1, subject2);
        for (Summary summary : filteredSummaries) {
            addSummaryToTable(summary);
        }
    }

    @FXML
    public void onModeButtonClick() {
        String MODE_ADVANCED = "Tryb zaawansowany";
        String MODE_BASIC = "Tryb podstawowy";
        String mode = modeButton.getText();
        if (mode.equals(MODE_ADVANCED)) {
            modeButton.setText(MODE_BASIC);
            advancedView.setVisible(true);
        }
        else {
            modeButton.setText(MODE_ADVANCED);
            advancedView.setVisible(false);
        }
    }

    public ArrayList<Summary> filterSummaries(ArrayList<Summary> summaries, String quantifier, String qualifier,
                                              String summarizer, String subject1, String subject2) {
        ArrayList<Summary> result = new ArrayList<>();
        System.out.println("quantifier: " + quantifier);
        System.out.println("qualifier: " + qualifier);
        System.out.println("summarizer: " + summarizer);
        System.out.println("subject 1: " + subject1);
        System.out.println("subject 2: " + subject2);
        if (!quantifier.equals("----")) {
            for (Summary summary : summaries) {
                if (summary.getQuantifier().equals(quantifier))
                    result.add(summary);
            }
        }
        else {
            result = new ArrayList<>(summaries);
        }
        ArrayList<Summary> result2 = new ArrayList<>();
        if (!qualifier.equals("----")) {
            for (Summary summary : result) {
                if (summary.getQualifier().equals(qualifier))
                    result2.add(summary);
            }
        }
        else {
            result2 = new ArrayList<>(result);
        }
        ArrayList<Summary> result3 = new ArrayList<>();
        if (!summarizer.equals("----")) {
            for (Summary summary : result2) {
                if (summary.getSummarizer().equals(summarizer)) {
                    result3.add(summary);
                }
            }
        }
        else {
            result3 = new ArrayList<>(result2);
        }

        ArrayList<Summary> result4 = new ArrayList<>();
        if (!subject1.equals("credit")) {
            for (Summary summary : result3) {
                if (summary.getSubject1().equals(subject1))
                    result4.add(summary);
            }
        }
        else {
            result4 = new ArrayList<>(result3);
        }

        ArrayList<Summary> result5 = new ArrayList<>();
        if (!subject2.equals("----")) {
            for (Summary summary : result4) {
                if (summary.getSubject2().equals(subject2))
                    result5.add(summary);
            }
        }
        else {
            result5 = new ArrayList<>(result4);
        }

        return result5;
    }

    public void addSummaryToTable(Summary summary) {
        ObservableList<Object> row = FXCollections.observableArrayList();
        row.add(summary.getSentence());
        row.add(summary.getMeasures().get(0));
        row.add(summary.getMeasures().get(1));
        row.add(summary.getMeasures().get(2));
        row.add(summary.getMeasures().get(3));
        row.add(summary.getMeasures().get(4));
        row.add(summary.getMeasures().get(5));
        row.add(summary.getMeasures().get(6));
        row.add(summary.getMeasures().get(7));
        row.add(summary.getMeasures().get(8));
        row.add(summary.getMeasures().get(9));
        row.add(summary.getMeasures().get(10));
        row.add(summary.getMeasures().get(11));
        summaryTable.getItems().add(row);
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

    public ArrayList<String> getQuantifiersLabels(ArrayList<Quantifier> quantifiers) {
        ArrayList<String> result = new ArrayList<>();
        for (Quantifier quantifier : quantifiers) {
            result.add(quantifier.getLabel().getName());
        }
        result.add("----");
        return result;
    }

    public void initResultTable() {
        summaryTable.getItems().clear();
        sentence.setCellValueFactory(param -> {
            return new ReadOnlyObjectWrapper<>(param.getValue().get(0));
        });
        measureT1.setCellValueFactory(param -> {
            return new ReadOnlyObjectWrapper<>(Double.valueOf(String.valueOf(param.getValue().get(1))));
        });
        measureT2.setCellValueFactory(param -> {
            return new ReadOnlyObjectWrapper<>(Double.valueOf(String.valueOf(param.getValue().get(2))));
        });
        measureT3.setCellValueFactory(param -> {
            return new ReadOnlyObjectWrapper<>(Double.valueOf(String.valueOf(param.getValue().get(3))));
        });
        measureT4.setCellValueFactory(param -> {
            return new ReadOnlyObjectWrapper<>(Double.valueOf(String.valueOf(param.getValue().get(4))));
        });
        measureT5.setCellValueFactory(param -> {
            return new ReadOnlyObjectWrapper<>(Double.valueOf(String.valueOf(param.getValue().get(5))));
        });
        measureT6.setCellValueFactory(param -> {
            return new ReadOnlyObjectWrapper<>(Double.valueOf(String.valueOf(param.getValue().get(6))));
        });
        measureT7.setCellValueFactory(param -> {
            return new ReadOnlyObjectWrapper<>(Double.valueOf(String.valueOf(param.getValue().get(7))));
        });
        measureT8.setCellValueFactory(param -> {
            return new ReadOnlyObjectWrapper<>(Double.valueOf(String.valueOf(param.getValue().get(8))));
        });
        measureT9.setCellValueFactory(param -> {
            return new ReadOnlyObjectWrapper<>(Double.valueOf(String.valueOf(param.getValue().get(9))));
        });
        measureT10.setCellValueFactory(param -> {
            return new ReadOnlyObjectWrapper<>(Double.valueOf(String.valueOf(param.getValue().get(10))));
        });
        measureT11.setCellValueFactory(param -> {
            return new ReadOnlyObjectWrapper<>(Double.valueOf(String.valueOf(param.getValue().get(11))));
        });
        measureT.setCellValueFactory(param -> {
            return new ReadOnlyObjectWrapper<>(Double.valueOf(String.valueOf(param.getValue().get(12))));
        });
        sentence.setCellFactory(TextFieldTableCell.forTableColumn());
        measureT1.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        measureT2.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        measureT3.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        measureT4.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        measureT5.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        measureT6.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        measureT7.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        measureT8.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        measureT9.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        measureT10.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        measureT11.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        measureT.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    }

    public void onMembershipFunctionTypeChange() {
        String functionType = membershipFunctionTypeComboBox.getValue();
        if (functionType.equals("Trapezoidalna")) {
            formGaussianFunction.setVisible(false);
            formTrapezoidalFunction.setVisible(true);
        }
        else {
            formGaussianFunction.setVisible(true);
            formTrapezoidalFunction.setVisible(false);
        }
    }

}