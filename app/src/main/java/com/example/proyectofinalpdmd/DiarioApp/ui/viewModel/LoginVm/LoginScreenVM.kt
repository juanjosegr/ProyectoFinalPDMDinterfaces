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
import kotlinx.coroutines.launch

class LoginScreenVM: ViewModel() {

    var email by mutableStateOf("")
        private set
    var pasww by mutableStateOf("")
        private set

    private val auth: FirebaseAuth = Firebase.auth
    var showAlert by mutableStateOf(false)
        private set

    var textError by mutableStateOf("")
        private set
    var casoErrorAcierto by mutableStateOf("")
        private set

    fun changeEmail(email:String){
        this.email = email
    }

    fun changePasww(pasww:String){
        this.pasww = pasww
    }


    fun login(onSuccess: () -> Unit){
        viewModelScope.launch {
            if (email.isBlank() || pasww.isBlank()) {
                Log.d("Error en firabase","Error con campos en blanco.")
                showAlert = true
                textError = "Campo email / contraseña vacío"
                casoErrorAcierto = "Error"
            }

            if (!isValidEmail(email)) {
                // Email no válido, mostrar error
                Log.d("Error en firabase","Error con email no valido.")
                showAlert = true
                textError = "email incorrecto"
                casoErrorAcierto = "Error"
            }
            try {
                auth.signInWithEmailAndPassword(email,pasww)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            onSuccess()
                        } else {
                            Log.d("Error en Firebase","Usuario y/o contraseña incorrectos.")
                            showAlert = true
                            textError = "Campo email / contraseña incorrecto"
                            casoErrorAcierto = "Error"
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
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }
}