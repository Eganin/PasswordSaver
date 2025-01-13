package org.saver.project.compose.list_passwords

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.saver.project.domain.model.SavedPassword
import org.saver.project.presentation.list_passwords.ListPasswordsComponent
import org.saver.project.presentation.list_passwords.PreviewListPasswordsComponent
import passwordsaver.composeapp.generated.resources.Res
import passwordsaver.composeapp.generated.resources.login
import passwordsaver.composeapp.generated.resources.no_answer
import passwordsaver.composeapp.generated.resources.password
import passwordsaver.composeapp.generated.resources.remove_master_password_question
import passwordsaver.composeapp.generated.resources.yes_answer

@Composable
fun ListPasswordsRoute(
    listPasswordsComponent: ListPasswordsComponent,
    modifier: Modifier = Modifier
) {
    val state = listPasswordsComponent.state.subscribeAsState().value
    Scaffold(
        floatingActionButton = {
            Column {
                CreatePasswordFAB(onClick = listPasswordsComponent::createPassword)
                Spacer(modifier = Modifier.height(16.dp))
                RemoveMasterPasswordFAB(onClick = {
                    listPasswordsComponent.changeVisibilityForDialog(visibility = true)
                })
            }
        },
        modifier = modifier
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
            items(state.savedPasswords) {
                SavedPasswordCell(
                    savedPassword = it,
                    modifier = Modifier.fillMaxWidth().clickable {
                        listPasswordsComponent.editPassword(it)
                    }.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
        }
    }

    if (state.showDialogForDeleteMasterPassword) {
        RemoveMasterPasswordDialog(listPasswordsComponent = listPasswordsComponent)
    }
}

@Composable
fun RemoveMasterPasswordDialog(listPasswordsComponent: ListPasswordsComponent) {
    AlertDialog(
        onDismissRequest = {
            listPasswordsComponent.changeVisibilityForDialog(visibility = false)
        },
        confirmButton = {
            Button(onClick = listPasswordsComponent::deleteMasterPassword) {
                Text(
                    text = stringResource(Res.string.yes_answer),
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 14.sp
                )
            }
        },
        dismissButton = {
            Button(onClick = {
                listPasswordsComponent.changeVisibilityForDialog(visibility = false)
            }) {
                Text(
                    text = stringResource(Res.string.no_answer),
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 14.sp
                )
            }
        },
        title = {
            Text(
                text = stringResource(Res.string.remove_master_password_question),
                modifier = Modifier.fillMaxWidth(),
                fontSize = 22.sp
            )
        }
    )
}

@Composable
private fun SavedPasswordCell(savedPassword: SavedPassword, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        if (savedPassword.title.isNotBlank()) {
            Text(
                text = savedPassword.title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        if (savedPassword.login.isNotBlank()) {
            Text(
                text = stringResource(Res.string.login, savedPassword.login),
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(
            text = stringResource(Res.string.password, savedPassword.password),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider(modifier = Modifier.height(1.dp).fillMaxWidth(), color = Color.Black)
    }
}

@Composable
private fun CreatePasswordFAB(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.size(64.dp)
    ) {
        Icon(Icons.Filled.Add, null)
    }
}

@Composable
fun RemoveMasterPasswordFAB(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.size(64.dp),
        backgroundColor = Color.Red
    ) {
        Icon(Icons.Filled.Close, null)
    }
}

@Preview
@Composable
fun ListPasswordsRoutePreview() {
    ListPasswordsRoute(
        listPasswordsComponent = PreviewListPasswordsComponent(),
        modifier = Modifier.fillMaxSize()
    )
}