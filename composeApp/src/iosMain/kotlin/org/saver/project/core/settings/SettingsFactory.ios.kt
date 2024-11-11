package org.saver.project.core.settings

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.saver.project.core.platform.PlatformConfiguration

internal actual class SettingsFactoryImpl actual constructor(configuration: PlatformConfiguration) :
    SettingsFactory {
    actual override fun make(name: String): Settings {
        return NSUserDefaultsSettings.Factory().create(name)
    }
}