package ListView.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import ListView.handler.sample.Buch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortHandler implements EventHandler<ActionEvent> {
    private int counter = 0;
    private final Button button;
    private final ListView<Buch> buchList;
    private final List<String> sortierArt = new ArrayList<>();
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    public SortHandler(Button button, ListView<Buch> buchList) {
        this.button = button;
        this.buchList = buchList;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        sortierArt.add("sorted: title");
        sortierArt.add("sorted: author");
        sortierArt.add("sorted: published");

        if (counter < 2)
            counter++;
        else counter = 0;
        button.setText(sortierArt.get(counter));
        buchList.getSelectionModel().clearSelection();
        switch (counter) {
            case 0 : {
                buchList.getItems().sort(Comparator.comparing(Buch::getTitel));
                System.out.println(ANSI_RED+"sorted: title"+ANSI_YELLOW + buchList.getItems().toString()+ANSI_RESET);
                break;
            }
            case 1 : {
                buchList.getItems().sort(Comparator.comparing(Buch::getAuthor));
                System.out.println(ANSI_RED+"sorted: author"+ANSI_YELLOW + buchList.getItems().toString()+ANSI_RESET);
                break;
            }
            case 2 : {
                buchList.getItems().sort(Comparator.comparing(Buch::getPublished));
                System.out.println(ANSI_RED+"sorted: published"+ANSI_YELLOW + buchList.getItems().toString()+ANSI_RESET);
                break;
            }
            default : {
                buchList.getItems().sort(Comparator.naturalOrder());
                System.out.println("You should never see this...");
                System.out.println(ANSI_RED+"sorted: default"+ANSI_YELLOW + buchList.getItems().toString()+ANSI_RESET);
                break;
            }
        }
    }
}


