package com.chord_notes_app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TwoFloatingActionButtons(
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.End // Alinea los botones a la derecha
    ) {
        SmallFloatingActionButton(onClick = {
                                            navController.navigate("createeditgroup")
        },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ) {
            // Primer botón
            Icon(Icons.Filled.GroupAdd, null)


        }
        Spacer(modifier = Modifier.height(10.dp)) // Espacio entre los botones
        CustomFloatingIconButton(
            icon = Icons.Filled.Add,
            onClick = { /* Acción del segundo botón */ }
        )
    }
}