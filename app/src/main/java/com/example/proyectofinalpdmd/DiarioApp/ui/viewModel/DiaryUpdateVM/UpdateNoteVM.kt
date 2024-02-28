package com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiaryUpdateVM

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalpdmd.DiarioApp.data.model.NotaModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateNoteVM : ViewModel() {

    private val firestore = Firebase.firestore
    var titleNote by mutableStateOf("")
        private set
    var textNote by mutableStateOf("")
        private set
    var noteColorIndex by mutableStateOf(NotaModel.noteColors[0])
        private set
    var colorNames = listOf("RedOrange", "LightGreen", "Violet", "Blue", "RedPink")
        private set
    var idDoc by mutableStateOf("")
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
        this.noteColorIndex = color
        Log.d("Color", color.toString())
    }

    fun allDateObtains(
        title: String,
        text: String,
        backgroundColor: Color,
        idDoc: String
    ) {
        this.titleNote = title
        this.textNote = text
        this.noteColorIndex = backgroundColor
        this.idDoc = idDoc
        Log.d("Obtección de datos", title + text + backgroundColor + idDoc)
    }

    fun updateNote(idDoc: String) {
        Log.d("UpdateNoteComponent", "ID de la nota antes de la actualización: $idDoc")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (idDoc.isNotEmpty()) {
                    val editNote = hashMapOf(
                        "title" to titleNote,
                        "note" to textNote,
                        "noteColorIndex" to noteColorIndex
                    )
                    // DCS - Utiliza la instancia de Firestore para actualizar la info de una nota por su id
                    firestore.collection("Notes").document(idDoc)
                        .update(editNote as Map<String, Any>)
                        .addOnSuccessListener {
                            Log.d("ACTUALIZAR OK", "Se actualizó la nota correctamente en Firestore")
                        }
                        .addOnFailureListener {
                            Log.d("ERROR AL ACTUALIZAR", "ERROR al actualizar una nota en Firestore")
                        }
                    // DCS - Si se guarda con éxito limpiamos las variables
                    resetInfoNote()
                    }else{
                        Log.d("Error editar nota","ID no encontrada")
                }

            } catch (e: Exception) {
                Log.d("ERROR EDITAR NOTA", "Error al editar ${e.localizedMessage} ")
            }
        }
    }

    private fun resetInfoNote() {
        titleNote = ""
        textNote = ""
    }

    fun closedShowAlert() {
        showAlert = false
    }
}