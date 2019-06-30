package com.RadoslawGornik.JavaFx.todolist.datamodel;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class TodoData {
    private static TodoData instance = new TodoData();
    private static String filname = "TodoListItems.txt";

    private List<TodoItem> todoItemList;
    private DateTimeFormatter formatter;

    public static TodoData getInstance(){
        return instance;
    }

    private TodoData(){
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public List<TodoItem> getTodoItemList() {
        return todoItemList;
    }
//
//    public void setTodoItemList(List<TodoItem> todoItemList) {
//        this.todoItemList = todoItemList;
//    }

    public void loadTodoItems() throws IOException{

        todoItemList = FXCollections.observableArrayList();
        Path path = Paths.get(filname);
        BufferedReader bufferedReader = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = bufferedReader.readLine()) != null) {

                String[] iteamPices = input.split("\t");
                String shortDescription = iteamPices[0];
                String details = iteamPices[1];
                String dateString = iteamPices[2];

                LocalDate date = LocalDate.parse(dateString, formatter);

                TodoItem todoItem = new TodoItem(shortDescription,details,date);
                todoItemList.add(todoItem);
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    public void storeTodoItems() throws IOException{

        Path path = Paths.get(filname);
        BufferedWriter bufferedWriter = Files.newBufferedWriter(path);

        try {
            Iterator<TodoItem> itemIterator = todoItemList.iterator();
            while (itemIterator.hasNext()) {

                TodoItem item = itemIterator.next();

                bufferedWriter.write(String.format("%s\t%s\t%s",
                        item.getShortDesrciption(),
                        item.getDetails(),
                        item.getDeadline().format(formatter)));

                bufferedWriter.newLine();
            }
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }
}
