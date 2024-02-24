package com.example.proyectofinalpdmd.DiarioApp.ui.view.AnadirNotaView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinalpdmd.DiarioApp.data.model.NotaModel
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.AnadirNotaVM.AddNoteVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.LoginVm.LoginScreenVM

@Composable
fun AddNoteComponents(
    addNoteVM: AddNoteVM
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedColorName by remember { mutableStateOf("Elegir color") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Nueva nota", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = addNoteVM.titleNote,
            onValueChange = { addNoteVM.changeTitleNote(it) },
            label = { Text("Título") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = addNoteVM.textNote,
            onValueChange = { addNoteVM.changeTextNote(it) },
            label = { Text("Nota") },
            modifier = Modifier.height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { addNoteVM.saveNewNote() }) {
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
                    modifier = Modifier.padding(8.dp),
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
                NotaModel.noteColors.zip(addNoteVM.colorNames).forEach { (color, name) ->
                    DropdownMenuItem(onClick = {
                        addNoteVM.changeColorNote(color)
                        selectedColorName = name
                        expanded = false
                    }) {
                        Text(text = name)
                    }
                }
            }
        }
    }
}

@Composable
fun ShowAlert(
    title: String,
    text: String,
    confirmText: String,
    onAcceptClick: () -> Unit,
    OnDissmisClicl: () -> Unit
) {

    val scroll = rememberScrollState(0)

    AlertDialog(onDismissRequest = { OnDissmisClicl() },
        title = { Text(text = title) },
        text = {
            Text(
                text = text,
                textAlign = TextAlign.Justify,
                modifier = Modifier.verticalScroll(scroll)
            )
        },
        confirmButton = {
            Button(onClick = { onAcceptClick() }) {
                Text(text = confirmText)
            }
        }
    )
}

@Composable
fun LlamadaShowAler(AddNoteVM: AddNoteVM, text: String, caso:String) {
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
