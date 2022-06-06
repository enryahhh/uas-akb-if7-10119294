package com.example.uts_10119294_lingga.presenter;


import android.content.Context;
import android.view.View;

import com.example.uts_10119294_lingga.contract.TodoContract;
import com.example.uts_10119294_lingga.helpers.DatabaseHelper;
import com.example.uts_10119294_lingga.interactor.TodoInteractorImpl;
import com.example.uts_10119294_lingga.models.Todo;

public class TodoPresenter implements TodoContract.Presenter {
    private TodoContract.View v;
    private DatabaseHelper db;
    private TodoInteractorImpl intr = new TodoInteractorImpl();
    public TodoPresenter(TodoContract.View v, Context ctx){
        this.v = v;
        this.db = new DatabaseHelper(ctx);
    }
    @Override
    public void saveTodo(Todo todo) {
        intr.addTodo(todo,db);
        v.fetchTodo(intr.getAllTodo(db));
    }

    @Override
    public void loadTodo() {
            v.fetchTodo(intr.getAllTodo(db));
    }

    @Override
    public void removeTodo(int id){
        intr.deleteTodo(id,db);
        v.fetchTodo(intr.getAllTodo(db));
    }

    @Override
    public void editTodo(Todo todo) {
        intr.updateTodo(todo.getId(),todo,db);
        v.fetchTodo(intr.getAllTodo(db));
    }
}
