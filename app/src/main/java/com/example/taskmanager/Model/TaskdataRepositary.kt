package com.example.taskmanager.Model

import android.content.Context

class TaskRepository(context: Context) {

    private val taskDao = TaskDatabase.getDatabase(context).taskDao()

    suspend fun getallTasks(): List<Task> {
        return taskDao.getAllTasks()
    }

    suspend fun upsert(task: Task) {
        taskDao.upsert(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun getTaskById(taskId: Int): Task {
        return taskDao.getTaskById(taskId)
    }
}
