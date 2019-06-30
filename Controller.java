package com.RadoslawGornik.JavaFx.todolist;

import com.RadoslawGornik.JavaFx.todolist.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<TodoItem> todoItemList;

    @FXML
    private ListView<TodoItem> todolistView;

    @FXML
    private TextArea itemDetailsTextArea;

    @FXML
    private Label deadlineLabel;

    public void initialize(){
        TodoItem item1 = new TodoItem("Mail brithday card" ,
                "Buy a 30th brithday card for John",
                LocalDate.of(2016,Month.APRIL,25));
        TodoItem item2 = new TodoItem("Doctor's Appointment" ,
                "See Dr. Smith at 123 Main Street. Brong paperwork",
                LocalDate.of(2016,Month.MAY,23));
        TodoItem item3 = new TodoItem("Finish designe proposal for client" ,
                "I promised Mike i'd email website mockups by Friday 22nd April",
                LocalDate.of(2016,Month.APRIL,22));
        TodoItem item4 = new TodoItem("Pickup DOug at the train station" ,
                "Doug's arriving on March 23 on the 5:00 train",
                LocalDate.of(2016,Month.MARCH,23));
        TodoItem item5 = new TodoItem("Pick up dry cleaning" ,
                "The clothes should be ready by Wednesday",
                LocalDate.of(2016,Month.APRIL,20));

        todoItemList = new ArrayList<>();
        todoItemList.add(item1);
        todoItemList.add(item2);
        todoItemList.add(item3);
        todoItemList.add(item4);
        todoItemList.add(item5);

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

        todolistView.getItems().setAll(todoItemList);
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
