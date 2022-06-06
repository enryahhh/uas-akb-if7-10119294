package com.example.uts_10119294_lingga.contract;

import com.example.uts_10119294_lingga.helpers.DatabaseHelper;
import com.example.uts_10119294_lingga.models.Note;

import java.util.List;

public class NoteContract {
    public interface View{
        void showMessage(String message);
        void fetchTodo(List<Note> items);
    }

    public interface Interactor{
        void addTodo(Note note, DatabaseHelper db);
        List<Note> getAllTodo(DatabaseHelper db);
        void deleteTodo(int id,DatabaseHelper db);
        void updateTodo(int id, Note note, DatabaseHelper db);
    }

    public interface Presenter{
        void saveTodo(Note note);
        void loadTodo();
        void removeTodo(int id);
        void editTodo(Note note);
    }


}
