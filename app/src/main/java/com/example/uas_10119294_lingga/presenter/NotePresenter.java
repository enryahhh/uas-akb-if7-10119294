package com.example.uas_10119294_lingga.presenter;


import android.content.Context;

import com.example.uas_10119294_lingga.adapter.NoteAdapter;
import com.example.uas_10119294_lingga.contract.NoteContract;
import com.example.uas_10119294_lingga.helpers.DatabaseHelper;
import com.example.uas_10119294_lingga.interactor.NoteInteractorImpl;
import com.example.uas_10119294_lingga.models.Note;
/*
 * NIM : 10119294
 * NAMA : Lingga Juliansyah
 * Kelas : IF-7
 * */
public class NotePresenter implements NoteContract.Presenter {
    private NoteContract.View v;
    private DatabaseHelper db;
    private NoteInteractorImpl intr = new NoteInteractorImpl();
    private NoteAdapter noteAdapter;
    public NotePresenter(NoteContract.View v, Context ctx){
        this.v = v;
        this.db = new DatabaseHelper(ctx);
    }
    @Override
    public void saveTodo(Note note) {
        intr.addTodo(note,db);
        v.fetchTodo(intr.getAllTodo(db));
        v.showMessage("Berhasil Menambahkan");
    }

    @Override
    public void loadTodo() {
            v.fetchTodo(intr.getAllTodo(db));
    }

    @Override
    public void removeTodo(int id){
        intr.deleteTodo(id,db);
        v.fetchTodo(intr.getAllTodo(db));
        v.showMessage("Berhasil Menghapus");
    }

    @Override
    public void editTodo(Note note) {
        intr.updateTodo(note.getId(), note,db);
        v.fetchTodo(intr.getAllTodo(db));
        v.showMessage("Berhasil Mengubah");
    }
}
