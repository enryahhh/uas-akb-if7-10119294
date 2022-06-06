package com.example.uts_10119294_lingga.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_10119294_lingga.R;
import com.example.uts_10119294_lingga.models.Todo;
import com.example.uts_10119294_lingga.presenter.TodoPresenter;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder>{
    private List<Todo> tes;
    private TodoPresenter presenter;
    public class TodoViewHolder extends RecyclerView.ViewHolder {

        public TextView text_todo;
        public TodoViewHolder(View itemView) {
            super(itemView);
            text_todo = itemView.findViewById(R.id.text_todo);
        }
    }

    private final int mItemCount;

    public TodoAdapter(List<Todo> items,TodoPresenter presenter) {
        tes = items;
        mItemCount = items.size();
        this.presenter = presenter;
    }


    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View todoView = inflater.inflate(R.layout.todo_item, parent, false);

        // Return a new holder instance
        TodoViewHolder viewHolder = new TodoViewHolder(todoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.text_todo.setText(String.valueOf(tes.get(position).getId()));
        holder.itemView.findViewById(R.id.btn_delete).setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Delete Task");
            builder.setMessage("Are you sure you want to delete this Task?");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.removeTodo(tes.get(holder.getAdapterPosition()).getId());
                            System.out.println(tes.get(holder.getAdapterPosition()).getId());
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
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public void setTodoList(List<Todo> todos){
        this.tes = todos;
        notifyDataSetChanged();
    }
}
