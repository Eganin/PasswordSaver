package org.saver.project.presentation.auth

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import org.saver.project.core.di.Inject
import org.saver.project.domain.repository.SavedPasswordsRepository

interface AuthComponent {
    val state: Value<AuthState>

    fun changeMasterPassword(masterPassword: String)

    fun saveMasterPassword()
}

class DefaultAuthComponent(
    componentContext: ComponentContext,
    private val navigateToMasterPassword: () -> Unit,
    private val navigateToListPasswords: () -> Unit
) : AuthComponent,
    ComponentContext by componentContext {
    override val state = MutableValue(AuthState())

    private val savedPasswordsRepository: SavedPasswordsRepository = Inject.instance()

    init {
        checkMasterPassword()
    }

    override fun changeMasterPassword(masterPassword: String) {
        state.value = state.value.copy(masterPassword = masterPassword)
    }

    override fun saveMasterPassword() {
        savedPasswordsRepository.saveMasterPassword(password = state.value.masterPassword)
        navigateToListPasswords()
    }

    private fun checkMasterPassword() {
        val isAuth = savedPasswordsRepository.isAuth()
        if (isAuth) {
            navigateToMasterPassword()
        } else {
            state.value = state.value.copy(isLoading = false)
        }
    }
}

class PreviewAuthComponent : AuthComponent {
    override val state: Value<AuthState> = MutableValue(AuthState())

    override fun changeMasterPassword(masterPassword: String) {}

    override fun saveMasterPassword() {}
}