package com.example.demotableview;

import app.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TableDemoApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        TableView<Person> tableView = new TableView<>();

        TableColumn<Person, String> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));


        TableColumn<Person, String> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));


        tableView.getColumns().add(column1);

        tableView.getItems().add(new Person("Roald", "Amundsen"));
        tableView.getItems().add(new Person("Frijof", "Nansen"));

        VBox vbox = new VBox(tableView);


        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setTitle("Explorer");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}