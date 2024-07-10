package com.chord_notes_app.ui.pages.notes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chord_notes_app.ui.components.InputDialog

@Composable
fun Chord(
    chord: MutableState<String>,
    callback: () -> Unit
){

    val colorBackground = if (chord.value != "   ") MaterialTheme.colorScheme.primaryContainer else Color.Transparent
    val colorBorder = if (chord.value != "   ") MaterialTheme.colorScheme.outline else Color.Transparent

    val show = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier

            .clickable {
                show.value = true
                callback()

            }

        ,
        shape = RoundedCornerShape(3.dp),
        color = colorBackground,
        shadowElevation = if (chord.value != "   ") 2.dp else 0.dp,

    ) {

        InputDialog(
            value = chord,
            showDialog = show,
            callback = callback
        )


        Text(text = chord.value, style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight(600),
        ), color = MaterialTheme.colorScheme.onPrimaryContainer,

                modifier = Modifier.padding(horizontal = 7.dp, vertical = 0.dp),
            )

    }
}


@Preview(showBackground = true)
@Composable
fun ChordPreview() {

//    val chord = remember { mutableStateOf("A") }
//
//    Chord(
//        chord = chord
//    )
//

}