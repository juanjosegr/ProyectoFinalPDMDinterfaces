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

/**
 * ViewModel utilizado para la pantalla de actualización de notas.
 */
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
    var textError by mutableStateOf("")
        private set
    var casoErrorAcierto by mutableStateOf("")
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
     * Función para cambiar el contenido de la nota.
     *
     * @param text Nuevo contenido de la nota.
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
     * Función para obtener todos los datos de la nota antes de la actualización.
     *
     * @param title Título de la nota.
     * @param text Contenido de la nota.
     * @param backgroundColor Color de fondo de la nota.
     * @param idDoc Identificador único de la nota en Firestore.
     */
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

    /**
     * Función para actualizar la nota en Firestore.
     *
     * @param idDoc Identificador único de la nota en Firestore.
     */
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
                    // Utiliza la instancia de Firestore para actualizar la información de una nota por su id
                    firestore.collection("Notes").document(idDoc)
                        .update(editNote as Map<String, Any>)
                        .addOnSuccessListener {
                            Log.d(
                                "ACTUALIZAR OK",
                                "Se actualizó la nota correctamente en Firestore"
                            )
                            showAlert = true
                            textError = "Actualizado correctamente"
                            casoErrorAcierto = "Correcto"
                        }
                        .addOnFailureListener {
                            Log.d(
                                "ERROR AL ACTUALIZAR",
                                "ERROR al actualizar una nota en Firestore"
                            )
                            showAlert = true
                            textError = "Error al actualizar"
                            casoErrorAcierto = "Error"
                        }
                } else {
                    Log.d("Error editar nota", "ID no encontrada")
                }

            } catch (e: Exception) {
                Log.d("ERROR EDITAR NOTA", "Error al editar ${e.localizedMessage} ")
            }
        }
    }

    /**
     * Función para eliminar una nota en Firestore.
     *
     * @param idDoc Identificador único de la nota en Firestore.
     */
    fun deleteNote(idDoc: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //Utiliza la instancia de Firestore para eliminar una nota por su id
                firestore.collection("Notes").document(idDoc)
                    .delete()
                    .addOnSuccessListener {
                        showAlert = true
                        textError = "Borrado Correctamente"
                        casoErrorAcierto = "Borrado"
                        Log.d("ELIMINAR OK", "Se eliminó la nota correctamente en Firestore")
                    }
                    .addOnFailureListener {
                        Log.d("ERROR AL ELIMINAR", "ERROR al eliminar una nota en Firestore")
                    }
            } catch (e: Exception) {
                Log.d("ERROR BORRAR NOTA", "Error al eliminar ${e.localizedMessage} ")
            }
        }
    }

    /**
     * Función para cerrar la alerta de la interfaz de usuario.
     */
    fun closedShowAlert() {
        showAlert = false
    }
}