package org.iniad.se.myapplication

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.iniad.se.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyTodoApp()
                }
            }
        }
    }
}

@Composable
fun MyTodoApp() {
    val todo = remember { mutableStateOf("") }
    val todoList = remember { mutableStateListOf<String>() }

    MyTodoAppContent(todo = todo, todoList = todoList)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTodoAppContent(
    todo: MutableState<String>,
    todoList: SnapshotStateList<String>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My TODO") }
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .padding(paddingValue)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                TextField(
                    value = todo.value,
                    onValueChange = { text -> todo.value = text },
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .weight(1f)
                )
                Button(onClick = {
                    todoList.add(todo.value)
                    todo.value = ""
                }) {
                    Text(text = "追加")
                }
            }
            todoList.forEachIndexed { index, item ->
                TodoItem(
                    text = item,
                    deleteTodo = { todoList.removeAt(index) }
                )
            }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "行ってらっしゃい。 $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        MyTodoApp()
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MyTodoAppContent(
            todo = remember { mutableStateOf("文字を入れています???") },
            todoList = remember { mutableStateListOf("長い長い長い長い長い長い長い長い長い長い長い長い長い TODO", "TODO 2") }
        )
    }
}
@Composable
fun TodoItem(text: String, deleteTodo: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = text,
            modifier = Modifier
                .padding(vertical = 4.dp)
                .weight(1f)
        )
        IconButton(onClick = { deleteTodo() }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription ="削除ボタン")
        }
    }
}