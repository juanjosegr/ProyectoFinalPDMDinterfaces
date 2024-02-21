package com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.AnadirNotaVM

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun AnadirNotasComponent() {
    var title by remember { mutableStateOf(TextFieldValue()) }
    var note by remember { mutableStateOf(TextFieldValue()) }
    var noteColor by remember { mutableStateOf(Color.Gray) } // Predeterminado, pero podrías permitir seleccionar

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Nueva nota", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Nota") },
            modifier = Modifier.height(200.dp) // Ajusta la altura según necesites
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // Aceptar
            Button(onClick = { /* Acción para guardar la nota */ }) {
                Text("Aceptar")
            }

            Spacer(modifier = Modifier.height(8.dp).width(50.dp))

            // Elegir color
            Button(onClick = { /* Acción para elegir color */ }) {
                Text("Elegir color")
            }
        }
    }
}