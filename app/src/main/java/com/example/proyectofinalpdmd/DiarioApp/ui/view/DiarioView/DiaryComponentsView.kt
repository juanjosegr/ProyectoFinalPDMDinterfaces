package com.example.proyectofinalpdmd.DiarioApp.ui.view.DiarioView


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectofinalpdmd.DiarioApp.ui.view.GenericComponent.CustomTextBox
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiarioVM.DiaryScreenVM
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiaryUpdateVM.UpdateNoteVM
import com.example.proyectofinalpdmd.arribadiario.Diario
import com.example.proyectofinalpdmd.arribadiario.Frame2
import com.example.proyectofinalpdmd.arribadiario.TopLevel
import com.example.proyectofinalpdmd.ui.theme.BlueOcean
import com.example.proyectofinalpdmd.ui.theme.LightGreen
import com.example.proyectofinalpdmd.ui.theme.RedOrange
import com.example.proyectofinalpdmd.ui.theme.RedPink
import com.example.proyectofinalpdmd.ui.theme.Violet


@Composable
fun ArribaDiarioNuevo(
    modifier: Modifier = Modifier,
    diaryScreenVM: DiaryScreenVM
) {
    TopLevel(modifier = modifier) {
        Diario()
        Frame2 {
            OutlinedTextField(
                value = diaryScreenVM.search,
                onValueChange = { diaryScreenVM.changeSearch(it) },
                modifier = Modifier.fillMaxSize(),
                label = { Text(text = "Buscar..." , color = Color.White)  },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "", tint = Color.White) },
                singleLine = true,
                shape = RoundedCornerShape(32.dp),
            )
        }
    }
}

@Composable
fun ColumnasSeparadas(
    navController: NavController,
    diaryScreenVM: DiaryScreenVM,
    updateNoteVM: UpdateNoteVM
) {

    LaunchedEffect(Unit) {
        diaryScreenVM.fetchNotes()
    }

    val notes by diaryScreenVM.notesData.collectAsState()
    Log.d("Compose,ColumnasSeparadas", "Number of notes: ${notes.size}")
    if (notes.isNotEmpty()) {
        val halfSize = (notes.size + 1) / 2
        val leftColumnNotes = notes.take(halfSize)
        val rightColumnNotes = notes.drop(halfSize)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Left side
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(8.dp)

            ) {
                Column {
                    for (note in rightColumnNotes) {
                        val colorIndexValue = note.noteColorIndex?.get("value-s-VKNKU") as? Long
                        val backgroundColor: Color = when (colorIndexValue) {
                            -92835718103040 -> RedOrange
                            -25378210132787200 -> LightGreen
                            -53606925635420160 -> BlueOcean
                            -3219709348544512 -> RedPink
                            -13911192913313792 -> Violet
                            else -> Color.White
                        }
                        CustomTextBox(
                            backgroundColor = backgroundColor,
                            title = note.title,
                            titleColor = Color.Black,
                            text = note.note,
                            textColor = Color.Black,
                            navController = navController,
                            updateNoteVM = updateNoteVM,
                            idDoc = note.idNote
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Right side
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(8.dp)

            ) {
                Column {
                    for (note in leftColumnNotes) {
                        val colorIndexValue = note.noteColorIndex?.get("value-s-VKNKU") as? Long
                        Log.d("ColorDebug", "Color Index Value: $colorIndexValue")
                        Log.d("ColorDebug", "Color Index Value: ${note.noteColorIndex}")

                        val backgroundColor: Color = when (colorIndexValue) {
                            -92835718103040 -> RedOrange
                            -25378210132787200 -> LightGreen
                            -53606925635420160 -> BlueOcean
                            -3219709348544512 -> RedPink
                            -13911192913313792 -> Violet
                            else -> Color.White
                        }

                        CustomTextBox(
                            backgroundColor = backgroundColor,
                            title = note.title,
                            titleColor = Color.Black,
                            text = note.note,
                            textColor = Color.Black,
                            navController = navController,
                            updateNoteVM = updateNoteVM,
                            idDoc = note.idNote
                        )
                    }
                }
            }
        }
    } else {
        Text("No hay notas disponibles.")
    }
}
