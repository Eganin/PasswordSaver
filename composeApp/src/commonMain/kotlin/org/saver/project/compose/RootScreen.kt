package org.saver.project.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import org.saver.project.compose.auth.AuthRoute
import org.saver.project.presentation.root.RootComponent

@Composable
fun RootScreen(rootComponent: RootComponent, modifier: Modifier = Modifier) {
    MaterialTheme {
        Children(
            stack = rootComponent.childStack,
            modifier = modifier
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.AuthChild -> {
                    AuthRoute(authComponent = child.component, modifier = Modifier.fillMaxSize())
                }

                is RootComponent.Child.MasterPassword -> {}
                is RootComponent.Child.ListPasswordsChild -> {}
                is RootComponent.Child.CreatePasswordChild -> {}
            }
        }
    }
}