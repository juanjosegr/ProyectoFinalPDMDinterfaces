package com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.AnadirNotaVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AnadirNotaVM: ViewModel() {

    var titutloNota by mutableStateOf("")
        private set
    var textoNota by mutableStateOf("")
        private set



}