package com.chord_notes_app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
    fun CustomButton(onClick: () -> Unit, label: String, padding: Int) {

        return ElevatedButton(
            //elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
            modifier = Modifier

                .fillMaxWidth()
                .padding(horizontal = padding.dp)
                ,

            shape = RoundedCornerShape(10.dp),

            onClick = { onClick() }

        ) {
            Text(label, style =  MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary     )
            )
        }
    }
