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

/**
 * Composable que representa un grupo de elementos para la pantalla de inicio de sesión y registro.
 *
 * @param modifier Modificador para personalizar la apariencia del grupo.
 * @param onBtnLogin Función lambda que se ejecuta al hacer clic en el botón de inicio de sesión.
 * @param onBtnRegister Función lambda que se ejecuta al hacer clic en el botón de registro.
 * @param loginScreenVM ViewModel para la pantalla de inicio de sesión y registro.
 * @param passwordVisible Estado mutable que indica si la contraseña es visible.
 */
@Composable
fun GrupoLoginNuevo(
    modifier: Modifier = Modifier,
    onBtnLogin: () -> Unit = {},
    onBtnRegister: () -> Unit = {},
    loginScreenVM: LoginRegisterVM,
    passwordVisible: MutableState<Boolean>
) {
    // Determinar la transformación visual según la visibilidad de la contraseña
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

/**
 * Función para mostrar un cuadro de alerta en la pantalla de inicio de sesión y registro.
 *
 * @param loginScreenVM ViewModel para la pantalla de inicio de sesión y registro.
 * @param text Texto a mostrar en la alerta.
 * @param caso Caso de la alerta.
 */
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