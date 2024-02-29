package com.example.proyectofinalpdmd.DiarioApp.ui.view.DiaryUpdateView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiaryUpdateVM.UpdateNoteVM
import com.example.proyectofinalpdmd.arriba.Arriba

@Composable
fun DiaryUpdateScreen(navController: NavController, updateNoteVM: UpdateNoteVM) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
        ) {
            Arriba()
            UpdateNoteComponent(updateNoteVM = updateNoteVM)
            LlamadaShowAler(
                navController,
                updateNoteVM,
                updateNoteVM.textError,
                updateNoteVM.casoErrorAcierto,
            )

        }
    }
}