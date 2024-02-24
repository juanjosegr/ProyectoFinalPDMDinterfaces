package com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiarioVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DiaryScreenMain : ViewModel() {

    var search by mutableStateOf("")

    fun changeSearch(search: String) {
        this.search = search
    }
}