package playground;

import com.example.demotableview.Person;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class ObsarvableListPlayground {
    public static void main(String[] args) {
        ObservableList<Person> ol= FXCollections.observableArrayList();
        ol.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                System.out.println("Es wurde etwas ");
                while (c.next()){
                    if (c.wasAdded()){
                        System.out.println("Hinzugefügt");
                    }
                    if (c.wasReplaced()){System.out.print("Ersetz");}
                    if (c.wasRemoved()){System.out.println("Entfernet");}
                    if (c.wasUpdated()){System.out.println("Aktualisirt");}
                    if (c.wasPermutated()){System.out.println("Kombinirt");}
                }
            }


        });
        ol.add(new Person("Roald", "Amundsen", 55));
        System.out.println("Roald Amundsen hinzugefügt");
        ol.add(new Person("Fridtjof", "Nansen"));
        System.out.println("Fridtjof Nansen hinzugefügt");
        ol.add(new Person("Otto", "Sverdrup"));
        System.out.println("Otto Sverdrup hinzugefügt");
        addAll(ol);
        System.out.println("Es wurde eine Liste von Personen hinzugefügt");
        ol.get(5).setFirstname("John");
        System.out.println("Es wurde ein name Geändert");
        ol.remove(4);
        ol.removeAll();

    }

    public static void addAll(ObservableList<Person> pers) {
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
