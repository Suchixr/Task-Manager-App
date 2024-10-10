package com.example.taskmanager.Views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.taskmanager.Model.Task
import com.example.taskmanager.ViewModel.TaskViewModel
import kotlinx.coroutines.launch


@Composable
fun AddTaskScreen(
    modifier: Modifier,
    viewModel: TaskViewModel,
    taskToEdit: Task? = null  // Nullable task object, used when editing
) {
    // Pre-fill form fields with task data if editing
    var title by remember { mutableStateOf(taskToEdit?.title ?: "") }
    var description by remember { mutableStateOf(taskToEdit?.description ?: "") }
    var dueDate by remember { mutableStateOf(taskToEdit?.dueDate ?: "") }
    var priority by remember { mutableStateOf(taskToEdit?.priority ?: "") }

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = if (taskToEdit == null) "Add Task" else "Edit Task",  // Update title based on context
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = dueDate,
            onValueChange = { dueDate = it },
            label = { Text("Due Date") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = priority,
            onValueChange = { priority = it },
            label = { Text("Priority") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty() && dueDate.isNotEmpty() && priority.isNotEmpty()) {
                    val newTask = Task(
                        id = taskToEdit?.id ?: 0,  // Use existing ID if editing
                        title = title,
                        description = description,
                        dueDate = dueDate,
                        priority = priority,
                        isCompleted = taskToEdit?.isCompleted ?: false  // Maintain completion status if editing
                    )
                    scope.launch {
                        viewModel.upsert(newTask)  // Update or insert the task
                        Toast.makeText(
                            context,
                            if (taskToEdit == null) "Task Added Successfully!" else "Task Updated Successfully!",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Clear the input fields and hide the keyboard
                        title = ""
                        description = ""
                        dueDate = ""
                        priority = ""
                        focusManager.clearFocus()
                    }
                } else {
                    Toast.makeText(context, "Please fill all fields!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .padding(start = 64.dp, end = 64.dp, top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(if (taskToEdit == null) "Submit" else "Update")  // Change button text based on context
        }
    }
}
