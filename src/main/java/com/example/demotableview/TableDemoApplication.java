package com.example.demotableview;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableDemoApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        TableView<Person> tableView = new TableView<>();

        TableColumn<Person, String> firstNameColum = new TableColumn<>("First Name");
        firstNameColum.setCellValueFactory(new PropertyValueFactory<Person, String>("firstname"));

        TableColumn<Person, String> surNameColum = new TableColumn<>("Surname");
        surNameColum.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));

        TableColumn<Person, Integer> ageColum = new TableColumn<>("Age");
        ageColum.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));


        tableView.getColumns().add(firstNameColum);
        tableView.getColumns().add(surNameColum);
        tableView.getColumns().add(ageColum);
        tableView.setEditable(true);

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        ObservableList<Person> pers = tableView.getItems();

        pers.add(new Person("Roald", "Amundsen", 55));
        pers.add(new Person("Fridtjof", "Nansen"));
        pers.add(new Person("Otto", "Sverdrup"));
        addAll(pers);





        HBox hBox0 = new HBox();

        HBox hBox1 = gethBox1(tableView, hBox0);

        HBox hBox2 = gethBox2(tableView);




        VBox vbox = new VBox(tableView, hBox0, hBox1, hBox2);


        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setTitle("Explorer");
        stage.show();
    }

    private HBox gethBox1(TableView<Person> tableView, HBox hBox0) {
        TextField firstNameInput = new TextField();
        firstNameInput.setPromptText("Vorname");
        TextField surNameInput = new TextField();
        surNameInput.setPromptText("Nachname");
        TextField ageInput = new TextField();
        ageInput.setPromptText("Alter");
        hBox0.getChildren().addAll(firstNameInput, surNameInput, ageInput);
        HBox hBox1 = new HBox();
        Button add = new Button("Hinzufügen");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Button Add was clicked");
                tableView.getItems().add(new Person(firstNameInput.getText(), surNameInput.getText(), Integer.parseInt(ageInput.getText())));
            }
        });

        Button delete = new Button("Entfernen");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Button Delete was clicked");
                ObservableList selected = tableView.getSelectionModel().getSelectedItems();
                tableView.getItems().removeAll(selected);
            }
        });
        hBox1.getChildren().addAll(add, delete);
        return hBox1;
    }

    private HBox gethBox2(TableView<Person> tableView) {
        HBox hBox2 = new HBox();
        Button selectAll = new Button("Alle Auswählen");
        selectAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Button selectAll was clicked");
                tableView.getSelectionModel().selectAll();
            }
        });

        Button deselectAll = new Button("Auswahl aufheben");
        deselectAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Button deselectAll was clicked");
                tableView.getSelectionModel().clearSelection();
            }
        });
        hBox2.getChildren().addAll(selectAll, deselectAll);
        return hBox2;
    }

    public static void main(String[] args) {
        launch();
    }

    public void addAll(ObservableList<Person> pers) {
        pers.add(new Person("Robert", "Pery"));
        pers.add(new Person("John", "Franklin"));
        pers.add(new Person("James", "Ross"));
        pers.add(new Person("Robert", "Scott"));
        pers.add(new Person("Umberto", "Noblie"));
        pers.add(new Person("Fredrick", "Cook"));
        pers.add(new Person("Adrian", "Gerlache"));
        pers.add(new Person("Ernest", "Shackelton"));
        pers.add(new Person("Olav", "Bjaaland", 70));
        pers.add(new Person("Helmer", "Hanssen"));
        pers.add(new Person("Sverre", "Hassel"));
        pers.add(new Person("Oscar", "Wisting"));
    }
}