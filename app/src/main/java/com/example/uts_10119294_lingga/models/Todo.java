package com.example.uts_10119294_lingga.models;

import java.io.Serializable;
import java.util.Date;

public class Todo implements Serializable {
    private int id,isDone;
    private String todo;
    private String tanggal;
    private String description;

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getTodo() {
        return todo;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(int done) {
        isDone = done;
    }

    public int isDone() {
        return isDone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
