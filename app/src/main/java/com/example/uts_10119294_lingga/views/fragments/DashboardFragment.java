package com.example.uts_10119294_lingga.views.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_10119294_lingga.R;
import com.example.uts_10119294_lingga.adapter.NoteAdapter;
import com.example.uts_10119294_lingga.contract.NoteContract;

import com.example.uts_10119294_lingga.databinding.FragmentDashboardBinding;
import com.example.uts_10119294_lingga.helpers.DialogCloseListener;
import com.example.uts_10119294_lingga.models.Note;
import com.example.uts_10119294_lingga.presenter.NotePresenter;

import java.util.List;

public class DashboardFragment extends Fragment implements NoteContract.View , DialogCloseListener {
    /*
     * NIM : 10119294
     * NAMA : Lingga Juliansyah
     * Kelas : IF-7
     * */
    private FragmentDashboardBinding binding;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private List<Note> notes;
    private NotePresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        presenter = new NotePresenter(this,getContext());
//        final TextView textView = binding.textDashboard;
//        textView.setText("Dashbor");
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = binding.recyclerViewTodo;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.loadTodo();


        binding.floatingActionButton.setOnClickListener(View->{
            BottomSheetDialog bottomSheet = new BottomSheetDialog();
            bottomSheet.show(getChildFragmentManager(),"dialog");
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    private List<Note> dummyTodo(){
//        List<Note> todosnya = new ArrayList<>();
//        for(int i=1;i<=5;i++){
//            Note todo = new Note();
//            todo.setTodo("coba");
//            todo.setDescription("tes aja");
//            todosnya.add(todo);
//        }
//        return todosnya;
//    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fetchTodo(List<Note> items) {
        notes = items;
        adapter = new NoteAdapter(notes,presenter,getChildFragmentManager());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void handleDialogClose(DialogInterface dialogInterface) {
//        DashboardFragment df = new DashboardFragment();
//        FragmentManager fm = getChildFragmentManager();
//        fm.beginTransaction().replace(R.id.todoFrag,DashboardFragment.class,null)
//                .setReorderingAllowed(true).addToBackStack(null).commit();
//        df.handleDialogClose(dialogInterface);
        System.out.println("ini presenter df "+presenter);
        presenter.loadTodo();

    }
}