package com.example.uts_10119294_lingga.contract;

import com.example.uts_10119294_lingga.helpers.DatabaseHelper;
import com.example.uts_10119294_lingga.models.Todo;
import android.view.View;

import java.util.List;

public class TodoContract {
    public interface View{
        void showMessage(String message);
        void fetchTodo(List<Todo> items);
    }

    public interface Interactor{
        void addTodo(Todo todo,DatabaseHelper db);
        List<Todo> getAllTodo(DatabaseHelper db);
        void deleteTodo(int id,DatabaseHelper db);
    }

    public interface Presenter{
        void saveTodo(Todo todo);
        void loadTodo();
    }


}
