package org.saver.project.data.db

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val dbModule = DI.Module(name = "db_module") {
    bind<SavedPasswordsDatabase>() with singleton {
        AppDatabaseConstructor.initialize()
    }
}