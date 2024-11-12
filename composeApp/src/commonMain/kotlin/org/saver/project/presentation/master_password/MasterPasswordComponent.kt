package org.saver.project.presentation.master_password

import com.arkivanov.decompose.ComponentContext

interface MasterPasswordComponent {
    fun changeMasterPassword(password:String)
    fun compareMasterPassword()
}

class DefaultMasterPasswordComponent(componentContext: ComponentContext): MasterPasswordComponent,ComponentContext by componentContext

class PreviewMasterPasswordComponent: MasterPasswordComponent