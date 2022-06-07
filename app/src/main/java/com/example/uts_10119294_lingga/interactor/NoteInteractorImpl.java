package com.example.uts_10119294_lingga.interactor;

import com.example.uts_10119294_lingga.contract.NoteContract;
import com.example.uts_10119294_lingga.helpers.DatabaseHelper;
import com.example.uts_10119294_lingga.models.Note;

import java.util.List;

public class NoteInteractorImpl implements NoteContract.Interactor {
    /*
     * NIM : 10119294
     * NAMA : Lingga Juliansyah
     * Kelas : IF-7
     * */
    @Override
    public void addTodo(Note note, DatabaseHelper db) {
        db.openDb();
        db.storeNote(note);
    }

    @Override
    public List<Note> getAllTodo(DatabaseHelper db) {
        db.openDb();
        return db.getAllNote();
    }

    @Override
    public void updateTodo(int id, Note note, DatabaseHelper db) {
        db.openDb();
        db.updateNote(id, note);
        System.out.println("note edit");
    }

    @Override
    public void deleteTodo(int id, DatabaseHelper db) {
        System.out.println("todo deleted");
        db.openDb();
        db.deleteNote(id);
    }
}
