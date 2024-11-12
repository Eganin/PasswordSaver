package org.saver.project.compose.master_password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.saver.project.presentation.master_password.MasterPasswordComponent
import org.saver.project.presentation.master_password.MasterPasswordState
import org.saver.project.presentation.master_password.PreviewMasterPasswordComponent

@Composable
fun MasterPasswordRoute(
    masterPasswordComponent: MasterPasswordComponent,
    modifier: Modifier = Modifier
) {
    val state = masterPasswordComponent.state.subscribeAsState().value
    Scaffold(modifier = modifier) {
        MasterPasswordScreen(
            masterPasswordComponent = masterPasswordComponent,
            state = state,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun MasterPasswordScreen(
    masterPasswordComponent: MasterPasswordComponent,
    state: MasterPasswordState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        if (state.masterPasswordIsCorrect) {
            Text(
                text = "Введите мастер пароль",
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                textAlign = TextAlign.Center
            )
        } else {
            Text(
                text = "Мастер пароль неверный",
                color = Color.Red,
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        TextField(
            value = state.masterPassword,
            onValueChange = masterPasswordComponent::changeMasterPassword,
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            onClick = masterPasswordComponent::compareMasterPassword
        ) {
            Text(
                text = "Далее",
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun MasterPasswordScreenPreview() {
    MasterPasswordScreen(
        masterPasswordComponent = PreviewMasterPasswordComponent(),
        state = MasterPasswordState(),
        modifier = Modifier.fillMaxSize()
    )
}