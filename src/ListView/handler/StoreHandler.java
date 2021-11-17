package ListView.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ListView.handler.sample.Buch;
import ListView.handler.sample.RunFX;

public class StoreHandler implements EventHandler<ActionEvent> {

    private final TextField titel;
    private final TextField author;
    private final TextField year;
    private final ListView<Buch> buchList;

    public StoreHandler(TextField titel, TextField author, TextField year, ListView<Buch> buchList) {
            this.titel = titel;
            this.author = author;
            this.year = year;
            this.buchList = buchList;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (buchList.getSelectionModel().getSelectedItems().isEmpty()) {
            if (!titel.getText().strip().isBlank() && !author.getText().strip().isBlank() && !year.getText().strip().isBlank())
                try {
                    RunFX.backend.add(new Buch(titel.getText().strip(), author.getText().strip(), year.getText().strip()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("INPUT ERROR");
                }
        }
    }
}
