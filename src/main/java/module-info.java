module com.example.demotableview {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demotableview to javafx.fxml;
    exports com.example.demotableview;
}