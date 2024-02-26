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
import com.example.proyectofinalpdmd.DiarioApp.data.model.NotaModel
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiaryUpdateVM.DiaryUpdateVM

@Composable
fun UpdateNoteComponent(
    diaryUpdateVM: DiaryUpdateVM
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedColorName by remember { mutableStateOf("Elegir color") }

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = diaryUpdateVM.titleNote,
            onValueChange = { diaryUpdateVM.changeTitleNote(it) },
            label = { Text("Título") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = diaryUpdateVM.textNote,
            onValueChange = { diaryUpdateVM.changeTextNote(it) },
            label = { Text("Nota") },
            modifier = Modifier.height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { }) {
                Text("Aceptar")
            }

            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .width(50.dp)
            )

            Surface(
                modifier = Modifier.clickable { expanded = true},
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
                NotaModel.noteColors.zip(diaryUpdateVM.colorNames).forEach { (color, name) ->
                    DropdownMenuItem(onClick = {
                        diaryUpdateVM.changeColorNote(color)
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