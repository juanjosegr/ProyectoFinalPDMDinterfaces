package com.example.proyectofinalpdmd.navigation.Routes

/**
 * Clase sellada que representa las diferentes rutas (destinos) en la navegación de la aplicación.
 *
 * @property routes Nombre de la ruta como cadena.
 */
sealed class Routes(val routes: String) {
    object loginScreen : Routes("LoginScreen")
    object registerScreen : Routes("RegisterScreen")
    object diarioScreen : Routes("DiarioPrincipalScreen")
    object anadirScreen : Routes("AnadirNotaScreen")
    object diaryUpdateScren : Routes("DiaryUpdateScreen")

}