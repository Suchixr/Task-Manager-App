package com.example.taskmanager.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.Model.Task

@Composable
fun TaskCard(
    data: Task,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    val bgColor = when (data.priority) {
        "High" -> Color(0xFFFFCDD2) // Reddish shade
        "Medium" -> Color(0xFFFFF9C4) // Yellow shade
        "Low" -> Color(0xFFC8E6C9) // Green shade
        else -> Color.LightGray // Default shade for any other case
    }

    Column(
        modifier = Modifier
            .padding(top = 16.dp,start = 16.dp,end = 16.dp)
            .background(
                color = bgColor,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = data.title,
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp)
                    .weight(1f),
                fontSize = 20.sp,
            )
            // Removed the priority text as it's now represented by color
            // You could also keep it, but visually representing priority with color looks more intuitive.
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .padding(12.dp)
                    .clickable { onEdit() }
            )
        }
        Text(
            text = data.description,
            modifier = Modifier.padding(top = 12.dp, start = 12.dp),
            fontSize = 15.sp,
            lineHeight = 20.sp
        )
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = data.dueDate,
                modifier = Modifier.weight(1f),
                fontSize = 13.sp,
                lineHeight = 20.sp
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .clickable { onDelete() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskCard() {
    TaskCard(
        data = Task(
            title = "Important Task",
            description = "This task needs to be done urgently.",
            dueDate = "12/12/2024",
            priority = "High",
            isCompleted = false
        ),
        onDelete = {},
        onEdit = {}
    )
}
