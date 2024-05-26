module com.example.summarization {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.summarization to javafx.fxml;
    exports com.example.summarization;
}