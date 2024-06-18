package com.chord_notes_app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun InputDialog(
    showDialog: MutableState<Boolean>,
    value: MutableState<String>
) {

    Column(

    ) {

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    showDialog.value = false
                },
                title = {
                    Text(text = "Chord")
                },
                text = {
                    CustomCleanTextField(
                        modifier = Modifier.fillMaxWidth()

                        ,
                        placeholder = "Type the chord",
                        size = 15,
                        fontWeight =  FontWeight(400),
                        value = value.value.trim(), onValueChange ={
                            value.value = it.trim()
                        } )

                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog.value = false
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialog.value = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}