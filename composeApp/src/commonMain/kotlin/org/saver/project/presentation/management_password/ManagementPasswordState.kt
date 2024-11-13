package org.saver.project.presentation.management_password

data class ManagementPasswordState(
    val title: String = "",
    val login: String = "",
    val password: String = "",
    val isCorrectLogin: Boolean = true,
    val isCorrectTitle: Boolean = true,
    val isCorrectPassword: Boolean = true,
    val mode: ManagementPasswordMode = ManagementPasswordMode.CREATE,
    val passwordVisibility: Boolean = false,
) {
    fun isEditMode() = mode == ManagementPasswordMode.EDIT
    fun isCreateMode() = mode == ManagementPasswordMode.CREATE
}

enum class ManagementPasswordMode {
    CREATE,
    EDIT
}