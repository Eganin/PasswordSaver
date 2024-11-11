package org.saver.project.presentation.create_password

import com.arkivanov.decompose.ComponentContext

interface CreatePasswordComponent {
}

class DefaultCreatePasswordComponent(componentContext: ComponentContext): CreatePasswordComponent, ComponentContext by componentContext

class PreviewCreatePasswordComponent: CreatePasswordComponent