package org.saver.project.presentation.create_password

data class CreatePasswordState(
    val title: String = "",
    val login: String = "",
    val password: String = "",
    val isCorrectInputs:Boolean=true,
)