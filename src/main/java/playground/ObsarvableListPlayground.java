package playground;

import com.example.demotableview.Person;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ObsarvableListPlayground {
    public static void main(String[] args) {

        ObservableList<Person> ol= FXCollections.observableArrayList();
        ol.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                System.out.print("Es wurde etwas ");
                while (c.next()){
                    if (c.wasAdded()){System.out.print("Hinzugefügt\n");}
                    if (c.wasReplaced()){System.out.print("Ersetzt\n");}
                    if (c.wasRemoved()){System.out.print("Entfernt\n");}
                    if (c.wasUpdated()){System.out.print("Aktualisiert\n");}
                    if (c.wasPermutated()){System.out.print("Kombiniert\n");}
                }
            }


        });
        ol.add(new Person("Roald", "Amundsen", 55));
        System.out.println("Roald Amundsen hinzugefügt");
        ol.add(new Person("Fridtjof", "Nansen"));
        System.out.println("Fridtjof Nansen hinzugefügt");
        ol.add(new Person("Otto", "Sverdrup"));
        System.out.println("Otto Sverdrup hinzugefügt");
        addMultiple(ol);
        System.out.println("Es wurde eine Liste von Personen hinzugefügt");
        ol.get(5).setFirstname("John");
        System.out.println("Es wurde ein name Geändert");
        ol.remove(4);
        ol.removeAll(ol);
        printOl(ol);

    }

    private static void printOl(ObservableList<Person> ol) {
        for (Person p: ol
             ) {
            System.out.println(p.toString());
        }
    }

    public static void addMultiple(ObservableList<Person> pers) {
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
