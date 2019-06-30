package com.RadoslawGornik.JavaFx.todolist;

import com.RadoslawGornik.JavaFx.todolist.datamodel.TodoData;
import com.RadoslawGornik.JavaFx.todolist.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import java.time.format.DateTimeFormatter;


public class Controller {


    @FXML
    private ListView<TodoItem> todolistView;

    @FXML
    private TextArea itemDetailsTextArea;

    @FXML
    private Label deadlineLabel;

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
    public void handleClickListView(){

        TodoItem item = todolistView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());
    }
}
