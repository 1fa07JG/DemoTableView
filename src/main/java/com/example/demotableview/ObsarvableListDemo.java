package com.example.demotableview;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ObsarvableListDemo {
    public static void main(String[] args) {
        ObservableList<String> ol= FXCollections.observableArrayList();
        ol.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                System.out.println("Es wurde etwas geändert");
                while (c.next()){
                    if (c.wasAdded()){}
                    if (c.wasReplaced()){}
                    if (c.wasRemoved()){}
                    if (c.wasUpdated()){}
                    if (c.wasPermutated()){}
                }
            }

        });
    }
}
