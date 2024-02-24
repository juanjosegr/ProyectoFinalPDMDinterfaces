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
import com.example.proyectofinalpdmd.DiarioApp.ui.view.DiarioView.DiarioPrincipalScreen
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.AnadirNotaVM.AddNoteVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiarioVM.DiaryScreenMain
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.LoginVm.LoginScreenVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.RegisterVm.RegisterScreenVM
import com.example.proyectofinalpdmd.navigation.NavManager
import com.example.proyectofinalpdmd.ui.theme.ProyectoFinalPDMDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val loginScreenVM: LoginScreenVM by viewModels()
        val registerScreenVM: RegisterScreenVM by viewModels()
        val diaryScreenMain: DiaryScreenMain by viewModels()
        val addNoteVM: AddNoteVM by viewModels()

        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinalPDMDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavManager(loginScreenVM,registerScreenVM,diaryScreenMain,addNoteVM)
                    //AnadirNotaScreen(navController, anadirNotaVM)
                    //DiarioPrincipalScreen(navController,diaryScreenMain)
                }
            }
        }
    }
}

