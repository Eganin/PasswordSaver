package org.saver.project.core.settings

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import org.saver.project.core.platform.PlatformConfiguration
import java.util.prefs.Preferences

internal actual class SettingsFactoryImpl actual constructor(configuration: PlatformConfiguration) :
    SettingsFactory {
    actual override fun make(name: String): Settings {
        val delegate = Preferences.userRoot()
        return PreferencesSettings(delegate)
    }
}