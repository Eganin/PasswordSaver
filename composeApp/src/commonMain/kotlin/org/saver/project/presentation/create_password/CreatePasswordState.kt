package org.saver.project.presentation.create_password

data class CreatePasswordState(
    val title: String = "",
    val login: String = "",
    val password: String = "",
    val isCorrectLogin: Boolean = true,
    val isCorrectTitle: Boolean = true,
    val isCorrectPassword: Boolean = true,
)