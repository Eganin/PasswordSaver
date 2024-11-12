package org.saver.project.domain.repository

import org.saver.project.domain.model.SavedPassword

interface SavedPasswordsRepository {
    fun isAuth(): Boolean
    fun compareMasterPassword(password: String): Boolean
    fun saveMasterPassword(password:String)
    suspend fun savedPasswords(): List<SavedPassword>
    suspend fun insertPasswordInfo(title: String, login: String, password: String)
}