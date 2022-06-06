package com.example.uts_10119294_lingga.views.fragments;

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

import com.example.uts_10119294_lingga.R;
import com.example.uts_10119294_lingga.contract.TodoContract;
import com.example.uts_10119294_lingga.models.Todo;
import com.example.uts_10119294_lingga.presenter.TodoPresenter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class BottomSheetDialog extends BottomSheetDialogFragment  implements TodoContract.View{

    private TodoPresenter presenter;
    private EditText inptd;
    private EditText inptdesc;
    private Button btnadd;
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

        btnadd.setOnClickListener(view -> {
            Todo todo = new Todo();
            todo.setTodo(inptd.getText().toString());
            todo.setDescription(inptdesc.getText().toString());
            todo.setTanggal("12-01-2020");
            todo.setDone(0);
            presenter.saveTodo(todo);
            dismiss();
        });
//        Button algo_button = v.findViewById(R.id.algo_button);
//        Button course_button = v.findViewById(R.id.course_button);

//        algo_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                Toast.makeText(getActivity(),
//                        "Algorithm Shared", Toast.LENGTH_SHORT)
//                        .show();
//                dismiss();
//            }
//        });
//
//        course_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                Toast.makeText(getActivity(),
//                        "Course Shared", Toast.LENGTH_SHORT)
//                        .show();
//                dismiss();
//            }
//        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        System.out.println("ini dismiss");
    }

    @Override
    public void showMessage(String message) {
        System.out.println("tes");
    }

    @Override
    public void fetchTodo(List<Todo> items) {

    }
}
