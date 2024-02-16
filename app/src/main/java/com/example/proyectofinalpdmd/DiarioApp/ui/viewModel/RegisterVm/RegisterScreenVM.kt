package com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.RegisterVm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterScreenVM: ViewModel() {

    var email by mutableStateOf("")
    var pasww by mutableStateOf("")

    fun changeEmail(email:String){
        this.email = email
    }
    fun changePasww(pasww:String){
        this.pasww = pasww
    }


}