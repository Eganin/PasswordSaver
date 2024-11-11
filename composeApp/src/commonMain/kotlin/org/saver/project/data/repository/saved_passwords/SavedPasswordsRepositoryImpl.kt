package org.saver.project.data.repository.saved_passwords

import org.saver.project.data.repository.saved_passwords.data_store.LocalSavedPasswordsDataStore
import org.saver.project.domain.model.SavedPassword
import org.saver.project.domain.repository.SavedPasswordsRepository

internal class SavedPasswordsRepositoryImpl(
    private val localSavedPasswordsDataStore: LocalSavedPasswordsDataStore,
) : SavedPasswordsRepository {
    override fun isAuth(): Boolean = localSavedPasswordsDataStore.getMasterPassword().isNotBlank()

    override fun compareMasterPassword(password: String): Boolean {
        return localSavedPasswordsDataStore.getMasterPassword() == password
    }

    override suspend fun savedPasswords(): List<SavedPassword> {
        return localSavedPasswordsDataStore.getSavedPasswords()
    }

    override suspend fun insertPasswordInfo(title: String, login: String, password: String) {
        val savedPassword = SavedPassword(
            title = title,
            login = login,
            password = password
        )
        localSavedPasswordsDataStore.insertSavedPasswords(savedPassword = savedPassword)
    }
}