package com.arvindve.simpletodo.model;

import com.arvindve.simpletodo.TodoDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.UUID;

@Table(database = TodoDatabase.class)
public class Todo extends BaseModel {
    @Column
    @PrimaryKey
    private String id;

    @Column
    private String text;

    @Column
    private boolean completed;

    @Column
    private Date dueDate;

    @Column
    private Date alarmDate;

    private static final String ID_TAG = "TODO_";

    public Todo() {
        super();
    }

    public Todo(String text, Date dueDate, Date alarmDate) {
        super();

        this.id = ID_TAG + UUID.randomUUID().toString();
        this.completed = false;
        this.text = text;
        this.dueDate = dueDate;
        this.alarmDate = alarmDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(Date alarmDate) {
        this.alarmDate = alarmDate;
    }

    @Override
    public String toString() {
        return text;
    }
}