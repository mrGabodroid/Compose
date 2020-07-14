package com.example.myapplication

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.material.Button
import androidx.ui.tooling.preview.Preview


@Preview
@Composable
fun Counter() {
    val count = state { 0 }

    Button(
        text = { Text("I've been ${count.value} clicked times") },
        backgroundColor = Color.White,
        onClick = { count.value++ }
    )
}