package org.saver.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.retainedComponent
import org.saver.project.compose.RootScreen
import org.saver.project.presentation.root.DefaultRootComponent
import org.saver.project.presentation.root.PreviewRootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootComponent = retainedComponent { DefaultRootComponent(componentContext = it) }
        setContent {
            RootScreen(rootComponent = rootComponent, modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    RootScreen(rootComponent = PreviewRootComponent(), modifier = Modifier.fillMaxSize())
}