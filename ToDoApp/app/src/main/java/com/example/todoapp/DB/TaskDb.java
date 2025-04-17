package com.example.todoapp.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todoapp.DAO.TaskDao;
import com.example.todoapp.Model.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDb extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static TaskDb instance;

    public static synchronized TaskDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDb.class, "task_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
