package org.saver.project.domain.model

import kotlinx.serialization.Serializable
import org.saver.project.data.model.SavedPasswordDBModel

@Serializable
data class SavedPassword(
    val title: String,
    val login: String,
    val password: String,
)


fun SavedPassword.toDbModel(passwordKey: String) = SavedPasswordDBModel(
    title = title,
    login = login,
    passwordKey = passwordKey
)

fun SavedPasswordDBModel.toDomainModel(password: String) = SavedPassword(
    title = title,
    login = login,
    password = password,
)