package com.example.proyectofinalpdmd.DiarioApp.data.model

/**
 * Modelo de datos para representar un usuario.
 *
 * @property userId Identificador único del usuario.
 * @property email Correo electrónico del usuario.
 * @property pasww Contraseña del usuario.
 */
data class UserModel(
    val userId: String,
    val email: String,
    val pasww: String,
)