package com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.AnadirNotaVM

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalpdmd.DiarioApp.data.model.NotaModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteVM : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore

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
    var textError by mutableStateOf("")
        private set
    var casoErrorAcierto by mutableStateOf("")
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


    fun saveNewNote() {
        val email = auth.currentUser?.email

        viewModelScope.launch(Dispatchers.IO) {
            if (titleNote.isBlank() || textNote.isBlank()) {
                Log.d("Error en firabase", "Error con campos en blanco.")
                showAlert = true
                textError = "Campo título / texto vacío"
                casoErrorAcierto = "Error"
            } else {
                try {
                    val newNote = hashMapOf(
                        "title" to titleNote,
                        "note" to textNote,
                        "noteColor" to noteColor,
                        "emailUser" to email.toString()
                    )
                    firestore.collection("Notes")
                        .add(newNote)
                        .addOnSuccessListener {
                            Log.d("GUARDAR OK", "Se guardó la nota correctamente en Firestore")
                            resetInfoNote()
                            showAlert = true
                            textError = "Añadido correctamente"
                            casoErrorAcierto = "Correcto"
                        }
                        .addOnFailureListener {
                            Log.d("ERROR AL GUARDAR", "ERROR al guardar en Firestore")
                        }
                } catch (e: Exception) {
                    Log.d("Error de guardado de nota", "Error al guardar ${e.localizedMessage}")
                }
            }
        }
    }

    private fun resetInfoNote() {
        titleNote = ""
        textNote = ""
        noteColor = NotaModel.noteColors[0]
    }

    fun closedShowAlert() {
        showAlert = false
    }
}