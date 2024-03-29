package com.example.proyectofinalpdmd.DiarioApp.ui.view.AnadirNotaView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.AnadirNotaVM.AddNoteVM
import com.example.proyectofinalpdmd.arriba.Arriba

/**
 * Composable que representa la pantalla para agregar una nueva nota.
 *
 * @param addNoteVM ViewModel para agregar notas.
 */
@Composable
fun AddNoteScreen(addNoteVM: AddNoteVM) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
        ) {
            Arriba()
            AddNoteComponents(addNoteVM)
            LlamadaShowAler(addNoteVM, addNoteVM.textError, addNoteVM.casoErrorAcierto)
        }
    }
}