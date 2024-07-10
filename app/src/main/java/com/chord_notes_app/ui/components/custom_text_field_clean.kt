package com.chord_notes_app.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCleanTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    size: Int = 24,
    fontWeight: FontWeight = FontWeight(500),
    lines: Int? = 5,
    callback: () -> Unit? = { },
    modifier: Modifier = Modifier
) {

    TextField(
        value = value,


        onValueChange = onValueChange,
        placeholder = {
            Text(placeholder,
            fontSize = size.sp,
            modifier = Modifier.padding(0.dp),
            style = TextStyle(fontWeight = fontWeight, color = MaterialTheme.colorScheme.outline)
        ) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent, // Fondo transparente
            unfocusedContainerColor = Color.Transparent, // Fondo desenfocado transparente
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent, // Indicador de foco transparente
            unfocusedIndicatorColor = Color.Transparent, // Indicador desenfocado transparente
            disabledIndicatorColor = Color.Transparent, // Indicador deshabilitado transparente
        ),
        textStyle = TextStyle(fontSize = size.sp, fontWeight = fontWeight, color = MaterialTheme.colorScheme.outline), // Tamaño de fuente grande
        modifier = modifier
            .padding(horizontal = 0.dp),
        singleLine = if(lines == 1){true}else{ false},
        maxLines = lines!!,

        keyboardActions = if(lines == 1) KeyboardActions(
            onDone = {
                println("SE LOGRO")
                callback()
            }

        ) else KeyboardActions {  },
        label = null


    )
}