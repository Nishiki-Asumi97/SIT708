package com.example.todoapp.View;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.todoapp.DAO.TaskDao;
import com.example.todoapp.DB.TaskDb;
import com.example.todoapp.Model.Task;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskDao taskDao;
    private List<Task> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        TaskDb database = TaskDb.getInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public List<Task> getAllTasks() {
        return allTasks;
    }

    public void insert(Task task) {
        new InsertTaskAsync(taskDao).execute(task);
    }

    public void update(Task task) {
        new UpdateTaskAsync(taskDao).execute(task);
    }

    public void delete(Task task) {
        new DeleteTaskAsync(taskDao).execute(task);
    }

    // AsyncTask for DB operations
    //ADD
    private static class InsertTaskAsync extends AsyncTask<Task, Void, Void> {
        private TaskDao dao;

        private InsertTaskAsync(TaskDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            dao.insert(tasks[0]);
            return null;
        }
    }

    //UPDATE
    private static class UpdateTaskAsync extends AsyncTask<Task, Void, Void> {
        private TaskDao dao;

        private UpdateTaskAsync(TaskDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            dao.update(tasks[0]);
            return null;
        }
    }

    //DELETE
    private static class DeleteTaskAsync extends AsyncTask<Task, Void, Void> {
        private TaskDao dao;

        private DeleteTaskAsync(TaskDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            dao.delete(tasks[0]);
            return null;
        }
    }
}

