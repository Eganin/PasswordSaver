package org.saver.project.core.platform

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.singleton
import org.saver.project.core.coreModule
import org.saver.project.core.di.Inject

object PlatformSDK {
    fun init(platformConfiguration: PlatformConfiguration) {
        val platformModule = DI.Module(name = "platformModule") {
            bind<PlatformConfiguration>() with singleton {
                platformConfiguration
            }
        }

        Inject.createDependencies(tree = DI {
            importAll(
                platformModule,
                coreModule
            )
        }.direct)
    }
}