package com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.LoginVm

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

class LoginScreenVM: ViewModel() {

    var email by mutableStateOf("")
    var pasww by mutableStateOf("")

    private val auth: FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore
    var showAlert by mutableStateOf(false)
        private set

    fun changeEmail(email:String){
        this.email = email
    }

    fun changePasww(pasww:String){
        this.pasww = pasww
    }


    fun login(onSuccess: () -> Unit){
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email,pasww)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            onSuccess()
                        } else {
                            Log.d("Error en Firebase","Usuario y/o contraseña incorrectos.")
                            showAlert = true
                        }
                    }
            }
            catch (e: Exception){
                Log.d("Error en Jetpack", "Error: ${e.localizedMessage}")
            }
        }
    }

    fun closedShowAlert(){
        showAlert = false
    }

}