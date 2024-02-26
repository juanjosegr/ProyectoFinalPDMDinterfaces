package com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiaryUpdateVM

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.proyectofinalpdmd.DiarioApp.data.model.NotaModel

class DiaryUpdateVM : ViewModel() {
    var titleNote by mutableStateOf("")
        private set
    var textNote by mutableStateOf("")
        private set
    var noteColor by mutableStateOf(NotaModel.noteColors[0])
        private set
    var colorNames = listOf("RedOrange", "LightGreen", "Violet", "Blue", "RedPink")
        private set

    var showAlert by mutableStateOf(false)
        private set

    fun changeTitleNote(title: String) {
        this.titleNote = title
        Log.d("Titulo", title)
    }

    fun changeTextNote(text: String) {
        this.textNote = text
        Log.d("Texto", text)
    }

    fun changeColorNote(color: Color) {
        this.noteColor = color
        Log.d("Color", color.toString())
    }


    fun closedShowAlert() {
        showAlert = false
    }
    fun allDateObtains(
        title: String,
        text: String,
        backgroundColor: Color,
    ) {
        this.titleNote = title
        this.textNote = text
        this.noteColor = backgroundColor
    }
}