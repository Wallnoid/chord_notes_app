package com.chord_notes_app.ui.pages.notes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.AutoFixHigh
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chord_notes_app.constants.currentDate
import com.chord_notes_app.ui.components.CustomCleanTextField
import com.chord_notes_app.ui.components.CustomTopAppBar
import com.chord_notes_app.ui.pages.notes.components.CustomText
import com.chord_notes_app.ui.pages.notes.components.IconTitle
import com.chord_notes_app.ui.pages.notes.components.InputChord
import com.chord_notes_app.utils.SharedPreferencesManager

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEditNotePage(
   navController: NavController
) {

    val scrollState = rememberScrollState()

    val title = remember { mutableStateOf("") }

    val key = remember { mutableStateOf("") }

    val bpm = remember { mutableStateOf("") }

    val autor = remember { mutableStateOf("") }
    val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

    val user = sharedPreferencesManager.getData("username","")

    var textFields by remember { mutableStateOf(mapOf<Int, @Composable () -> Unit>()) }
    var currentKey by remember { mutableStateOf(1) }


    fun eliminarTextfield(key: Int) {
        textFields = textFields.toMutableMap().apply {
            remove(key)
        }
    }

    fun actualizarTextfields(previousKey: Int) {
        val newIndex = currentKey++
        val updatedFields = mutableMapOf<Int, @Composable () -> Unit>()

        textFields.forEach { (key, textField) ->
            updatedFields[key] = textField
            if (key == previousKey) {
                updatedFields[newIndex] = {
                    InputChord(
                        index = newIndex,
                        label = "",
                        callback = { actualizarTextfields(newIndex) },
                        eliminarCallback = { eliminarTextfield(newIndex) }
                    )
                }
            }
        }

        textFields = updatedFields
    }




    Scaffold(

        topBar = {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

            CustomTopAppBar(scrollBehavior = scrollBehavior, title ="Create Song", icon =
            Icons.Filled.ArrowBackIosNew,
                onNavigationIconClicked = {
                    navController.popBackStack()
                },
                action = {

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Save,
                            contentDescription = "Localized description"
                        )

                    }
                }

            )
        }
    ) {

        Column(
            Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(vertical = 70.dp, horizontal = 5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            CustomCleanTextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = "Title...",
                size = 35,
                fontWeight = FontWeight(400),
                value = title.value, onValueChange ={
                    title.value = it
                } )


            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 0.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconTitle(icon = Icons.Outlined.MusicNote, title = "Key:")

                    //Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(25.dp))


                    CustomCleanTextField(
                        modifier = Modifier.width(200.dp),
                        placeholder = "Type the key...",
                        size = 15,
                        fontWeight =  FontWeight(400),
                        value = key.value, onValueChange ={
                            key.value = it
                        } )



                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconTitle(icon = Icons.Outlined.AutoFixHigh, title = "Bpm:", iconSize = 18)

                    //Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(19.dp))

                    CustomCleanTextField(
                        modifier = Modifier.width(200.dp),
                        placeholder = "Type the BPM...",
                        size = 15,
                        fontWeight =  FontWeight(400),
                        value = bpm.value, onValueChange ={
                            bpm.value = it
                        } )



                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconTitle(icon = Icons.Outlined.Person, title = "Author:")

                    //Spacer(modifier = Modifier.weight(1f))
                    //Spacer(modifier = Modifier.width(1.dp))


                    CustomCleanTextField(
                        modifier = Modifier.width(200.dp),
                        placeholder = "Type the Author...",
                        size = 15,
                        fontWeight =  FontWeight(400),
                        value = autor.value, onValueChange ={
                            autor.value = it
                        } )



                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconTitle(icon = Icons.Outlined.People, title = "Group:")

                    //Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(8.dp))


                    DropdownMenuExample()



                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)

                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    IconTitle(icon = Icons.Outlined.DateRange, title = "Date:")

                    Spacer(modifier = Modifier.weight(1f))

                    CustomText(text = currentDate)

                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    IconTitle(icon = Icons.Outlined.Person, title = "User:")

                    Spacer(modifier = Modifier.weight(1f))

                    CustomText(text = user.toString())

                }
                Spacer(modifier = Modifier.height(5.dp))

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)

                )




            }


            if (textFields.isEmpty()) {
                textFields = mapOf(
                    0 to { InputChord(index =0, label = "Tap to start writing..." ,callback = { actualizarTextfields( previousKey = 0) }, eliminarCallback =  { eliminarTextfield(0) }) }
                )
            }


            // Interfaz de usuario
            Column(modifier = Modifier.fillMaxWidth()) {
                textFields.forEach { (_, textField) ->
                    textField()
                }
            }



        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuExample() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    val options = listOf("Option 1", "Option 2", "Option 3")

    Column(modifier = Modifier.fillMaxWidth()) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = { selectedOption = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                placeholder = { Text("Select a group",
                    style = TextStyle(fontWeight = FontWeight(400), color = MaterialTheme.colorScheme.outline)
                    ) },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,


                ),
                textStyle = TextStyle(
                    fontWeight = FontWeight(400),
                    color = MaterialTheme.colorScheme.outline
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            selectedOption = option
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CreateEditNotePagePreview() {
    //CreateEditNotePage()
}