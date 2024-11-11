package org.saver.project.core.settings

import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.saver.project.core.platform.PlatformConfiguration

internal actual class SettingsFactoryImpl actual constructor(configuration: PlatformConfiguration) :
    SettingsFactory {
    @OptIn(ExperimentalSettingsImplementation::class)
    actual override fun make(name: String): Settings {
        return KeychainSettings(service = name)
    }
}