package org.saver.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.saver.project.core.platform.PlatformConfiguration
import org.saver.project.core.platform.PlatformSDK

fun main() = application {
    PlatformSDK.init(
        platformConfiguration = PlatformConfiguration()
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = "PasswordSaver",
    ) {
        App()
    }
}