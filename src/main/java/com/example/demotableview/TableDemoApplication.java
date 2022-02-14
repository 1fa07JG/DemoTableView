package com.example.demotableview;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableDemoApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        TableView<Person> tableView = new TableView<>();

        TableColumn<Person, String> firstNameColum = new TableColumn<>("First Name");
        firstNameColum.setCellValueFactory(new PropertyValueFactory<Person,String>("firstname"));

        TableColumn<Person,String> surNameColum=new TableColumn<>("Sur Name");
        surNameColum.setCellValueFactory(new PropertyValueFactory<Person,String>("surname"));

        TableColumn<Person,Integer> ageColum=new TableColumn<>("Age");
        ageColum.setCellValueFactory(new PropertyValueFactory<Person,Integer>("age"));


        tableView.getColumns().add(firstNameColum);
        tableView.getColumns().add(surNameColum);
        tableView.getColumns().add(ageColum);
        tableView.setEditable(true);

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        ObservableList<Person>pers=tableView.getItems();

        pers.add(new Person("Roald", "Amundsen",55));
        pers.add(new Person("Fridtjof", "Nansen"));
        pers.add(new Person("Otto", "Sverdrup"));
        addAll(pers);

        HBox hBox=new HBox();

        Button add=new Button("Hinzufügen");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Button Add was clicked");
                tableView.getItems().add(new Person("Hjalmar","Johansen"));
            }
        });

        Button delete=new Button("Entfernen");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Button Delete was clicked");
                ObservableList selected = tableView.getSelectionModel().getSelectedItems();
                tableView.getItems().removeAll(selected);
            }
        });
        hBox.getChildren().addAll(add,delete);
        VBox vbox = new VBox(tableView,hBox);


        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setTitle("Explorer");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void addAll(ObservableList<Person>pers){
        pers.add(new Person("Robert","Pery"));
        pers.add(new Person("John","Franklin"));
        pers.add(new Person("James","Ross"));
        pers.add(new Person("Robert","Scott"));
        pers.add(new Person("Umberto","Noblie"));
        pers.add(new Person("Fredrick","Cook"));
        pers.add(new Person("Adrian","Gerlache"));
        pers.add(new Person("Ernest","Shackelton"));
        pers.add(new Person("Olav","Bjaaland",70));
        pers.add(new Person("Helmer","Hanssen"));
        pers.add(new Person("Sverre","Hassel"));
        pers.add(new Person("Oscar","Wisting"));
    }
}