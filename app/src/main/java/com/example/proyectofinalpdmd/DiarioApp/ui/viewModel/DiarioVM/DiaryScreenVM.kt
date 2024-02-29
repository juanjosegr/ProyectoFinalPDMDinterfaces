package com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiarioVM

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.proyectofinalpdmd.DiarioApp.data.model.NotaModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel para la pantalla del diario que gestiona la recuperación de notas del usuario.
 */
class DiaryScreenVM : ViewModel() {

    // Instancias de Firebase
    private val auth: FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore

    // Estado de las notas para la interfaz de usuario
    private val _notesData = MutableStateFlow<List<NotaModel>>(emptyList())
    val notesData: StateFlow<List<NotaModel>> = _notesData

    /**
     * Recupera las notas del usuario desde Firestore y actualiza el estado de las notas.
     */
    fun fetchNotes() {
        val email = auth.currentUser?.email
        firestore.collection("Notes")
            .whereEqualTo("emailUser", email.toString())
            .addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                    Log.e("Firebase", "Error fetching notes: $error")
                    return@addSnapshotListener
                }
                val documents = mutableListOf<NotaModel>()
                if (querySnapshot != null) {
                    for (document in querySnapshot) {
                        val myDocument =
                            document.toObject(NotaModel::class.java).copy(idNote = document.id)
                        documents.add(myDocument)
                    }
                }

                // Filtra las notas según el término de búsqueda. Si la búsqueda no está vacía,
                // se devuelven solo las notas que contienen el término de búsqueda en el título o en el contenido.
                // De lo contrario, se devuelven todas las notas sin filtrar.
                val searchNote = if (search.isNotEmpty()) {
                    documents.filter { note ->
                        note.title.contains(search, ignoreCase = true) || note.note.contains(
                            search,
                            ignoreCase = true
                        )
                    }
                } else {
                    documents
                }
                _notesData.value = searchNote
                Log.d("Firebase", "Notes fetched successfully: ${searchNote.size} notes")
            }
    }

    // Propiedad de estado para la barra de búsqueda
    var search by mutableStateOf("")
        private set

    /**
     * Función para cambiar el término de búsqueda y actualizar las notas según la búsqueda.
     *
     * @param search Término de búsqueda.
     */
    fun changeSearch(search: String) {
        this.search = search
    }
}