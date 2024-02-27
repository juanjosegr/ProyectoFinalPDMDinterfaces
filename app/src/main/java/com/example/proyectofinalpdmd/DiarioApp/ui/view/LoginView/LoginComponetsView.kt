package com.example.proyectofinalpdmd.DiarioApp.ui.view.LoginView

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.proyectofinalpdmd.DiarioApp.ui.view.GenericComponent.PasswordVisibleIcon
import com.example.proyectofinalpdmd.DiarioApp.ui.view.GenericComponent.ShowAlert
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.UserVM.LoginRegisterVM
import com.example.proyectofinalpdmd.grupologin.BtnLogin
import com.example.proyectofinalpdmd.grupologin.BtnRegistrer
import com.example.proyectofinalpdmd.grupologin.Conectar
import com.example.proyectofinalpdmd.grupologin.Conectar1
import com.example.proyectofinalpdmd.grupologin.Frame2
import com.example.proyectofinalpdmd.grupologin.Frame3
import com.example.proyectofinalpdmd.grupologin.Rectangle6
import com.example.proyectofinalpdmd.grupologin.Rectangle7
import com.example.proyectofinalpdmd.grupologin.Register
import com.example.proyectofinalpdmd.grupologin.TopLevel

@Composable
fun GrupoLoginNuevo(
    modifier: Modifier = Modifier,
    onBtnLogin: () -> Unit = {},
    onBtnRegister: () -> Unit = {},
    loginScreenVM: LoginRegisterVM,
    passwordVisible: MutableState<Boolean>
) {
    val visualTranformaction = if (passwordVisible.value)
        VisualTransformation.None
    else PasswordVisualTransformation()
    TopLevel(modifier = modifier) {
        Conectar()
        Frame2 {
            OutlinedTextField(
                value = loginScreenVM.email,
                onValueChange = { loginScreenVM.changeEmail(it) },
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
                value = loginScreenVM.password,
                onValueChange = { loginScreenVM.changePasww(it) },
                modifier = Modifier.fillMaxSize(),
                label = { Text(text = "Contraseña") },
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
                visualTransformation = visualTranformaction,
                trailingIcon = {
                    if (loginScreenVM.password.isNotBlank()) {
                        PasswordVisibleIcon(passwordVisible)
                    } else null
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                shape = RoundedCornerShape(32.dp)
            )
        }
        BtnLogin(onBtnLogin = onBtnLogin) {
            Rectangle6(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.Center,
                    offset = DpOffset(
                        x = 0.0.dp,
                        y = 0.0.dp
                    )
                )
            )
            Conectar1(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.Center,
                    offset = DpOffset(
                        x = -1.0.dp,
                        y = -0.000010013580322265625.dp
                    )
                )
            )
        }
        BtnRegistrer(onBtnRegister = onBtnRegister) {
            Rectangle7(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.Center,
                    offset = DpOffset(
                        x = 0.0.dp,
                        y = 0.0.dp
                    )
                )
            )
            Register(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.Center,
                    offset = DpOffset(
                        x = -1.0.dp,
                        y = 0.000020503997802734375.dp
                    )
                )
            )
        }
    }
}

@Composable
fun LlamadaShowAler(loginScreenVM: LoginRegisterVM, text: String, caso: String) {
    if (loginScreenVM.showAlert) {
        ShowAlert(
            caso,
            text,
            "Acepart",
            onAcceptClick = { loginScreenVM.closedShowAlert() },
            OnDissmisClicl = { }
        )
    }
}