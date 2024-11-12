package org.saver.project.presentation.list_passwords

import org.saver.project.domain.model.SavedPassword

data class ListPasswordsState(
    val savedPasswords:List<SavedPassword> = emptyList(),
)
