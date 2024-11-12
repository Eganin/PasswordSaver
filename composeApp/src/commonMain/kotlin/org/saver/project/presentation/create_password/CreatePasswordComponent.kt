package org.saver.project.presentation.create_password

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.saver.project.core.di.Inject
import org.saver.project.domain.repository.SavedPasswordsRepository

interface CreatePasswordComponent {
    val state: Value<CreatePasswordState>
    fun changeTitle(title: String)
    fun changeLogin(login: String)
    fun changePassword(password: String)
    fun submit()
    fun toBack()
}

class DefaultCreatePasswordComponent(
    componentContext: ComponentContext,
    private val navigateToBack: () -> Unit
) : CreatePasswordComponent, ComponentContext by componentContext {
    override val state = MutableValue(CreatePasswordState())

    private val savedPasswordsRepository: SavedPasswordsRepository = Inject.instance()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

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
        val isCorrectOptionalFields =
            state.value.login.isNotBlank() || state.value.title.isNotBlank()
        state.value = state.value.copy(isCorrectPassword = state.value.password.isNotBlank())
        if (!isCorrectOptionalFields) {
            state.value = state.value.copy(
                isCorrectLogin = state.value.login.isNotBlank(),
                isCorrectTitle = state.value.title.isNotBlank()
            )
        }
        if (isCorrectOptionalFields && state.value.isCorrectPassword) {
            scope.launch {
                savedPasswordsRepository.insertPasswordInfo(
                    title = state.value.title,
                    login = state.value.login,
                    password = state.value.password
                )
                uiScope.launch {
                    navigateToBack()
                }
            }
        }
    }

    override fun toBack() = navigateToBack()
}

class PreviewCreatePasswordComponent : CreatePasswordComponent {
    override val state = MutableValue(CreatePasswordState())
    override fun changeTitle(title: String) {}

    override fun changeLogin(login: String) {}

    override fun changePassword(password: String) {}
    override fun submit() {}
    override fun toBack() {}
}