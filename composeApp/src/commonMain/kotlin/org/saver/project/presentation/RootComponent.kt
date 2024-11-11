package org.saver.project.presentation

import com.arkivanov.decompose.ComponentContext

interface RootComponent{}

class DefaultRootComponent(
    componentContext: ComponentContext
):RootComponent,ComponentContext by componentContext {
}

class PreviewRootComponent:RootComponent