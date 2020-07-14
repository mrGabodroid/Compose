package com.example.myapplication

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.drawShadow
import androidx.ui.foundation.Image
import androidx.ui.foundation.clickable
import androidx.ui.foundation.drawBorder
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.CutCornerShape
import androidx.ui.graphics.Shape
import androidx.ui.layout.padding
import androidx.ui.layout.size
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.ripple
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

@Preview
@Composable
fun RoundCutCornerImage() {
    val (shape, setShape) = state<Shape> { CircleShape }

    val modifier = Modifier.size(256.dp)
        .padding(16.dp)
        .drawShadow(8.dp, shape)
        .drawBorder(6.dp, MaterialTheme.colors.primary, shape)
        .drawBorder(12.dp, MaterialTheme.colors.secondary, shape)
        .drawBorder(18.dp, MaterialTheme.colors.background, shape)
        .ripple(color = MaterialTheme.colors.onSurface)
        .clickable {
            setShape(
                if (shape == CircleShape)
                    CutCornerShape(topLeft = 32.dp, bottomRight = 32.dp)
                else
                    CircleShape
            )
        }

    Image(
        asset = imageResource(id = R.drawable.header),
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}