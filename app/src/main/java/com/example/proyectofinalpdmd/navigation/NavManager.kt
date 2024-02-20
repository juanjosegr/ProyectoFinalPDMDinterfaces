package com.example.proyectofinalpdmd.navigation

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalpdmd.DiarioApp.ui.view.DiarioView.DiarioPrincipalScreen
import com.example.proyectofinalpdmd.DiarioApp.ui.view.LoginView.LoginScreen
import com.example.proyectofinalpdmd.DiarioApp.ui.view.RegisterView.RegisterScreen
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiarioVM.DiarioPrincipalScreenVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.LoginVm.LoginScreenVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.RegisterVm.RegisterScreenVM
import com.example.proyectofinalpdmd.navigation.Routes.Routes

@Composable
fun NavManager(loginScreenVM: LoginScreenVM ,registerScreenVM: RegisterScreenVM,diarioPrincipalScreenVM: DiarioPrincipalScreenVM) {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Routes.loginScreen.routes
    ){
        composable(Routes.loginScreen.routes){
            LoginScreen(navController,loginScreenVM)
        }
        composable(Routes.registerScreen.routes){
            RegisterScreen(navController, registerScreenVM)
        }
        composable(Routes.diarioScreen.routes){
            DiarioPrincipalScreen(navController, diarioPrincipalScreenVM)
        }
    }
}