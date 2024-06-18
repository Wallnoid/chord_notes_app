package com.chord_notes_app.ui.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomFloatingIconButton(
    onClick: () -> Unit,
    icon: ImageVector
){
    FloatingActionButton(
        onClick = onClick,
    ) {
        Icon(icon, null)
    }



}