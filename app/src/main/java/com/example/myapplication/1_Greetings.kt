package com.example.myapplication

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.material.Divider
import androidx.ui.material.Surface
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

@Composable
fun Greetings(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.padding(24.dp), color = Color.White)
}

@Composable
fun ListOfGreetings(names: List<String> = listOf("World", "Android")) {
    Column {
        for (name in names) {
            Greetings(name = name)
            Divider(color = Color.Yellow)
        }
    }
}

@Composable
fun BlueContainer(content: @Composable() () -> Unit) {
    Surface(color = Color.Blue) {
        content()
    }
}

@Preview
@Composable
fun GreetingsPreview() {
    Greetings("Compose")
}

@Preview
@Composable
fun ListOfGreetingsPreview() {
    ListOfGreetings()
}

@Preview
@Composable
fun BlueGreetingsPreview() {
    BlueContainer(content = {
        Greetings(name = "Blue")
    })
}