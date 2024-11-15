package org.saver.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.saver.project.compose.root.RootScreen
import org.saver.project.core.platform.PlatformConfiguration
import org.saver.project.core.platform.PlatformSDK
import org.saver.project.presentation.root.DefaultRootComponent

fun main() = application {
    val lifecycle = LifecycleRegistry()
    PlatformSDK.init(
        platformConfiguration = PlatformConfiguration()
    )
    val rootComponent = DefaultRootComponent(
        componentContext = DefaultComponentContext(lifecycle = lifecycle)
    )
    Window(
        onCloseRequest = {
            lifecycle.onPause()
            lifecycle.onStop()
            lifecycle.onDestroy()
            exitApplication()
        },
        title = "PasswordSaver",
        state = rememberWindowState()
    ) {
        RootScreen(rootComponent = rootComponent, modifier = Modifier.fillMaxSize())
    }

    lifecycle.onCreate()
    lifecycle.onStart()
    lifecycle.onResume()
}