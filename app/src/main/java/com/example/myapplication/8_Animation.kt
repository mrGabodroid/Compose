package com.example.myapplication

import androidx.animation.FloatPropKey
import androidx.animation.transitionDefinition
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.animation.ColorPropKey
import androidx.ui.animation.Transition
import androidx.ui.animation.animate
import androidx.ui.core.Modifier
import androidx.ui.core.drawOpacity
import androidx.ui.foundation.Box
import androidx.ui.foundation.Canvas
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.wrapContentSize
import androidx.ui.material.*
import androidx.ui.material.ripple.RippleIndication
import androidx.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

@Preview
@Composable
fun Animation() {
    val animatingFab = state { false }

    MaterialTheme {
        Scaffold(
            topAppBar = {
                TopAppBar(title = {
                    Text(
                        text = "M",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().drawOpacity(
                            animate(if (animatingFab.value) 0f else 1f)
                        )
                    )
                }, elevation = 0.dp)
            },
            bodyContent = {
                val list = listOf("Tab 1", "Tab 2")
                val state = state { 0 }
                Column {
                    TabRow(
                        items = list,
                        selectedIndex = state.value
                    ) { index, currentTab ->
                        Tab(
                            selected = state.value == index,
                            onSelected = { state.value = index }
                        )
                        {
                            Text(
                                text = currentTab,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            },
            floatingActionButton = {
                Box(
                    modifier = Modifier.clickable(
                        onClick = {
                            animatingFab.value = true
                        },
                        indication = RippleIndication(
                            radius = 28.dp,
                            bounded = false
                        )
                    )
                        .wrapContentSize()
                ) {
                    Transition(
                        definition = sizeTransitionDefinition(
                            MaterialTheme.colors.secondary,
                            MaterialTheme.colors.primary
                        ), //  the transition reference
                        initState = FabState.Idle, // the state that we want the animation to be initialised with
                        toState = if (!animatingFab.value) { //  the state that we want the animation to be transitioned to when it is played out
                            FabState.Idle
                        } else FabState.Exploded,
                        onStateChangeFinished = {
                            // navigate to the second fragment
                        }
                    ) { state ->
                        Canvas(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            drawCircle(state[colorState], state[sizeState])
                            drawCircle(Color.Black, 25f, alpha = state[alphaState])
                        }
                    }
                }
            }
        )
    }
}

// FAB

// the size of our FAB and will be changed as our animation transitions
val sizeState = FloatPropKey()
val alphaState = FloatPropKey()
val colorState = ColorPropKey()

// the two different animated states
enum class FabState {
    Idle, Exploded
}

// the transition definition
private fun sizeTransitionDefinition(idleColor: Color, explodedColor: Color) =
    transitionDefinition {
        // we set defined values for these for each state,
        // this value will be animated between those two values as the transition progresses.
        state(FabState.Idle) {
            this[alphaState] = 1f
            this[sizeState] = 56f
            this[colorState] = idleColor
        }
        state(FabState.Exploded) {
            this[alphaState] = 0f
            this[sizeState] = 4000f
            this[colorState] = explodedColor
        }

        transition(fromState = FabState.Idle, toState = FabState.Exploded) {
            sizeState using keyframes {
                duration = 700 // run for 700 milliseconds
                56f at 0 // normal size at frame 0
                35f at 120 // shrink at frame 120
                4000f at 700 // between frame 120 and 700, animate from size 35f to 4000f
            }
        }
    }