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

/**
 * ViewModel para la pantalla de añadir nota.
 */
class AddNoteVM : ViewModel() {

    // Instancias de Firebase
    private val auth: FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore

    // Propiedades de estado para la información de la nota y la interfaz de usuario
    var titleNote by mutableStateOf("")
        private set
    var textNote by mutableStateOf("")
        private set
    var noteColorIndex by mutableStateOf(NotaModel.noteColors[0])
        private set
    var colorNames = listOf("RedOrange", "LightGreen", "Violet", "Blue", "RedPink")
        private set
    var showAlert by mutableStateOf(false)
        private set
    var textError by mutableStateOf("")
        private set
    var casoErrorAcierto by mutableStateOf("")
        private set

    var selectedColorName by mutableStateOf("Elegir color")
        private set

    /**
     * Función para cambiar el título de la nota.
     *
     * @param title Nuevo título de la nota.
     */
    fun changeTitleNote(title: String) {
        this.titleNote = title
        Log.d("Titulo", title)
    }

    /**
     * Función para cambiar el texto de la nota.
     *
     * @param text Nuevo texto de la nota.
     */
    fun changeTextNote(text: String) {
        this.textNote = text
        Log.d("Texto", text)
    }

    /**
     * Función para cambiar el color de la nota.
     *
     * @param color Nuevo color de la nota.
     */
    fun changeColorNote(color: Color) {
        this.noteColorIndex = color
        Log.d("Color", color.toString())
    }

    /**
     * Función para cambiar el nombre del color seleccionado.
     *
     * @param newName Nuevo nombre del color seleccionado.
     */
    fun changeSelectedColorName(newName: String) {
        selectedColorName = newName
    }

    /**
     * Función para guardar una nueva nota en Firestore.
     */
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
                        "noteColorIndex" to noteColorIndex,
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

    /**
     * Función para restablecer la información de la nota después de guardarla.
     */
    private fun resetInfoNote() {
        titleNote = ""
        textNote = ""
        noteColorIndex = NotaModel.noteColors[0]
        selectedColorName = "Elegir color"
    }

    /**
     * Función para cerrar la alerta mostrada en la interfaz de usuario.
     */
    fun closedShowAlert() {
        showAlert = false
    }
}