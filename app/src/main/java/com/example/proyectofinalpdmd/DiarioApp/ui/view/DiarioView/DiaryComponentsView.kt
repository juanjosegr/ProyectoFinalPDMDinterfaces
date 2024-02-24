package com.example.proyectofinalpdmd.DiarioApp.ui.view.DiarioView


import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.proyectofinalpdmd.DiarioApp.ui.viewModel.DiarioVM.DiaryScreenMain
import com.example.proyectofinalpdmd.arribadiario.Diario
import com.example.proyectofinalpdmd.arribadiario.Frame2
import com.example.proyectofinalpdmd.arribadiario.TopLevel
import com.example.proyectofinalpdmd.framedetextos.FrameDeTextos


@Composable
fun ArribaDiarioNuevo(
    modifier: Modifier = Modifier,
    diaryScreenMain: DiaryScreenMain
) {
    TopLevel(modifier = modifier) {
        Diario()
        Frame2 {
            OutlinedTextField(
                value = diaryScreenMain.search,
                onValueChange = { diaryScreenMain.changeSearch(it) },
                modifier = Modifier.fillMaxSize(),
                label = { Text(text = "Buscar...") },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                singleLine = true,
                shape = RoundedCornerShape(32.dp)
            )
        }

    }
}

@Composable
fun ColumnasSeparadas() {
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
                .background(Color.Gray)
        ) {
            Column {
                Text(text = "Texto de que me dices")
                Text(text = "Texto de prueba2")
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Right side
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(8.dp)
                .background(Color.LightGray)
        ) {
            Column {
                Text(text = "Texto de prueba derecha")
                Text(text = "Texto de prueba derecha2")
            }
        }
    }
}
