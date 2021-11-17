package ListView.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import ListView.handler.sample.Buch;
import ListView.handler.sample.RunFX;

public class DelHandler implements EventHandler<ActionEvent> {

    private final ListView<Buch> buchList;

    public DelHandler(ListView<Buch> buchList) {
        this.buchList = buchList;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        if (!buchList.getSelectionModel().getSelectedItems().isEmpty()) {
        RunFX.backend.remove(buchList.getSelectionModel().getSelectedItem());
        }
        buchList.getSelectionModel().clearSelection();
    }
}
