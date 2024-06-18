package com.chord_notes_app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomButtonWithIcon(
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .width(55.dp)
    ) {
        ElevatedButton(
            onClick = { },
            //elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
            modifier = Modifier

                .height(55.dp),

            shape = RoundedCornerShape(10.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer

            )

        ) {
            Icon(
                imageVector = icon, contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,

            )
        }
    }
}