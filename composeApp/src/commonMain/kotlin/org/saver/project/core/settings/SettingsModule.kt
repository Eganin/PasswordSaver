package org.saver.project.core.settings

import com.russhwolf.settings.Settings
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import org.saver.project.core.di.Inject.instance

val settingsModule = DI.Module(name = "settings_module") {
    bind<Settings>() with singleton {
        SettingsFactoryImpl(instance()).make(name = "core")
    }
}