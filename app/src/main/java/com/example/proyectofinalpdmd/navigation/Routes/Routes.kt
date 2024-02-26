package com.example.proyectofinalpdmd.navigation.Routes

sealed class Routes (val routes: String) {
    object loginScreen: Routes ("LoginScreen")
    object registerScreen: Routes ("RegisterScreen")
    object diarioScreen: Routes ("DiarioPrincipalScreen")
    object anadirScreen: Routes ("AnadirNotaScreen")
    object diaryUpdateScren: Routes ("DiaryUpdateScreen")

}