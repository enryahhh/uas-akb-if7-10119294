package com.example.uts_10119294_lingga.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_10119294_lingga.R;
import com.example.uts_10119294_lingga.models.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder>{
    private List<Todo> tes;
    public class TodoViewHolder extends RecyclerView.ViewHolder {

        public TextView text_todo;
        public TodoViewHolder(View itemView) {
            super(itemView);
            text_todo = itemView.findViewById(R.id.text_todo);
        }
    }

    private final int mItemCount;

    public TodoAdapter(List<Todo> items) {
        tes = items;
        mItemCount = items.size();
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
        holder.text_todo.setText(tes.get(position).getTodo());
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
