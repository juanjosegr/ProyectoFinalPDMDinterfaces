package com.example.proyectofinalpdmd.DiarioApp.ui.view.RegisterView

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.RegisterVm.RegisterScreenVM
import com.example.proyectofinalpdmd.gruporegistro.BtnRgt
import com.example.proyectofinalpdmd.gruporegistro.Frame2
import com.example.proyectofinalpdmd.gruporegistro.Frame3
import com.example.proyectofinalpdmd.gruporegistro.Rectangle6
import com.example.proyectofinalpdmd.gruporegistro.Regristro
import com.example.proyectofinalpdmd.gruporegistro.Rgt
import com.example.proyectofinalpdmd.gruporegistro.TopLevel


@Composable
fun GrupoRegistroNuevo(
    modifier: Modifier = Modifier,
    onBtnRegister: () -> Unit = {},
    registerScreenVM : RegisterScreenVM

) {
    TopLevel(modifier = modifier) {
        Regristro()
        Frame2 {
            OutlinedTextField(
                value = registerScreenVM.email,
                onValueChange = { registerScreenVM.changeEmail(it) },
                modifier = Modifier.fillMaxSize(),
                label = { Text(text = "Email") },
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                shape = RoundedCornerShape(32.dp)
            )
        }
        Frame3 {
            OutlinedTextField(
            value = registerScreenVM.pasww,
            onValueChange = { registerScreenVM.changePasww(it) },
            modifier = Modifier.fillMaxSize(),
            label = { Text(text = "Contraseña") },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            shape = RoundedCornerShape(32.dp)
        )
        }
        BtnRgt(onBtnRegister = onBtnRegister) {
            Rectangle6(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.Center,
                    offset = DpOffset(
                        x = 0.0.dp,
                        y = 0.0.dp
                    )
                )
            )
            Rgt(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.Center,
                    offset = DpOffset(
                        x = -3.0247039794921875.dp,
                        y = -0.0000457763671875.dp
                    )
                )
            )
        }
    }
}

@Composable
fun ShowAlert(
    title: String,
    text: String,
    confirmText: String,
    onAcceptClick: () -> Unit,
    OnDissmisClicl: () -> Unit
) {

    val scroll = rememberScrollState(0)

    AlertDialog(onDismissRequest = { OnDissmisClicl() },
        title = { Text(text = title) },
        text = {
            Text(
                text = text,
                textAlign = TextAlign.Justify,
                modifier = Modifier.verticalScroll(scroll)
            )
        },
        confirmButton = {
            Button(onClick = { onAcceptClick() }) {
                Text(text = confirmText)
            }
        }
    )
}

@Composable
fun LlamadaShowAlert(registerScreen: RegisterScreenVM) {
    if (registerScreen.showAlert) {
        ShowAlert(
            "Error",
            "Usuario no creado correctamente.",
            "Acepart",
            onAcceptClick = { registerScreen.closedShowAlert() },
            OnDissmisClicl = { }
        )
    }
}