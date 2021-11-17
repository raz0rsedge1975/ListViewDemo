package sample;

import handler.DelHandler;
import handler.SortHandler;
import handler.StoreHandler;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RunFX extends Application {

    public final static ObservableList<Buch> backend = FXCollections.observableArrayList();

    @Override
    public void start(Stage window) {
        final SplitPane root = new SplitPane();
        final ListView<Buch> buchList = new ListView<Buch>(); //Ist nur eine Ansicht
        final Text leerLabel = new Text();
        final Text titelLabel = new Text(" Titel");
        final Text autorLabel = new Text(" Author");
        final Text jahrLabel = new Text(" Year");

        TextField titelTextField = new TextField("");
        titelTextField.setAlignment(Pos.BASELINE_RIGHT);
        TextField autorTextfield = new TextField("");
        TextField jahrTextfield = new TextField("");
        final TextField[] textFieldArray = {titelTextField,autorTextfield,jahrTextfield};

        final Button bSave = new Button("Save");
        final Button bDelete = new Button("Delete");
        final Button bSort = new Button("Sort");
        final Button[] buttons = {bSave,bDelete,bSort};

        final FlowPane myPane = new FlowPane();
        final SortHandler sortHandler = new SortHandler(bSort,buchList);
        final StoreHandler storeHandler = new StoreHandler(titelTextField,autorTextfield,jahrTextfield, buchList);
        final DelHandler delHandler = new DelHandler(buchList);

        myPane.setHgap(15.);
        myPane.setVgap(5.);
        myPane.setOrientation(Orientation.VERTICAL);
        myPane.setAlignment(Pos.CENTER);

        myPane.getChildren().addAll(titelLabel,
                titelTextField,
                autorLabel,
                autorTextfield,
                jahrLabel,
                jahrTextfield,
                leerLabel,
                bSave,
                bDelete,
                bSort);

        root.setOrientation(Orientation.HORIZONTAL);
        root.getItems().addAll(buchList, myPane);
        root.setPadding(new Insets(15.));
        root.setStyle("-fx-border-color: #222222;-fx-border-size: 5px");
        DropShadow shadow = new DropShadow();
        Scene scene = new Scene(root, 720, 400);
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle("  Most Famous Books ");
        for (TextField textField : textFieldArray) {
            textField.setPrefSize(260., 38.);
            textField.setEditable(true);
            textField.setAlignment(Pos.BASELINE_RIGHT);
            textField.setStyle("-fx-font-size: 18;-fx-border-color: cornflowerblue;" +
                    "-fx-border-size: 9px;-fx-font-weight: bold;" +
                    "-fx-background-color: #D0D0D1;-fx-border-radius: 10px");
        }
        for (Button button : buttons) {

            button.setMaxWidth(Double.MAX_VALUE);
            button.setPadding(new Insets(5.));
            button.setLineSpacing(10.);
            button.setCursor(Cursor.HAND);
            button.setStyle("-fx-font-weight: bold;-fx-font-size: 20;-fx-border-radius: 10px");
            button.setStyle("-fx-background-color: gray");
            button.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
                button.isFocused();
                button.setEffect(shadow);
                button.setStyle("-fx-background-color: cornflowerblue");

            });
            button.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> {
                button.setEffect(null);
                button.setStyle("-fx-background-color: gray");
            });
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                button.setStyle("-fx-background-color: royalblue");
            });

            //actionHandlers
            bDelete.setOnAction(delHandler);
            bSort.setOnAction(sortHandler);
            bSave.setOnAction(storeHandler);
        }
        window.getIcons().add(new Image("books.png"));
        window.isAlwaysOnTop();
        window.show();

        //Backend
        backend.addAll(BuchRegal.getOutBUECHER()); //raus mit de Viecher!!!1!
        backend.add(new Buch("Bauschan","Thomas Mann",1896));
        buchList.setItems(backend);
        buchList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Irgendwas laeuft hier schief, aber nur wenn man nicht hinguckt (Schroedingers Katze laesst gruessen)
        //
        try {
            buchList.getSelectionModel().selectedItemProperty().addListener((observableValue, buch1, buch2) -> {
                buchList.getSelectionModel().getSelectedItem();
                if (!buchList.getSelectionModel().getSelectedItems().isEmpty()) {
                    titelTextField.setText(buchList.getSelectionModel().getSelectedItem().getTitel());
                    autorTextfield.setText(buchList.getSelectionModel().getSelectedItem().getAuthor());
                    jahrTextfield.setText(buchList.getSelectionModel().getSelectedItem().getPublished());
                }
                else if (buchList.getSelectionModel().getSelectedItems().isEmpty()) {
                     titelTextField.setText("");
                     autorTextfield.setText("");
                     jahrTextfield.setText("");
                }
            });
        } catch (NullPointerException e) {
            //buchList.getSelectionModel().selectFirst();
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
