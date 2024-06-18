package com.chord_notes_app.ui.pages.notes.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun CustomText(
    text: String
)
{
    Text(text = text , style = MaterialTheme.typography.titleLarge.copy(
        fontSize = 20.sp,
        fontWeight = FontWeight(400),
        color = MaterialTheme.colorScheme.outline
    ))
}