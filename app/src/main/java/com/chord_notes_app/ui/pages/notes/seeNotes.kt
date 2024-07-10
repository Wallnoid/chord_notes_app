package com.chord_notes_app.ui.pages.notes


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chord_notes_app.constants.formatDate
import com.chord_notes_app.data.Chords
import com.chord_notes_app.data.Lyrics
import com.chord_notes_app.data.SongsResponse
import com.chord_notes_app.ui.components.CustomCleanTextField
import com.chord_notes_app.ui.components.CustomTopAppBar
import com.chord_notes_app.ui.pages.notes.actions.getSong
import com.chord_notes_app.ui.pages.notes.components.CustomText
import com.chord_notes_app.ui.pages.notes.components.IconTitle
import com.chord_notes_app.ui.pages.notes.components.InputChord
import com.chord_notes_app.ui.viewModels.SongsViewModel
import com.chord_notes_app.utils.SharedPreferencesManager

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeeNotePage(
    noteId: String? = null,
    navController: NavController,
    SongsViewModel: SongsViewModel = hiltViewModel()
) {

    val song = remember { mutableStateOf<SongsResponse>(
        SongsResponse(
            author = "",
            bpm = 0,
            chords = Chords(lines = mutableMapOf()),
            id_prov = "",
            key = "",
            lyrics = Lyrics(lines = mutableMapOf()),
            name = "",
            id = null,
            name_user = "",
            date = ""
        )
    ) }

    val isEdit = noteId != null

    val scrollState = rememberScrollState()

    val title = remember { mutableStateOf("") }


    val key = remember { mutableStateOf("") }

    val bpm = remember { mutableStateOf("") }

    val autor = remember { mutableStateOf("") }

    val date = remember { mutableStateOf("") }


    val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

    var user = sharedPreferencesManager.getData("username","")

    val username = remember { mutableStateOf(user) }

    var textFields by remember { mutableStateOf(mapOf<Int, @Composable () -> Unit>()) }
    var textFieldsValues by remember { mutableStateOf(mapOf<Int, MutableState<String>>()) }
    var currentKey by remember { mutableStateOf(1) }

    var chordList by remember {
        mutableStateOf(
            mutableMapOf<Int, MutableList<String>>()  // Inicialización como un mapa mutable vacío
        )
    }





    fun addChordList(key: Int) {
        val newList = MutableList(13) { "" }
        chordList[key] = newList
    }


    fun eliminarTextfield(key: Int) {
        textFields = textFields.toMutableMap().apply {
            remove(key)
        }

        textFieldsValues = textFieldsValues.toMutableMap().apply {
            remove(key)
        }

        chordList = chordList.toMutableMap().apply {
            remove(key)
        }
    }



    // Hay que crear una función para actualizar los textFields con los datos que devuelve la funcion

    fun actualizarTextfields(previousKey: Int) {
        val newIndex = currentKey++
        val updatedFields = mutableMapOf<Int, @Composable () -> Unit>()
        val updateFieldsValues = mutableMapOf<Int, MutableState<String>>()

        textFieldsValues.forEach { (key, value) ->
            updateFieldsValues[key] = value
            if (key == previousKey) {
                updateFieldsValues[newIndex] = mutableStateOf("")
            }
        }

        textFieldsValues = updateFieldsValues

        textFields.forEach { (key, textField) ->
            updatedFields[key] = textField
            if (key == previousKey) {
                updatedFields[newIndex] = {
                    InputChord(
                        index = newIndex,
                        label = "",
                        callback = { actualizarTextfields(newIndex) },
                        eliminarCallback = { eliminarTextfield(newIndex) },

                        value = updateFieldsValues[newIndex]!!,
                        addChords = { index, chords ->
                            chordList[index] = chords
                        },
                        chordsE = null
                    )
                }
            }
        }

        textFields = updatedFields
    }

    fun initializeFieldsFromSong(song: SongsResponse) {
        val lyrics = song.lyrics.lines
        val chords = song.chords.lines

        val updatedFields = mutableMapOf<Int, @Composable () -> Unit>()
        val updateFieldsValues = mutableMapOf<Int, MutableState<String>>()

        lyrics.forEach { (index, line) ->
            updateFieldsValues[index] = mutableStateOf(line)
            updatedFields[index] = {
                InputChord(
                    index = index,
                    label = "",
                    callback = { actualizarTextfields(index) },
                    eliminarCallback = { eliminarTextfield(index) },
                    value = updateFieldsValues[index]!!,
                    addChords = { idx, ch -> chordList[idx] = ch },
                    chordsE = chords[index]
                )
            }
        }

        textFieldsValues = updateFieldsValues
        textFields = updatedFields
    }

    LaunchedEffect(Unit) {

        if (isEdit){
            getSong(noteId!!.toInt() ,navController.context, SongsViewModel,
                onResult = {
                    song.value = it!!
                    title.value = song.value?.name.toString()
                    key.value = song.value?.key.toString()
                    bpm.value = song.value.bpm.toString()
                    autor.value = song.value?.author.toString()
                    date.value = song.value?.date.toString()
                    username.value = song.value?.name_user.toString()

                    if( song.value.lyrics.lines.isNotEmpty()){
                        chordList = song.value.chords.lines.toMutableMap()
                        initializeFieldsFromSong(song.value)

                        currentKey = song.value.lyrics.lines.size + 1

                    }




                }
            )


        }


    }



    Scaffold(

        topBar = {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

            CustomTopAppBar(scrollBehavior = scrollBehavior, title ="View Song", icon =
            Icons.Filled.ArrowBackIosNew,
                onNavigationIconClicked = {
                    navController.popBackStack()
                },


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

                    CustomText(text = formatDate(date.value))

                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    IconTitle(icon = Icons.Outlined.Person, title = "User:")

                    Spacer(modifier = Modifier.weight(1f))

                    CustomText(text = username.value)

                }
                Spacer(modifier = Modifier.height(5.dp))

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)

                )




            }


            if (textFields.isEmpty()) {

                textFieldsValues = mapOf(
                    0 to mutableStateOf("")
                )

                textFields = mapOf(
                    0 to { InputChord(index =0, label = "Tap to start writing..." ,callback = { actualizarTextfields( previousKey = 0) }, eliminarCallback =  { eliminarTextfield(0) },
                        value = textFieldsValues[0]!!
                        ,
                        addChords = { index, chords ->
                            chordList[index] = chords
                        },
                        chordsE = null
                    )

                    }
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


