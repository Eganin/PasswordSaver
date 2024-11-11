package org.saver.project

import android.app.Application
import org.saver.project.core.platform.PlatformConfiguration
import org.saver.project.core.platform.PlatformSDK

class SaverApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initPlatformSDK()
    }
}

private fun SaverApp.initPlatformSDK() {
    PlatformSDK.init(
        platformConfiguration = PlatformConfiguration(androidContext = this)
    )
}