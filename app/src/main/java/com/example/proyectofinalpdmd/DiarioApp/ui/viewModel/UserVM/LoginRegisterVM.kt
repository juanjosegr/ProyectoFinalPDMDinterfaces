package com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.UserVM

import android.util.Log
import androidx.compose.runtime.MutableState
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

/**
 * ViewModel utilizado para la pantalla de inicio de sesión y registro.
 */
class LoginRegisterVM : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    //passwordVisible Indica si la contraseña debe mostrarse en texto plano o no.
    var passwordVisible: MutableState<Boolean> = mutableStateOf(false)
        private set
    var showAlert by mutableStateOf(false)
        private set
    var userName by mutableStateOf("")
        private set

    var textError by mutableStateOf("")
        private set
    var casoErrorAcierto by mutableStateOf("")
        private set

    /**
     * Función para cambiar la dirección de correo electrónico del usuario.
     *
     * @param email Nueva dirección de correo electrónico.
     */
    fun changeEmail(email: String) {
        this.email = email
    }

    /**
     * Función para cambiar la contraseña del usuario.
     *
     * @param pasww Nueva contraseña.
     */
    fun changePasww(pasww: String) {
        this.password = pasww
    }

    /**
     * Función para realizar el inicio de sesión del usuario.
     *
     * @param onSuccess Lambda a ejecutar en caso de éxito en el inicio de sesión.
     */
    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            if (email.isBlank() || password.isBlank()) {
                Log.d("Error en firabase", "Error con campos en blanco.")
                showAlert = true
                textError = "Campo email / contraseña vacío"
                casoErrorAcierto = "Error"
            }
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onSuccess()
                        } else {
                            Log.d("Error en Firebase", "Usuario y/o contraseña incorrectos.")
                            showAlert = true
                            textError = "Campo email / contraseña incorrecto"
                            casoErrorAcierto = "Error"
                        }
                    }
            } catch (e: Exception) {
                Log.d("Error en Jetpack", "Error: ${e.localizedMessage}")
            }
        }
    }

    /**
     * Función para crear un nuevo usuario.
     *
     * @param onSuccess Lambda a ejecutar en caso de éxito al crear el usuario.
     */
    fun createUser(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                if (email.isBlank()) {
                    // Campos en blanco, mostrar error
                    Log.d("Error en firabase", "Error con campos en blanco.")
                    showAlert = true
                    textError = "Campo email vacío"
                    casoErrorAcierto = "Error"
                } else if (!isValidEmail(email)) {
                    // Email no válido, mostrar error
                    Log.d("Error en firabase", "Error con email no valido.")
                    showAlert = true
                    textError = "email incorrecto"
                    casoErrorAcierto = "Error"
                } else {
                    val passwordValidationResult = validatePassword(password)
                    if (!passwordValidationResult.first) {
                        // La contraseña no cumple con los requisitos, mostrar error
                        showAlert = true
                        textError = passwordValidationResult.second
                        casoErrorAcierto = "Error"
                    } else {
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    saveUser(userName, password)
                                    onSuccess()
                                    resetFields()
                                } else {
                                    Log.d("Error en firabase", "Error al crear el user")
                                    showAlert = true
                                    textError = "Error al crear el usuario"
                                    casoErrorAcierto = "Error"
                                }
                            }
                    }
                }

            } catch (e: Exception) {
                Log.d("Error crear user", "Error: ${e.localizedMessage}")
            }
        }
    }

    /**
     * Función para guardar la información del usuario en Firestore.
     *
     * @param username Nombre de usuario.
     * @param pasww Contraseña del usuario.
     */
    private fun saveUser(username: String, pasww: String) {
        val uid = auth.currentUser?.uid
        val email = auth.currentUser?.email

        viewModelScope.launch(Dispatchers.IO) {
            val user = UserModel(
                email = email.toString(),
                userId = uid.toString(),
                pasww = pasww
            )
            //Añade el usuario a la colección "Users" en la base de datos Firestore
            firestore.collection("Users")
                .add(user)
                .addOnSuccessListener {
                    Log.d(
                        "GUARDAR OK",
                        "Se guardó el usuario correctamente en Firestore"
                    )
                }
                .addOnFailureListener { Log.d("ERROR AL GUARDAR", "ERROR al guardar en Firestore") }
        }
    }

    /**
     * Función para validar si la dirección de correo electrónico es válida.
     *
     * @param email Dirección de correo electrónico a validar.
     * @return `true` si es válida, `false` si no lo es.
     */
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    /**
     * Función para validar la fortaleza de la contraseña.
     *
     * @param password Contraseña a validar.
     * @return Par de valores: `true` si la contraseña es válida, `false` y un mensaje de error si no lo es.
     */
    private fun validatePassword(password: String): Pair<Boolean, String> {
        // Verificar longitud mínima de la contraseña
        if (password.length < 6) {
            return Pair(false, mapErrorToString(InvalidError.LOW_CHARACTERS))
        }

        // Verificar presencia de minúsculas
        if (!password.any { it.isLowerCase() }) {
            return Pair(false, mapErrorToString(InvalidError.NO_LOWERCASE))
        }

        // Verificar presencia de mayúsculas
        if (!password.any { it.isUpperCase() }) {
            return Pair(false, mapErrorToString(InvalidError.NO_UPPERCASE))
        }

        // Verificar presencia de dígitos
        if (!password.any { it.isDigit() }) {
            return Pair(false, mapErrorToString(InvalidError.NO_DIGITS))
        }

        // Verificar presencia de caracteres especiales
        val specialCharacters = "~`!@#$%^&*()-_+=<>?/,.|;:\"'{}[]\\"
        if (!password.any { specialCharacters.contains(it) }) {
            return Pair(false, mapErrorToString(InvalidError.NO_SPECIAL_CHARACTERS))
        }

        // La contraseña pasa todas las validaciones
        return Pair(true, "")
    }


    /**
     * Función para mapear un tipo de error a un mensaje descriptivo.
     *
     * @param error Tipo de error.
     * @return Mensaje descriptivo del error.
     */
    private fun mapErrorToString(error: InvalidError): String {
        return when (error) {
            InvalidError.LOW_CHARACTERS -> "No hay caracteres suficientes"
            InvalidError.NO_LOWERCASE -> "No hay minuscula"
            InvalidError.NO_UPPERCASE -> "No hay mayuscula"
            InvalidError.NO_DIGITS -> "No hay numeros"
            InvalidError.NO_SPECIAL_CHARACTERS -> "No hay caracteres especiales"
        }
    }

    /**
     * Función para cerrar la alerta de la interfaz de usuario.
     */
    fun closedShowAlert() {
        showAlert = false
    }

    /**
     * Función para restablecer los campos a sus valores por defecto.
     */
    fun resetFields() {
        email = ""
        password = ""
        userName = ""
    }
}

/**
 * Enumeración que representa los posibles errores de validación de contraseña.
 */
enum class InvalidError {
    LOW_CHARACTERS,
    NO_LOWERCASE,
    NO_UPPERCASE,
    NO_DIGITS,
    NO_SPECIAL_CHARACTERS,
}