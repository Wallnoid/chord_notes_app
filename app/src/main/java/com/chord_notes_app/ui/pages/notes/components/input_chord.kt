package com.chord_notes_app.ui.pages.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chord_notes_app.ui.components.CustomCleanTextField


@Composable
fun InputChord(
    index: Int,
    label: String,
    callback: () -> Unit,
    eliminarCallback: () -> Unit
){

    var linea = remember { mutableStateOf("") }
    var previousLinea by remember { mutableStateOf(linea) }
    var isDeleting by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }


    val chords = List(13) {
        remember { mutableStateOf("   ") }
    }


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 11.dp)
        ){


            for (i in 0..chords.size - 1) {
                Chord(chord = chords[i])
            }


        }

        //CAMBIAR ESTO PARA QUE DETECTE TECLADO NUMERICO
        
        CustomCleanTextField(
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                        if(focusState.isFocused){
                            println("FOCUSED $index")
                        }


                },
            placeholder = "${label} ${index}",
            size = 20,
            fontWeight = FontWeight(400),
            lines = 1,
            callback = callback,
            value = linea.value, onValueChange ={

                isDeleting = it.length < previousLinea.value.length
                previousLinea.value = it
                linea.value = it

                if (it.isEmpty() && isDeleting) {
                    eliminarCallback()
                }

                println(it)
                if(it.isNotEmpty()){
                    if(it.last() == '\n'){
                        callback()
                    }
                }


                linea.value = it
            } )

    }

}