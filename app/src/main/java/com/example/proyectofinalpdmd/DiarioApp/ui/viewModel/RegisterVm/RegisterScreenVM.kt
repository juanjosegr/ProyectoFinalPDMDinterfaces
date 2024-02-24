package com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.RegisterVm

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalpdmd.DiarioApp.data.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterScreenVM: ViewModel() {

    var email by mutableStateOf("")
        private set
    var pasww by mutableStateOf("")
        private set
    var userName by mutableStateOf("")
        private set

    var textError by mutableStateOf("")
        private set
    var casoErrorAcierto by mutableStateOf("")
        private set

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

    fun createUser(onSuccess:() -> Unit){
        viewModelScope.launch {
            try {
                if (email.isBlank() || pasww.isBlank()) {
                    // Campos en blanco, mostrar error
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
                auth.createUserWithEmailAndPassword(email,pasww)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            saveUser(userName,pasww)
                            onSuccess()
                        }else{
                            Log.d("Error en firabase","Error al crear el user")
                            showAlert = true
                            textError = "Error al crear el usuario"
                            casoErrorAcierto = "Error"
                        }
                    }
            }catch (e: Exception){
                Log.d("Error crear user","Error: ${e.localizedMessage}")
            }
        }
    }

    private fun saveUser(username: String, pasww: String){
        val uid = auth.currentUser?.uid
        val email = auth.currentUser?.email

        viewModelScope.launch(Dispatchers.IO) {
            val user = UserModel(
                email = email.toString(),
                userId = uid.toString(),
                pasww = pasww
            )
            // DCS - Añade el usuario a la colección "Users" en la base de datos Firestore
            firestore.collection("Users")
                .add(user)
                .addOnSuccessListener { Log.d("GUARDAR OK", "Se guardó el usuario correctamente en Firestore") }
                .addOnFailureListener { Log.d("ERROR AL GUARDAR", "ERROR al guardar en Firestore") }
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