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

class DiaryScreenVM : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore

    private val _notesData = MutableStateFlow<List<NotaModel>>(emptyList())
    val notesData: StateFlow<List<NotaModel>> = _notesData

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
                        val myDocument = document.toObject(NotaModel::class.java).copy(idNote = document.id)
                        documents.add(myDocument)
                    }
                }
                _notesData.value = documents
                Log.d("Firebase", "Notes fetched successfully: ${documents.size} notes")

            }
    }

    var search by mutableStateOf("")
        private  set
    fun changeSearch(search: String) {
        this.search = search
    }
}