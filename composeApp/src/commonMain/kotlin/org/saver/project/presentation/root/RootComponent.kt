package org.saver.project.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.saver.project.presentation.auth.AuthComponent
import org.saver.project.presentation.auth.DefaultAuthComponent
import org.saver.project.presentation.create_password.CreatePasswordComponent
import org.saver.project.presentation.create_password.DefaultCreatePasswordComponent
import org.saver.project.presentation.list_passwords.DefaultListPasswordsComponent
import org.saver.project.presentation.list_passwords.ListPasswordsComponent
import org.saver.project.presentation.master_password.DefaultMasterPasswordComponent
import org.saver.project.presentation.master_password.MasterPasswordComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun onBackClicked()

    sealed class Child {
        data class AuthChild(val component: AuthComponent) : Child()
        data class CreatePasswordChild(val component: CreatePasswordComponent) : Child()
        data class ListPasswordsChild(val component: ListPasswordsComponent) : Child()
        data class MasterPassword(val component: MasterPasswordComponent) : Child()
    }
}

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<ScreenConfig>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = ScreenConfig.serializer(),
        handleBackButton = true,
        initialStack = { listOf(ScreenConfig.Auth) },
        childFactory = ::child,
    )

    override fun onBackClicked() = navigation.pop()

    private fun child(
        config: ScreenConfig,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            is ScreenConfig.Auth -> {
                RootComponent.Child.AuthChild(
                    component = DefaultAuthComponent(componentContext = componentContext,
                        navigateToMasterPassword = {
                            navigation.replaceAll(ScreenConfig.MasterPassword)
                        },
                        navigateToListPasswords = {
                            navigation.replaceAll(ScreenConfig.ListPasswords)
                        })
                )
            }

            is ScreenConfig.CreatePassword -> {
                RootComponent.Child.CreatePasswordChild(
                    component = DefaultCreatePasswordComponent(
                        componentContext
                    )
                )
            }

            is ScreenConfig.MasterPassword -> {
                RootComponent.Child.MasterPassword(
                    component = DefaultMasterPasswordComponent(
                        componentContext = componentContext,
                        navigateToListPasswords = {
                            navigation.replaceAll(ScreenConfig.ListPasswords)
                        }
                    )
                )
            }

            is ScreenConfig.ListPasswords -> {
                RootComponent.Child.ListPasswordsChild(
                    component = DefaultListPasswordsComponent(
                        componentContext=componentContext,
                        navigateToCreatePassword = {
                            navigation.pushNew(ScreenConfig.CreatePassword)
                        }
                    )
                )
            }
        }
    }
}

@Serializable
sealed class ScreenConfig {
    @Serializable
    data object Auth : ScreenConfig()

    @Serializable
    data object CreatePassword : ScreenConfig()

    @Serializable
    data object ListPasswords : ScreenConfig()

    @Serializable
    data object MasterPassword : ScreenConfig()
}