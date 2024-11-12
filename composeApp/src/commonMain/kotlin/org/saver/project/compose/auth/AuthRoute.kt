package org.saver.project.compose.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.saver.project.presentation.auth.AuthComponent
import org.saver.project.presentation.auth.AuthState
import org.saver.project.presentation.auth.PreviewAuthComponent

@Composable
fun AuthRoute(authComponent: AuthComponent, modifier: Modifier = Modifier) {
    val state = authComponent.state.subscribeAsState().value
    Scaffold(modifier = modifier) {
        AuthScreen(authComponent = authComponent, state = state, modifier = Modifier.fillMaxSize())
    }
}

@Composable
private fun AuthScreen(
    authComponent: AuthComponent,
    state: AuthState,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if (!state.isLoading) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Введите мастер пароль",
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.weight(1f))

                TextField(
                    value = state.masterPassword,
                    onValueChange = authComponent::changeMasterPassword,
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp),
                    onClick = authComponent::saveMasterPassword
                ) {
                    Text(
                        text = "Сохранить",
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp).align(Alignment.Center),
                color = MaterialTheme.colors.secondary,
                backgroundColor = MaterialTheme.colors.surface,
            )
        }
    }
}

@Preview
@Composable
fun StartAuthPreview() {
    AuthScreen(
        authComponent = PreviewAuthComponent(),
        state = AuthState(),
        modifier = Modifier.fillMaxSize()
    )
}

@Preview
@Composable
fun NoAuthPreview() {
    AuthScreen(
        authComponent = PreviewAuthComponent(),
        state = AuthState(isLoading = false),
        modifier = Modifier.fillMaxSize()
    )
}