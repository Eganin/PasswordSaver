package org.saver.project.compose.management_password

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.saver.project.presentation.management_password.ManagementPasswordComponent
import org.saver.project.presentation.management_password.ManagementPasswordState
import org.saver.project.presentation.management_password.PreviewManagementPasswordComponent

@Composable
fun ManagementPasswordRoute(
    managementPasswordComponent: ManagementPasswordComponent,
    modifier: Modifier = Modifier
) {
    val state = managementPasswordComponent.state.subscribeAsState().value
    Scaffold(modifier = modifier) {
        ManagementPasswordScreen(
            managementPasswordComponent = managementPasswordComponent,
            state = state,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun ManagementPasswordScreen(
    managementPasswordComponent: ManagementPasswordComponent,
    state: ManagementPasswordState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier.size(32.dp).padding(start = 16.dp)
                    .clickable { managementPasswordComponent.toBack() })
            Text(
                text = if (state.isCreateMode()) "Создать запись" else "Редактировать запись",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        TextFieldWithError(
            title = state.title,
            onValueChange = managementPasswordComponent::changeTitle,
            isError = !state.isCorrectTitle,
            placeholderText = "Введите заголовок",
            errorMessage = "Заголовок пустой",
            textFieldModifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp)
        )

        TextFieldWithError(
            title = state.login,
            onValueChange = managementPasswordComponent::changeLogin,
            isError = !state.isCorrectLogin,
            placeholderText = "Введите логин",
            errorMessage = "Логин пустой",
            textFieldModifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp)
        )

        TextFieldWithError(
            title = state.password,
            onValueChange = managementPasswordComponent::changePassword,
            isError = !state.isCorrectPassword,
            placeholderText = "Введите пароль",
            errorMessage = "Пароль пустой",
            textFieldModifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp)
        )

        if (state.isEditMode()){
            Spacer(modifier=Modifier.height(32.dp))

            Button(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                onClick = managementPasswordComponent::deleteSavedPassword
            ) {
                Text(
                    text = "Удалить запись",
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier=Modifier.height(32.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            onClick = managementPasswordComponent::submit
        ) {
            Text(
                text = if (state.isCreateMode()) "Сохранить" else "Изменить",
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
    textFieldModifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        TextField(
            value = title,
            onValueChange = onValueChange,
            modifier = textFieldModifier,
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
fun ManagementPasswordScreenPreview() {
    ManagementPasswordScreen(
        managementPasswordComponent = PreviewManagementPasswordComponent(),
        state = ManagementPasswordState(),
        modifier = Modifier.fillMaxSize()
    )
}