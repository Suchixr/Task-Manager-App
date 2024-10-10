package com.example.taskmanager.Views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmanager.Model.Task
import com.example.taskmanager.ViewModel.TaskViewModel

@Composable
fun TaskListScreen(
    modifier: Modifier,
    viewModel: TaskViewModel,
    tasks: List<Task>,
    onEdit: (Task) -> Unit  // Edit action callback
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text(
                text = "Total Tasks: ${tasks.size}",
                modifier = Modifier.padding(16.dp)
            )
        }

        items(tasks) { task ->
            TaskCard(
                data = task,
                onDelete = { viewModel.delete(task) },
                onEdit = { onEdit(task) }  // Trigger edit action
            )
        }
    }
}
