package com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiarioVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DiarioPrincipalScreenVM : ViewModel() {

    var buscar by mutableStateOf("")

    fun changeBuscar(buscar: String) {
        this.buscar = buscar
    }
}