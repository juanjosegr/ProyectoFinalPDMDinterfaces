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
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.RegisterVm.RegisterScreenVM
import com.example.proyectofinalpdmd.arriba.Arriba
import com.example.proyectofinalpdmd.navigation.Routes.Routes

@Composable
fun RegisterScreen(navController: NavController, registerScreemVm: RegisterScreenVM) {
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
                registerScreenVM = registerScreemVm,
                onBtnRegister = { registerScreemVm.createUser{ navController.navigate(Routes.loginScreen.routes) }}
            )
            LlamadaShowAlert(registerScreemVm, registerScreemVm.textError, registerScreemVm.casoErrorAcierto)
        }
    }
}