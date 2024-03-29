package com.example.proyectofinalpdmd.DiarioApp.ui.view.AnadirNotaView

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
import com.example.proyectofinalpdmd.DiarioApp.data.model.NotaModel
import com.example.proyectofinalpdmd.DiarioApp.ui.view.GenericComponent.ShowAlert
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.AnadirNotaVM.AddNoteVM

/**
 * Componentes para la pantalla de añadir nota.
 *
 * @param addNoteVM ViewModel para agregar notas.
 */
@Composable
fun AddNoteComponents(
    addNoteVM: AddNoteVM
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Nueva nota", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para el título de la nota
        OutlinedTextField(
            value = addNoteVM.titleNote,
            onValueChange = { addNoteVM.changeTitleNote(it) },
            label = { Text("Título") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para el contenido de la nota
        OutlinedTextField(
            value = addNoteVM.textNote,
            onValueChange = { addNoteVM.changeTextNote(it) },
            label = { Text("Nota") },
            modifier = Modifier.height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar la nueva nota
        Row {
            Button(onClick = { addNoteVM.saveNewNote() }) {
                Text("Aceptar")
            }

            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .width(50.dp)
            )

            // Superficie con menú desplegable para seleccionar el color de la nota
            Surface(
                modifier = Modifier.clickable { expanded = true },
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colors.primary,
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = addNoteVM.selectedColorName, color = Color.White, fontSize = 16.sp)
                    Icon(Icons.Default.ArrowDropDown, "dropdown", tint = Color.White)
                }
            }

            // Menú desplegable de colores para la nota
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                NotaModel.noteColors.zip(addNoteVM.colorNames).forEach { (color, name) ->
                    DropdownMenuItem(onClick = {
                        addNoteVM.changeColorNote(color)
                        addNoteVM.changeSelectedColorName(name)
                        expanded = false
                    }) {
                        Text(text = name)
                    }
                }
            }
        }
    }
}

/**
 * Función para mostrar un cuadro de alerta.
 *
 * @param AddNoteVM ViewModel para agregar notas.
 * @param text Texto a mostrar en la alerta.
 * @param caso Caso de la alerta.
 */
@Composable
fun LlamadaShowAler(AddNoteVM: AddNoteVM, text: String, caso: String) {
    if (AddNoteVM.showAlert) {
        ShowAlert(
            caso,
            text,
            "Aceptar",
            onAcceptClick = { AddNoteVM.closedShowAlert() },
            OnDissmisClicl = { }
        )
    }
}
