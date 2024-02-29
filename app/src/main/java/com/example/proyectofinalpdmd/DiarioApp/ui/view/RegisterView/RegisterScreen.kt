package com.example.proyectofinalpdmd.DiarioApp.ui.view.RegisterView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.UserVM.LoginRegisterVM
import com.example.proyectofinalpdmd.arriba.Arriba
import com.example.proyectofinalpdmd.navigation.Routes.Routes

/**
 * Composable que representa la pantalla de registro.
 *
 * @param navController Controlador de navegación para manejar las transiciones entre pantallas.
 * @param loginScreenVM ViewModel para la pantalla de inicio de sesión y registro.
 */
@Composable
fun RegisterScreen(
    navController: NavController,
    loginScreenVM: LoginRegisterVM
) {


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
        ) {
            Arriba()
            GrupoRegistroNuevo(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(0.5f),
                loginScreenVM = loginScreenVM,
                onBtnRegister = {
                    loginScreenVM.createUser { navController.navigate(Routes.loginScreen.routes) }
                },
                passwordVisible = loginScreenVM.passwordVisible
            )
            LlamadaShowAlert(
                loginScreenVM,
                loginScreenVM.textError,
                loginScreenVM.casoErrorAcierto
            )
        }
    }
}