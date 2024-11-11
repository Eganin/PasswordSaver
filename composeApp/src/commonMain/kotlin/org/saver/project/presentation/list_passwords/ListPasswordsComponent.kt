package org.saver.project.presentation.list_passwords

import com.arkivanov.decompose.ComponentContext

interface ListPasswordsComponent {
}

class DefaultListPasswordsComponent(componentContext: ComponentContext): ListPasswordsComponent,ComponentContext by componentContext

class PreviewListPasswordsComponent: ListPasswordsComponent