package org.saver.project.core

import org.kodein.di.DI
import org.saver.project.core.settings.settingsModule

val coreModule = DI.Module(name="core_module"){
    importAll(
        settingsModule
    )
}