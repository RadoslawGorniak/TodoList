package com.RadoslawGornik.JavaFx.todolist.datamodel;

import java.time.LocalDate;

public class TodoItem {

    private String shortDesrciption;
    private String details;
    private LocalDate deadline;

    public TodoItem(String shortDesrciption, String details, LocalDate deadline) {
        this.shortDesrciption = shortDesrciption;
        this.details = details;
        this.deadline = deadline;
    }

    public String getShortDesrciption() {
        return shortDesrciption;
    }

    public void setShortDesrciption(String shortDesrciption) {
        this.shortDesrciption = shortDesrciption;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
