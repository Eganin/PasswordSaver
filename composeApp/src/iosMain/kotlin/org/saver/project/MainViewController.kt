package org.saver.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.ApplicationLifecycle
import org.saver.project.compose.RootScreen
import org.saver.project.core.platform.PlatformConfiguration
import org.saver.project.core.platform.PlatformSDK
import org.saver.project.presentation.root.DefaultRootComponent
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController {
    PlatformSDK.init(
        platformConfiguration = PlatformConfiguration()
    )
    val rootComponent =
        DefaultRootComponent(componentContext = DefaultComponentContext(lifecycle = ApplicationLifecycle()))
    RootScreen(rootComponent = rootComponent, modifier = Modifier.fillMaxSize())
}