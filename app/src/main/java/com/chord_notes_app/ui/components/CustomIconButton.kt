package com.chord_notes_app.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CustomIconButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick,
        modifier= Modifier.size(30.dp).padding(1.dp)
        ,

    )
    {
        Icon(icon, contentDescription = null)
    }
}


@Preview(showBackground = true)
@Composable
fun CustomIconButtonPreview() {
    CustomIconButton(Icons.Filled.Close,{})
}