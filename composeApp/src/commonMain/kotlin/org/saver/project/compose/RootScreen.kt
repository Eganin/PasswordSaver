package org.saver.project.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.saver.project.presentation.root.RootComponent

@Composable
fun RootScreen(rootComponent: RootComponent, modifier: Modifier = Modifier) {
    MaterialTheme {
        Text(text="TEst")
    }
}