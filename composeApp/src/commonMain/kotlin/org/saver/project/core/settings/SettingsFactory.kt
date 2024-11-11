package org.saver.project.core.settings

import org.saver.project.core.platform.PlatformConfiguration
import com.russhwolf.settings.Settings

interface SettingsFactory {
    fun make(name: String): Settings
}

internal expect class SettingsFactoryImpl(
    configuration: PlatformConfiguration
): SettingsFactory {
    override fun make(name: String): Settings
}