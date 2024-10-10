package com.example.taskmanager.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.Model.Task
import com.example.taskmanager.Model.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository = TaskRepository(application)


    private val _allTasks = MutableLiveData<List<Task>>()

    val allTasks: LiveData<List<Task>> get() = _allTasks

    init {
        getAllTasks()
    }

    fun getAllTasks(){
        viewModelScope.launch {
            _allTasks.value = repository.getallTasks() ?: emptyList()
        }
    }


    fun upsert(task: Task) = viewModelScope.launch {
        repository.upsert(task)
        getAllTasks()

    }

    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
        getAllTasks()

    }

    suspend fun getTaskById(taskId: Int): Task {
        return repository.getTaskById(taskId)
    }


}


