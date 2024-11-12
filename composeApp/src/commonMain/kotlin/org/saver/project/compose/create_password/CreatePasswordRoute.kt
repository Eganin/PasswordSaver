package org.saver.project.compose.create_password

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.saver.project.presentation.create_password.CreatePasswordComponent
import org.saver.project.presentation.create_password.CreatePasswordState
import org.saver.project.presentation.create_password.PreviewCreatePasswordComponent

@Composable
fun CreatePasswordRoute(
    createPasswordComponent: CreatePasswordComponent,
    modifier: Modifier = Modifier
) {
    val state = createPasswordComponent.state.subscribeAsState().value
    Scaffold(modifier = modifier) {
        CreatePasswordScreen(
            createPasswordComponent = createPasswordComponent,
            state = state,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CreatePasswordScreen(
    createPasswordComponent: CreatePasswordComponent,
    state: CreatePasswordState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier.clickable { createPasswordComponent.toBack() })
            Text(
                text = "Создать запись",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        TextFieldWithError(
            title = state.title,
            onValueChange = createPasswordComponent::changeTitle,
            isError = !state.isCorrectTitle,
            placeholderText = "Введите заголовок",
            errorMessage = "Заголовок пустой"
        )

        TextFieldWithError(
            title = state.login,
            onValueChange = createPasswordComponent::changeLogin,
            isError = !state.isCorrectLogin,
            placeholderText = "Введите логин",
            errorMessage = "Логин пустой"
        )

        TextFieldWithError(
            title = state.password,
            onValueChange = createPasswordComponent::changePassword,
            isError = !state.isCorrectPassword,
            placeholderText = "Введите пароль",
            errorMessage = "Пароль пустой"
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            onClick = createPasswordComponent::submit
        ) {
            Text(
                text = "Сохранить",
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TextFieldWithError(
    title: String,
    isError: Boolean,
    errorMessage: String,
    placeholderText: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        TextField(
            value = title,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp),
            placeholder = {
                Text(text = placeholderText)
            },
            isError = isError
        )
        if (isError) {
            Text(
                text = errorMessage,
                modifier = Modifier.padding(start = 32.dp),
                color = Color.Red,
            )
        }
    }
}

@Preview
@Composable
fun CreatePasswordScreenPreview() {
    CreatePasswordScreen(
        createPasswordComponent = PreviewCreatePasswordComponent(),
        state = CreatePasswordState(),
        modifier = Modifier.fillMaxSize()
    )
}