package org.saver.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.retainedComponent
import org.saver.project.compose.root.RootScreen
import org.saver.project.presentation.root.DefaultRootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootComponent = retainedComponent { DefaultRootComponent(componentContext = it) }
        setContent {
            RootScreen(rootComponent = rootComponent, modifier = Modifier.fillMaxSize())
        }
    }
}