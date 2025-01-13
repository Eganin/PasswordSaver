package org.saver.project.presentation.master_password

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import org.saver.project.core.di.Inject
import org.saver.project.domain.repository.SavedPasswordsRepository

interface MasterPasswordComponent {
    val state: Value<MasterPasswordState>
    fun changeMasterPassword(password: String)
    fun changePasswordVisibility(visibility: Boolean)
    fun compareMasterPassword()
}

class DefaultMasterPasswordComponent(
    componentContext: ComponentContext,
    private val navigateToListPasswords: () -> Unit
) : MasterPasswordComponent, ComponentContext by componentContext {
    override val state = MutableValue(MasterPasswordState())

    private val savedPasswordsRepository: SavedPasswordsRepository = Inject.instance()

    override fun changeMasterPassword(password: String) {
        state.value = state.value.copy(masterPassword = password)
    }

    override fun compareMasterPassword() {
        val masterPasswordIsCorrect =
            savedPasswordsRepository.compareMasterPassword(password = state.value.masterPassword)

        state.value = state.value.copy(masterPasswordIsCorrect = masterPasswordIsCorrect)
        if (masterPasswordIsCorrect) navigateToListPasswords()
    }

    override fun changePasswordVisibility(visibility: Boolean) {
        state.value = state.value.copy(passwordVisibility = visibility)
    }
}

class PreviewMasterPasswordComponent : MasterPasswordComponent {
    override val state = MutableValue(MasterPasswordState())
    override fun changeMasterPassword(password: String) {}

    override fun compareMasterPassword() {}

    override fun changePasswordVisibility(visibility: Boolean) {}
}