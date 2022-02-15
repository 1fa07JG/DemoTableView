package com.example.demotableview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.time.LocalDateTime;

public class TableDemoApplication extends Application {
    @Override
    public void start(Stage stage) {
        TableView<Person> tableView = new TableView<>();


        setUpTableView(tableView);

        ObservableList<Person> pers = tableView.getItems();
        pers.addListener(createChangeListener());
        addTestContent(pers);
        TableView.TableViewSelectionModel<Person> selectionModel = tableView.getSelectionModel();
        ObservableList<Person> selectedItems = tableView.getSelectionModel().getSelectedItems();
        selectedItems.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> change) {
                System.out.println(tableView.getSelectionModel().getSelectedIndices().size()+" Ausgewählt");
                if (tableView.getSelectionModel().getSelectedIndices().size() > 3) {
                    System.out.println("zu viele ausgewählt");
                    showAlert(tableView.getSelectionModel().getSelectedIndices().size());
                }

            }
        });


        HBox hBox0 = new HBox();

        HBox hBox1 = gethBox1(tableView, hBox0);

        HBox hBox2 = gethBox2(tableView);


        VBox vbox = new VBox(tableView, hBox0, hBox1, hBox2);


        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setTitle("Explorer");
        stage.show();
    }

    private static void showAlert(int ausgewhält) {
        String message = "Zu viele Personen ausgewählt! Es sind Momentan " + ausgewhält+" Ausgewählt\nEs dürfen Maximal drei Personen ausgewählt werden";
        Alert a = new Alert(Alert.AlertType.WARNING, message);
        a.initModality(Modality.NONE);
        a.show();
    }

    private ListChangeListener<Person> createChangeListener() {
        return new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                while (c.next()) {
                    if (c.wasAdded()) {

                        boolean one = c.getAddedSize() == 1;
                        System.out.print("Es wurde" + (one ? "" : "n") + " " + (one ? "ein" : c.getAddedSize()) + " Element" + (one ? "" : "e") + " hinzugefügt\n");
                    }
                    if (c.wasReplaced()) {
                        System.out.print(" etwas Ersetzt\n");
                    }
                    if (c.wasRemoved()) {
                        boolean one = c.getRemovedSize() == 1;
                        System.out.print("Es wurde" + (one ? "" : "n") + " " + (one ? "ein" : c.getRemovedSize()) + " Element" + (one ? "" : "e") + " entfernt\n");
                    }
                    if (c.wasUpdated()) {
                        System.out.print(" etwas Aktualisiert\n");
                    }
                    if (c.wasPermutated()) {
                        System.out.print(" etwas Kombiniert\n");
                    }
                }
            }
        };
    }


    private void setUpTableView(TableView<Person> tableView) {
        TableColumn<Person, String> firstNameColum = new TableColumn<>("First Name");
        firstNameColum.setCellValueFactory(new PropertyValueFactory<Person, String>("firstname"));
        firstNameColum.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                System.out.println("----EditCommit()---- ");
                event.getRowValue().setFirstname(event.getNewValue());
            }
        });


        TableColumn<Person, String> surNameColum = new TableColumn<>("Surname");
        surNameColum.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
        surNameColum.setCellFactory(TextFieldTableCell.forTableColumn());
        surNameColum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                System.out.println("----EditCommit()---- ");
                event.getRowValue().setSurname(event.getNewValue());
            }
        });

        TableColumn<Person, Integer> ageColum = new TableColumn<>("Age");
        ageColum.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
        ageColum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ageColum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, Integer> event) {
                System.out.println("----EditCommit()---- ");
                Person p = event.getRowValue();
                p.setAge(event.getNewValue());
            }
        });


        tableView.getColumns().add(firstNameColum);
        tableView.getColumns().add(surNameColum);
        tableView.getColumns().add(ageColum);
        tableView.setEditable(true);

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void addTestContent(ObservableList<Person> pers) {


        pers.add(new Person("Roald", "Amundsen", 55));
        pers.add(new Person("Fridtjof", "Nansen"));
        pers.add(new Person("Otto", "Sverdrup"));
        addMultiple(pers);
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
        Button print = new Button("Ausdrucken");
        print.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Button Print was clicked");
                ObservableList all = tableView.getItems();
                for (Object p : all
                ) {
                    System.out.println(p.toString());

                }
            }
        });
        hBox1.getChildren().addAll(add, delete, print);
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

    public void addMultiple(ObservableList<Person> pers) {
        ObservableList<Person> tempPers = FXCollections.observableArrayList();
        tempPers.add(new Person("Robert", "Pery"));
        tempPers.add(new Person("John", "Franklin"));
        tempPers.add(new Person("James", "Ross"));
        tempPers.add(new Person("Robert", "Scott"));
        tempPers.add(new Person("Umberto", "Noblie"));
        tempPers.add(new Person("Fredrick", "Cook"));
        tempPers.add(new Person("Adrian", "Gerlache"));
        tempPers.add(new Person("Ernest", "Shackelton"));
        tempPers.add(new Person("Olav", "Bjaaland", 70));
        tempPers.add(new Person("Helmer", "Hanssen"));
        tempPers.add(new Person("Sverre", "Hassel"));
        tempPers.add(new Person("Oscar", "Wisting"));
        pers.addAll(tempPers);
    }
}