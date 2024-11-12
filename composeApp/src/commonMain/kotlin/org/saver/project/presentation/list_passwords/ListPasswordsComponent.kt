package org.saver.project.presentation.list_passwords

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

interface ListPasswordsComponent {
    val state: Value<ListPasswordsState>
    fun createPassword()
}

class DefaultListPasswordsComponent(
    componentContext: ComponentContext,
    private val navigateToCreatePassword: () -> Unit
) : ListPasswordsComponent, ComponentContext by componentContext {
    init {
        loadSavedPasswords()
    }

    override val state = MutableValue(ListPasswordsState())
    private val savedPasswordsRepository: SavedPasswordsRepository = Inject.instance()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    override fun createPassword() = navigateToCreatePassword()

    private fun loadSavedPasswords() {
        scope.launch {
            val savedPasswords = savedPasswordsRepository.savedPasswords()
            state.value = state.value.copy(savedPasswords = savedPasswords)
        }
    }
}

class PreviewListPasswordsComponent : ListPasswordsComponent {
    override val state = MutableValue(ListPasswordsState())
    override fun createPassword() {}
}