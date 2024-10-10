package com.example.taskmanager.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TaskDao {

    @Upsert
    suspend fun upsert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task_table")
    suspend fun getAllTasks(): List<Task>

    @Query("SELECT * FROM task_table WHERE id = :taskId LIMIT 1")
    suspend fun getTaskById(taskId: Int): Task
}


