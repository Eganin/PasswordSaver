package org.saver.project.data.repository.saved_passwords.data_store

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import org.saver.project.data.db.SavedPasswordsDatabase
import org.saver.project.domain.model.SavedPassword
import org.saver.project.domain.model.toDbModel
import org.saver.project.domain.model.toDomainModel

interface LocalSavedPasswordsDataStore {
    fun saveMasterPassword(password: String): Boolean
    fun getMasterPassword(): String
    suspend fun getSavedPasswords(): List<SavedPassword>
    suspend fun insertSavedPasswords(savedPassword: SavedPassword)
    suspend fun deleteSavedPasswords(id:Long)
}

internal class LocalSavedPasswordsDataStoreImpl(
    private val database: SavedPasswordsDatabase,
    private val localStorage: Settings
) : LocalSavedPasswordsDataStore {
    override fun saveMasterPassword(password: String): Boolean {
        localStorage[MASTER_KEY] = password
        return localStorage.getStringOrNull(MASTER_KEY) != null
    }

    override fun getMasterPassword(): String {
        return localStorage.getString(key = MASTER_KEY, defaultValue = DEFAULT_VALUE)
    }

    override suspend fun getSavedPasswords(): List<SavedPassword> {
        return database.savedPasswordsDao().getAllSavedPasswords().map {
            val password = getPasswordFromKey(passwordKey = it.passwordKey)
            it.toDomainModel(password = password)
        }
    }

    override suspend fun insertSavedPasswords(savedPassword: SavedPassword) {
        val passwordKey = getKeyForPassword()
        localStorage[passwordKey] = savedPassword.password
        database.savedPasswordsDao()
            .insertSavedPassword(savedPassword = savedPassword.toDbModel(passwordKey = passwordKey))
    }

    override suspend fun deleteSavedPasswords(id: Long) {
        database.savedPasswordsDao().deleteSavedPasswordById(id = id)
    }

    private fun getPasswordFromKey(passwordKey: String): String {
        return localStorage.getString(key = passwordKey, defaultValue = DEFAULT_VALUE)
    }

    private fun getKeyForPassword(): String {
        val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        val key = (0..10).map {
            chars.random()
        }.joinToString("")
        return key
    }

    private companion object {
        const val MASTER_KEY = "MASTER_KEY"
        const val DEFAULT_VALUE = ""
    }
}