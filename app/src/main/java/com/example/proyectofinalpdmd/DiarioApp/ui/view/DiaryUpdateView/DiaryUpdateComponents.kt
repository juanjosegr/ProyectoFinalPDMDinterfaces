package com.example.proyectofinalpdmd.DiarioApp.ui.view.DiaryUpdateView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectofinalpdmd.DiarioApp.data.model.NotaModel
import com.example.proyectofinalpdmd.DiarioApp.ui.view.GenericComponent.ShowAlert
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiaryUpdateVM.UpdateNoteVM
import com.example.proyectofinalpdmd.navigation.Routes.Routes

/**
 * Composable que representa los componentes para actualizar una nota.
 *
 * @param updateNoteVM ViewModel para actualizar notas.
 */
@Composable
fun UpdateNoteComponent(
    updateNoteVM: UpdateNoteVM
) {
    // Estado para controlar la expansión del menú desplegable de colores
    var expanded by remember { mutableStateOf(false) }
    // Estado para almacenar el nombre del color seleccionado
    var selectedColorName by remember { mutableStateOf("Elegir color") }

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = updateNoteVM.titleNote,
            onValueChange = { updateNoteVM.changeTitleNote(it) },
            label = { Text("Título") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = updateNoteVM.textNote,
            onValueChange = { updateNoteVM.changeTextNote(it) },
            label = { Text("Nota") },
            modifier = Modifier.height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { updateNoteVM.updateNote(updateNoteVM.idDoc) }) {
                Text("Aceptar")
            }

            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .width(50.dp)
            )

            Surface(
                modifier = Modifier.clickable { expanded = true },
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colors.primary,
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Row(
                    modifier = Modifier.padding(9.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = selectedColorName, color = Color.White, fontSize = 16.sp)
                    Icon(Icons.Default.ArrowDropDown, "dropdown", tint = Color.White)
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                NotaModel.noteColors.zip(updateNoteVM.colorNames).forEach { (color, name) ->
                    DropdownMenuItem(onClick = {
                        updateNoteVM.changeColorNote(color)
                        selectedColorName = name
                        expanded = false
                    }) {
                        Text(text = name)
                    }
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(225.dp)
        ) {
            Button(onClick = { updateNoteVM.deleteNote(idDoc = updateNoteVM.idDoc) }) {
                Text("Borrar")
            }
        }
    }
}

/**
 * Función para mostrar un cuadro de alerta y navegar de vuelta a la pantalla principal del diario.
 *
 * @param navController Controlador de navegación para manejar las transiciones entre pantallas.
 * @param updateNoteVM ViewModel para actualizar notas.
 * @param text Texto a mostrar en la alerta.
 * @param caso Caso de la alerta.
 */
@Composable
fun LlamadaShowAler(
    navController: NavController,
    updateNoteVM: UpdateNoteVM,
    text: String,
    caso: String
) {
    if (updateNoteVM.showAlert) {
        ShowAlert(
            caso,
            text,
            "Aceptar",
            onAcceptClick = {
                // Cerrar la alerta, navegar a la pantalla principal del diario y restablecer el estado
                updateNoteVM.closedShowAlert()
                navController.navigate(Routes.diarioScreen.routes)
            },
            OnDissmisClicl = { }
        )
    }
}