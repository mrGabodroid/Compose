package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.clip
import androidx.ui.core.setContent
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.FilledTextField
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Animation()
        }
    }

    @Preview
    @Composable
    fun ListFilter(
        list: List<String> = listOf(
            "World",
            "Android",
            "Compose",
            "LastPass",
            "LogMeIn"
        )
    ) {
        val filter = state { "" }

        Column {
            FilledTextField(
                value = filter.value,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { filter.value = it },
                label = { Text("Search: ") }
            )

            for (name in list) {
                if (name.toLowerCase().contains(filter.value.toLowerCase())) {
                    Text(
                        name,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(8.dp) + Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    fun NewsStory() {
        val image = imageResource(R.drawable.header)

        MaterialTheme {
            Column(modifier = Modifier.fillMaxHeight()) {
                Column(
                    modifier = Modifier.padding(8.dp).weight(1f)
                ) {
                    val imageModifier = Modifier
                        .preferredHeightIn(maxHeight = 180.dp)
                        .clip(shape = RoundedCornerShape(12.dp))

                    Image(image, modifier = imageModifier, contentScale = ContentScale.Crop)
                    Spacer(Modifier.preferredHeight(16.dp))
                    Text("A day in Shark Fin Cove", style = MaterialTheme.typography.h1)
                    Text("Davenport, California")
                }
                Text("July, 2020")
            }
        }
    }
}

// state
// animation