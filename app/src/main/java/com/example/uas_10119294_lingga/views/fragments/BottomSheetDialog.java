package com.example.uas_10119294_lingga.views.fragments;

import android.content.DialogInterface;
import android.view.LayoutInflater;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.uas_10119294_lingga.MainActivity;
import com.example.uas_10119294_lingga.R;
import com.example.uas_10119294_lingga.adapter.NoteAdapter;
import com.example.uas_10119294_lingga.contract.NoteContract;
import com.example.uas_10119294_lingga.helpers.DialogCloseListener;
import com.example.uas_10119294_lingga.models.Note;
import com.example.uas_10119294_lingga.presenter.NotePresenter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/*
 * NIM : 10119294
 * NAMA : Lingga Juliansyah
 * Kelas : IF-7
 * */
public class BottomSheetDialog extends BottomSheetDialogFragment  implements NoteContract.View{

    private NotePresenter presenter;
    private EditText inpt_judul,inpt_kategori,inpt_isi;
    private Button btnadd;
    private Note note;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    DashboardFragment df;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,
                container, false);
         inpt_judul = v.findViewById(R.id.inpt_judul);
         inpt_kategori = v.findViewById(R.id.inpt_kategori);
         inpt_isi = v.findViewById(R.id.isi);
         btnadd = v.findViewById(R.id.btn_simpan);

        presenter = new NotePresenter(this,getContext());


        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean isUpdate = false;
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
//        df = (DashboardFragment) fragmentManager.findFragmentById(R.id.todoFrag);
        inpt_judul = requireView().findViewById(R.id.inpt_judul);
        final Bundle bundle = getArguments();
        df = (DashboardFragment) getParentFragment();
        if(bundle != null){
            isUpdate = true;
            note = (Note) getArguments().getSerializable("note");
            inpt_judul.setText(note.getJudul());
            inpt_kategori.setText(note.getKategori());
            inpt_isi.setText(note.getIsi());
            assert note != null;
        }
        final boolean finalIsUpdate = isUpdate;
        btnadd.setOnClickListener(view2 -> {

            String judul = inpt_judul.getText().toString();
            String kategori = inpt_kategori.getText().toString();
            String isi = inpt_isi.getText().toString();
            if(finalIsUpdate){
                note.setJudul(judul);
                note.setKategori(kategori);
                note.setIsi(isi);
                presenter.editTodo(note);
            }else{
                Note note = new Note();
                note.setJudul(judul);
                note.setKategori(kategori);
                note.setIsi(isi);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                note.setTanggal(formatter.format(date));
                presenter.saveTodo(note);
            }
            dismiss();
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        System.out.println("ini dismiss");

//        System.out.println(act.toString());
        System.out.println(df);
        df.handleDialogClose(dialog);
//        if(act instanceof DialogCloseListener)
//            System.out.println("yeee handle");
//            act.handleDialogClose(dialog);
    }

    @Override
    public void showMessage(String message) {
        df.showMessage(message);
    }

    @Override
    public void fetchTodo(List<Note> items) {
        NoteAdapter np = new NoteAdapter(items,presenter,getParentFragmentManager());
        np.notifyDataSetChanged();
    }
}
