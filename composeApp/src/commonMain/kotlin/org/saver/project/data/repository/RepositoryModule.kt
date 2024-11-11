package org.saver.project.data.repository

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.saver.project.data.repository.saved_passwords.SavedPasswordsRepositoryImpl
import org.saver.project.data.repository.saved_passwords.data_store.LocalSavedPasswordsDataStore
import org.saver.project.data.repository.saved_passwords.data_store.LocalSavedPasswordsDataStoreImpl
import org.saver.project.domain.repository.SavedPasswordsRepository

val repositoryModule = DI.Module(name = "repository_module") {
    bind<LocalSavedPasswordsDataStore>() with singleton {
        LocalSavedPasswordsDataStoreImpl(instance(), instance())
    }

    bind<SavedPasswordsRepository>() with singleton {
        SavedPasswordsRepositoryImpl(instance())
    }
}