package com.example.todoapp.DAO;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Model.Task;
import com.example.todoapp.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    private List<Task> taskList;
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void updateTasks(List<Task> newTasks) {
        this.taskList = newTasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.title.setText(task.title);
        holder.dueDate.setText("Due: " + task.dueDate);

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) clickListener.onItemClick(task);
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onItemLongClick(task);
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView title, dueDate;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            dueDate = itemView.findViewById(R.id.text_view_due_date);
        }
    }

    // Interfaces for click actions
    //For Edit
    public interface OnItemClickListener {
        void onItemClick(Task task);
    }

    //For delete
    public interface OnItemLongClickListener {
        void onItemLongClick(Task task);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }
}
