package org.saver.project.presentation.management_password

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.saver.project.core.di.Inject
import org.saver.project.domain.model.SavedPassword
import org.saver.project.domain.repository.SavedPasswordsRepository

interface ManagementPasswordComponent {
    val state: Value<ManagementPasswordState>
    fun changeTitle(title: String)
    fun changeLogin(login: String)
    fun changePassword(password: String)
    fun submit()
    fun toBack()
    fun deleteSavedPassword()
}

class DefaultManagementPasswordComponent(
    componentContext: ComponentContext,
    private val savedPassword: SavedPassword?,
    private val navigateToBack: () -> Unit
) : ManagementPasswordComponent, ComponentContext by componentContext {
    override val state = MutableValue(ManagementPasswordState())

    private val savedPasswordsRepository: SavedPasswordsRepository = Inject.instance()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        setMode()
    }

    override fun changeTitle(title: String) {
        state.value = state.value.copy(title = title, isCorrectTitle = true)
    }

    override fun changeLogin(login: String) {
        state.value = state.value.copy(login = login, isCorrectLogin = true)
    }

    override fun changePassword(password: String) {
        state.value = state.value.copy(password = password, isCorrectPassword = true)
    }

    override fun submit() {
        val isCorrectOptionalFields = checkCorrectInputs()
        if (isCorrectOptionalFields && state.value.isCorrectPassword) {
            scope.launch {
                savedPasswordsRepository.insertPasswordInfo(
                    title = state.value.title,
                    login = state.value.login,
                    password = state.value.password,
                    id = savedPassword?.id
                )
                uiScope.launch {
                    toBack()
                }
            }
        }
    }

    override fun toBack() = navigateToBack()

    override fun deleteSavedPassword() {
        savedPassword?.id?.let { id ->
            scope.launch {
                savedPasswordsRepository.deleteSavedPassword(id = id)
                uiScope.launch {
                    toBack()
                }
            }
        }
    }

    private fun setMode() {
        val mode =
            if (savedPassword == null) ManagementPasswordMode.CREATE else ManagementPasswordMode.EDIT
        state.value =
            state.value.copy(mode = mode)
        if (mode == ManagementPasswordMode.EDIT) {
            state.value = state.value.copy(
                title = savedPassword?.title ?: "",
                login = savedPassword?.login ?: "",
                password = savedPassword?.password ?: ""
            )
        }
    }

    private fun checkCorrectInputs(): Boolean {
        val isCorrectOptionalFields =
            state.value.login.isNotBlank() || state.value.title.isNotBlank()
        state.value = state.value.copy(isCorrectPassword = state.value.password.isNotBlank())
        if (!isCorrectOptionalFields) {
            state.value = state.value.copy(
                isCorrectLogin = state.value.login.isNotBlank(),
                isCorrectTitle = state.value.title.isNotBlank()
            )
        }
        return isCorrectOptionalFields
    }
}

class PreviewManagementPasswordComponent : ManagementPasswordComponent {
    override val state = MutableValue(ManagementPasswordState())
    override fun changeTitle(title: String) {}
    override fun changeLogin(login: String) {}
    override fun changePassword(password: String) {}
    override fun submit() {}
    override fun toBack() {}
    override fun deleteSavedPassword() {}
}