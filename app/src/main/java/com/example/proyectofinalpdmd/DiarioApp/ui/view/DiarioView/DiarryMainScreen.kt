package com.example.proyectofinalpdmd.DiarioApp.ui.view.DiarioView

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiarioVM.DiaryScreenVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiaryUpdateVM.UpdateNoteVM
import com.example.proyectofinalpdmd.abajo.Abajo
import com.example.proyectofinalpdmd.navigation.Routes.Routes

/**
 * Composable que representa la pantalla principal del diario.
 *
 * @param navController Controlador de navegación para manejar las transiciones entre pantallas.
 * @param diaryScreenVM ViewModel para la pantalla del diario.
 * @param updateNoteVM ViewModel para actualizar notas.
 */
@Composable
fun DiarioPrincipalScreen(
    navController: NavController,
    diaryScreenVM: DiaryScreenVM,
    updateNoteVM: UpdateNoteVM
) {
    // Log para seguimiento en el registro de eventos
    Log.d("Compose", "DiarioPrincipalScreen")
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .verticalScroll(
                    ScrollState(100000),
                    enabled = true,
                    reverseScrolling = true
                )
        ) {
            Box {
                ArribaDiarioNuevo(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .align(Alignment.TopStart),
                    diaryScreenVM
                )
            }
            ColumnasSeparadas(navController, diaryScreenVM, updateNoteVM)
        }
        Box(
            modifier = Modifier
                .size(40.dp, 36.dp)
                .align(Alignment.BottomEnd)
        ) {
            Abajo(
                onAanadirBtn = { navController.navigate(Routes.anadirScreen.routes) }
            )
        }
    }
}
