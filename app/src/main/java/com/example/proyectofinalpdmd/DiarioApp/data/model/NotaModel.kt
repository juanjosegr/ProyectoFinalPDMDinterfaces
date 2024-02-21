package com.example.proyectofinalpdmd.DiarioApp.data.model
import com.example.proyectofinalpdmd.ui.theme.*

data class NotaModel(
    val emailUsr: String,
    val titleNote: String,
    val textNote: String,
    val idNote: String,
    val color: Int
){
    companion object{
        val noteColors = listOf(RedOrange,LightGreen, Violet, Blue, RedPink)
    }
}