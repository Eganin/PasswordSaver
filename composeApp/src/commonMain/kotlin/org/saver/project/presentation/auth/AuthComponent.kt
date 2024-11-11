package org.saver.project.presentation.auth

import com.arkivanov.decompose.ComponentContext

interface AuthComponent {
}

class DefaultAuthComponent(componentContext: ComponentContext) : AuthComponent,
    ComponentContext by componentContext

class PreviewAuthComponent : AuthComponent