package com.example.proyectofinalpdmd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalpdmd.DiarioApp.ui.view.AnadirNotaView.AddNoteScreen
import com.example.proyectofinalpdmd.DiarioApp.ui.view.DiarioView.DiarioPrincipalScreen
import com.example.proyectofinalpdmd.DiarioApp.ui.view.DiaryUpdateView.DiaryUpdateScreen
import com.example.proyectofinalpdmd.DiarioApp.ui.view.LoginView.LoginScreen
import com.example.proyectofinalpdmd.DiarioApp.ui.view.RegisterView.RegisterScreen
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.AnadirNotaVM.AddNoteVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiarioVM.DiaryScreenVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiaryUpdateVM.UpdateNoteVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.UserVM.LoginRegisterVM
import com.example.proyectofinalpdmd.navigation.Routes.Routes

@Composable
fun NavManager(loginScreenVM: LoginRegisterVM, diaryScreenVM: DiaryScreenVM, addNoteVM: AddNoteVM, updateNoteVM: UpdateNoteVM) {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Routes.loginScreen.routes
    ){
        composable(Routes.loginScreen.routes){
            LoginScreen(navController,loginScreenVM)
        }
        composable(Routes.registerScreen.routes){
            RegisterScreen(navController, loginScreenVM)
        }
        composable(Routes.diarioScreen.routes){
            DiarioPrincipalScreen(navController, diaryScreenVM,updateNoteVM)
        }
        composable(Routes.anadirScreen.routes){
            AddNoteScreen(addNoteVM)
        }
        composable(Routes.diaryUpdateScren.routes){
            DiaryUpdateScreen(updateNoteVM)
        }
    }
}