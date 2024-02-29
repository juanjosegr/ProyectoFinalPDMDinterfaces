package com.example.proyectofinalpdmd.DiarioApp.data.model

import com.example.proyectofinalpdmd.ui.theme.BlueOcean
import com.example.proyectofinalpdmd.ui.theme.LightGreen
import com.example.proyectofinalpdmd.ui.theme.RedOrange
import com.example.proyectofinalpdmd.ui.theme.RedPink
import com.example.proyectofinalpdmd.ui.theme.Violet

/**
 * Modelo de datos para representar una nota.
 *
 * @property emailUser Correo electrónico del usuario asociado a la nota.
 * @property note Contenido de la nota.
 * @property noteColorIndex Índice de colores de la nota representado como un [HashMap].
 * @property title Título de la nota.
 * @property idNote Identificador único de la nota.
 */
data class NotaModel(
    val emailUser: String = "",
    val note: String = "",
    val noteColorIndex: HashMap<String, Any> = hashMapOf(),
    val title: String = "",
    val idNote: String = "",

    ) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BlueOcean, RedPink)
    }
}