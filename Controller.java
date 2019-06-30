package com.RadoslawGornik.JavaFx.todolist;
import com.RadoslawGornik.JavaFx.todolist.datamodel.TodoData;
import com.RadoslawGornik.JavaFx.todolist.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Controller {


    @FXML
    private ListView<TodoItem> todolistView;

    @FXML
    private TextArea itemDetailsTextArea;

    @FXML
    private Label deadlineLabel;

    @FXML
    private BorderPane mainBorderPane;

    public void initialize(){


        todolistView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {

                    if (newValue != null) {
                    TodoItem item = todolistView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadlineLabel.setText(dateTimeFormatter.format(item.getDeadline()));
                }
            }
        });

        todolistView.getItems().setAll(TodoData.getInstance().getTodoItemList());
        todolistView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todolistView.getSelectionModel().selectFirst();
    }

    @FXML
    public void showNewItemDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldnt load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            controller.processResults();
            System.out.println("OK pressed");

        } else {
            System.out.println("Cancel preseed");
        }
    }
    @FXML
    public void handleClickListView(){

        TodoItem item = todolistView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());
    }
}
