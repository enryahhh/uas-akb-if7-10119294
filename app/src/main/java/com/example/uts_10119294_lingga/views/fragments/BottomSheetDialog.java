package com.example.uts_10119294_lingga.views.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.uts_10119294_lingga.MainActivity;
import com.example.uts_10119294_lingga.R;
import com.example.uts_10119294_lingga.contract.TodoContract;
import com.example.uts_10119294_lingga.helpers.DialogCloseListener;
import com.example.uts_10119294_lingga.models.Todo;
import com.example.uts_10119294_lingga.presenter.TodoPresenter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BottomSheetDialog extends BottomSheetDialogFragment  implements TodoContract.View{

    private TodoPresenter presenter;
    private EditText inptd;
    private EditText inptdesc;
    private Button btnadd;
    private Todo todoo;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,
                container, false);
         inptd = v.findViewById(R.id.text_todo);
         inptdesc = v.findViewById(R.id.descrip);
         btnadd = v.findViewById(R.id.btn_simpan);

        presenter = new TodoPresenter(this,getContext());


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean isUpdate = false;
        inptd = requireView().findViewById(R.id.text_todo);
        final Bundle bundle = getArguments();

        if(bundle != null){
            isUpdate = true;
            todoo = (Todo) getArguments().getSerializable("todo");
            inptd.setText(todoo.getTodo());
            inptdesc.setText(todoo.getDescription());
            assert todoo != null;
        }
        final boolean finalIsUpdate = isUpdate;
        btnadd.setOnClickListener(view2 -> {

            String tods = inptd.getText().toString();
            String desc = inptdesc.getText().toString();
            if(finalIsUpdate){
                  todoo.setTodo(tods);
                  todoo.setDescription(desc);
                  presenter.editTodo(todoo);
            }else{
                Todo todo = new Todo();
                todo.setTodo(inptd.getText().toString());
                todo.setDescription(inptdesc.getText().toString());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                todo.setTanggal(formatter.format(date));
                todo.setDone(0);
                presenter.saveTodo(todo);
            }
            dismiss();
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        System.out.println("ini dismiss");
//        MainActivity act = (MainActivity) getActivity();
//        if(act instanceof DialogCloseListener)
//            ((DialogCloseListener)act).handleDialogClose(dialog);
    }

    @Override
    public void showMessage(String message) {
        System.out.println("tes");
    }

    @Override
    public void fetchTodo(List<Todo> items) {

    }
}
