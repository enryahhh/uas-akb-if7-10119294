package com.example.uas_10119294_lingga.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_10119294_lingga.R;
import com.example.uas_10119294_lingga.models.Note;
import com.example.uas_10119294_lingga.presenter.NotePresenter;
import com.example.uas_10119294_lingga.views.fragments.BottomSheetDialog;

import java.io.Serializable;
import java.util.List;
/*
 * NIM : 10119294
 * NAMA : Lingga Juliansyah
 * Kelas : IF-7
 * */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{
    private List<Note> tes;
    private NotePresenter presenter;
    private FragmentManager fm;
    public class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView text_judul,text_tgl,text_kategori,text_isi;
        public NoteViewHolder(View itemView) {
            super(itemView);
            text_judul = itemView.findViewById(R.id.text_judul);
            text_tgl = itemView.findViewById(R.id.text_tgl);
            text_kategori = itemView.findViewById(R.id.text_kategori);
            text_isi = itemView.findViewById(R.id.text_isi);
        }
    }

    private final int mItemCount;

    public NoteAdapter(List<Note> items, NotePresenter presenter, FragmentManager fm) {
        tes = items;
        mItemCount = items.size();
        this.presenter = presenter;
        this.fm = fm;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View todoView = inflater.inflate(R.layout.todo_item, parent, false);

        // Return a new holder instance
        NoteViewHolder viewHolder = new NoteViewHolder(todoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = tes.get(position);
        holder.text_judul.setText(note.getJudul());
        holder.text_tgl.setText(note.getTanggal());
        holder.text_kategori.setText(note.getKategori());
        holder.text_isi.setText(note.getIsi());
        System.out.println(note.getTanggal());

        holder.itemView.findViewById(R.id.btn_delete).setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Hapus Catatan");
            builder.setMessage("Anda yakin ingin menghapus catatan ini?");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.removeTodo(note.getId());
                            System.out.println(note.getId());
                            notifyItemRemoved(holder.getAdapterPosition());
                            dialog.dismiss();

                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        holder.itemView.findViewById(R.id.btn_edit).setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("note", (Serializable) note);
            bundle.putInt("id", note.getId());
            BottomSheetDialog inputView = new BottomSheetDialog();
            inputView.setArguments(bundle);
            inputView.show(fm,inputView.getTag());

        });
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public void setTodoList(List<Note> notes){
        this.tes = notes;
        notifyDataSetChanged();
    }
}
