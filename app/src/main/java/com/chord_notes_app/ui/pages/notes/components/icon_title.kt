package com.chord_notes_app.ui.pages.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoFixHigh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconTitle(
    icon: ImageVector,
    iconSize: Int = 20,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        // Icon
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(iconSize.dp),
            tint =  MaterialTheme.colorScheme.outline// Ajusta el tamaño del ícono para que sea similar al tamaño del texto
        )

        Spacer(modifier = Modifier.width(5.dp))

        // Title
        Text(
            title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(600),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.outline
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IconTitlePreview() {
    IconTitle(
        icon = Icons.Outlined.AutoFixHigh,
        title = "Icon Title"
    )
}
