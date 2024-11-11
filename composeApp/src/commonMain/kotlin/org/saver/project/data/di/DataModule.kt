package org.saver.project.data.di

import org.kodein.di.DI
import org.saver.project.data.db.dbModule
import org.saver.project.data.repository.repositoryModule

val dataModule = DI.Module(name = "data_module") {
    importAll(
        repositoryModule,
        dbModule,
    )
}