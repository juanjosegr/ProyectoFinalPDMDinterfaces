package com.example.proyectofinalpdmd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalpdmd.DiarioApp.ui.view.AnadirNotaView.AnadirNotaScreen
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.AnadirNotaVM.AnadirNotaVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiarioVM.DiarioPrincipalScreenVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.LoginVm.LoginScreenVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.RegisterVm.RegisterScreenVM
import com.example.proyectofinalpdmd.navigation.NavManager
import com.example.proyectofinalpdmd.ui.theme.ProyectoFinalPDMDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val loginScreenVM: LoginScreenVM by viewModels()
        val registerScreenVM: RegisterScreenVM by viewModels()
        val diarioPrincipalScreenVM: DiarioPrincipalScreenVM by viewModels()
        val anadirNotaVM: AnadirNotaVM by viewModels()

        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinalPDMDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    /* NavHost(
                         navController = navController, startDestination = Routes.loginScreen.routes
                     ){
                         composable(Routes.loginScreen.routes){ LoginScreen(navController,loginScreenVM) }
                         composable(Routes.registerScreen.routes){ RegisterScreen(navController, registerScreenVM) }
                         composable(Routes.diarioScreen.routes){ DiarioPrincipalScreen(navController, diarioPrincipalScreenVM) }
                     }*/

                    NavManager(loginScreenVM,registerScreenVM,diarioPrincipalScreenVM,anadirNotaVM)
                    //AnadirNotaScreen(navController, anadirNotaVM)

                }
            }
        }
    }
}

