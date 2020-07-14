package com.example.myapplication

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.tag
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.layout.ConstraintLayout
import androidx.ui.layout.ConstraintSet
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

@Preview
@Composable
fun ConstraintLayout() {
    ConstraintLayout(constraintSet = ConstraintSet {
        val decreaseConstraint = tag("DecreaseTag").apply {
            left constrainTo parent.left
            centerVertically()
        }

        val increaseConstraint = tag("IncreaseTag").apply {
            left constrainTo decreaseConstraint.right
            left.margin = 8.dp
            centerVertically()
        }
    }) {
        Box(modifier = Modifier.tag("DecreaseTag")) {
            Text("Decrease")
        }

        Box(modifier = Modifier.tag("IncreaseTag")) {
            Text("Increase")
        }
    }
}
