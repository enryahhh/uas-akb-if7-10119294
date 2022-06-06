package com.example.uts_10119294_lingga.interactor;

import com.example.uts_10119294_lingga.contract.TodoContract;
import com.example.uts_10119294_lingga.helpers.DatabaseHelper;
import com.example.uts_10119294_lingga.models.Todo;
import android.view.View;
import java.util.List;

public class TodoInteractorImpl implements TodoContract.Interactor {

    @Override
    public void addTodo(Todo todo, DatabaseHelper db) {
        db.openDb();
        db.storeTodo(todo);
    }

    @Override
    public List<Todo> getAllTodo(DatabaseHelper db) {
        db.openDb();
        return db.getAllTodo();
    }

    @Override
    public void deleteTodo(int id, DatabaseHelper db) {
        System.out.println("todo deleted");
        db.openDb();
        db.deleteTodo(id);
    }
}
