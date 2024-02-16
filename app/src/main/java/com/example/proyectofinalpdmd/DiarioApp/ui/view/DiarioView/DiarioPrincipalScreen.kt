package com.example.proyectofinalpdmd.DiarioApp.ui.view.DiarioView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectofinalpdmd.DiarioApp.ui.view.LoginView.GrupoLoginNuevo
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiarioVM.DiarioPrincipalScreenVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.LoginVm.LoginScreenVM
import com.example.proyectofinalpdmd.arribadiario.Diario
import com.example.proyectofinalpdmd.arribadiario.Frame2
import com.example.proyectofinalpdmd.arribadiario.TopLevel
import com.example.proyectofinalpdmd.navigation.Routes.Routes

@Composable
fun DiarioPrincipalScreen(
    navController: NavController,
    diarioPrincipalScreenVM: DiarioPrincipalScreenVM,
) {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
        ) {
            ArribaDiarioNuevo(
                modifier = Modifier.fillMaxSize(),
                diarioPrincipalScreenVM
            )

        }
    }
}

@Composable
fun ArribaDiarioNuevo(
    modifier: Modifier = Modifier,
    diarioPrincipalScreenVM: DiarioPrincipalScreenVM
) {
    TopLevel(modifier = modifier) {
        Diario()
        Frame2 {
            OutlinedTextField(
                value = diarioPrincipalScreenVM.buscar,
                onValueChange = { diarioPrincipalScreenVM.changeBuscar(it) },
                modifier = Modifier.fillMaxSize(),
                label = { Text(text = "Buscar...") },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                singleLine = true,
                shape = RoundedCornerShape(32.dp)
            )
        }
    }
}