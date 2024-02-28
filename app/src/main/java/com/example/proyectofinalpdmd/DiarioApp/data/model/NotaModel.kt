package com.example.proyectofinalpdmd.DiarioApp.data.model
import com.example.proyectofinalpdmd.ui.theme.*

data class NotaModel(
    val emailUser: String = "",
    val note: String = "",
    val noteColorIndex: HashMap<String, Any> = hashMapOf(),
    val title: String = "",
    val idNote: String = "",

    ){
    companion object{
        val noteColors = listOf(RedOrange,LightGreen, Violet, BlueOcean, RedPink)
    }
}