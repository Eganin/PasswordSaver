package org.saver.project

import androidx.compose.ui.window.ComposeUIViewController
import org.saver.project.core.platform.PlatformConfiguration
import org.saver.project.core.platform.PlatformSDK
import platform.UIKit.UIViewController


fun MainViewController(): UIViewController = ComposeUIViewController {
    PlatformSDK.init(
        platformConfiguration = PlatformConfiguration()
    )
    App()
}