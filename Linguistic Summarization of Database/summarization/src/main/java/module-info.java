module com.example.summarization {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires commons.math3;

    opens com.example.summarization to javafx.fxml;
    exports com.example.summarization;
}