package org.saver.project.core.settings

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.saver.project.core.platform.PlatformConfiguration

internal actual class SettingsFactoryImpl actual constructor(
    private val configuration: PlatformConfiguration
) : SettingsFactory {
    actual override fun make(name: String): Settings {
        val sharedPrefs =
            configuration.androidContext.getSharedPreferences(name, Context.MODE_PRIVATE)
        return SharedPreferencesSettings(delegate = sharedPrefs)
    }
}