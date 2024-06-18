package com.chord_notes_app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCleanTextFieldBody(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    size: Int = 24,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .padding(horizontal = 0.dp)
            .height(40.dp),

    ) {
        TextField(
            value = value,

            onValueChange = onValueChange,
            placeholder = {
                Text(
                    placeholder,
                    fontSize = size.sp,
                    modifier = Modifier.padding(0.dp),
                    style = TextStyle(
                        fontWeight = FontWeight(500),
                        color = MaterialTheme.colorScheme.outline
                    )
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent, // Fondo transparente
                unfocusedContainerColor = Color.Transparent, // Fondo desenfocado transparente
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent, // Indicador de foco transparente
                unfocusedIndicatorColor = Color.Transparent, // Indicador desenfocado transparente
                disabledIndicatorColor = Color.Transparent, // Indicador deshabilitado transparente
            ),
            textStyle = TextStyle(
                fontSize = size.sp,
                fontWeight = FontWeight(500),
                color = MaterialTheme.colorScheme.outline
            ), // Tamaño de fuente grande
            modifier = modifier
                .padding(horizontal = 0.dp),

            )
    }
}


@Composable
@Preview(showBackground = true)
fun CustomCleanTextFieldBodyPreview() {
    CustomCleanTextFieldBody(
        value = "Value",
        onValueChange = { },
        placeholder = "Placeholder",
        size = 12
    )
}