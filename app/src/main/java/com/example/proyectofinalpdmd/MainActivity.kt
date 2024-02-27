package com.example.proyectofinalpdmd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.AnadirNotaVM.AddNoteVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiarioVM.DiaryScreenVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiaryUpdateVM.UpdateNoteVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.UserVM.LoginRegisterVM
import com.example.proyectofinalpdmd.navigation.NavManager
import com.example.proyectofinalpdmd.ui.theme.ProyectoFinalPDMDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val loginScreenVM: LoginRegisterVM by viewModels()
        val diaryScreenVM: DiaryScreenVM by viewModels()
        val addNoteVM: AddNoteVM by viewModels()
        val updateNoteVM: UpdateNoteVM by viewModels()

        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinalPDMDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    NavManager(loginScreenVM,diaryScreenVM,addNoteVM,updateNoteVM)

                    //val navController = rememberNavController()
                    //DiarioPrincipalScreen(navController,diaryScreenMain)
                }
            }
        }
    }
}

