package com.example.taskmanager.Views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmanager.Model.Task
import com.example.taskmanager.ViewModel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskApp() {
    var selectedScreen by remember { mutableStateOf("Home") }
    var taskToEdit by remember { mutableStateOf<Task?>(null) }  // Task to be edited

    val viewModel: TaskViewModel = viewModel()
    val tasks by viewModel.allTasks.observeAsState(initial = emptyList())

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedScreen == "Home",
                    onClick = {
                        selectedScreen = "Home"
                        taskToEdit = null  // Clear the edit state when navigating to Home
                    },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = selectedScreen == "AddTask",
                    onClick = {
                        selectedScreen = "AddTask"
                        taskToEdit = null  // Clear the edit state when navigating to Add Task
                    },
                    icon = { Icon(Icons.Default.Add, contentDescription = "Add Task") },
                    label = { Text("Add Task") }
                )
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Task Manager") }
            )
        }
    ) {
        when (selectedScreen) {
            "Home" -> TaskListScreen(
                modifier = Modifier.padding(it),
                viewModel = viewModel,
                tasks = tasks,
                onEdit = { task ->
                    taskToEdit = task  // Set the task to be edited
                    selectedScreen = "AddTask"  // Navigate to AddTaskScreen
                }
            )
            "AddTask" -> AddTaskScreen(
                modifier = Modifier.padding(it),
                viewModel = viewModel,
                taskToEdit = taskToEdit  // Pass the task to be edited (if any)
            )
        }
    }
}
