@file:OptIn(ExperimentalMaterial3Api::class)

package com.chord_notes_app.ui.pages.my_notes

import NotesCard
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chord_notes_app.data.SongsResponse
import com.chord_notes_app.ui.components.CustomFloatingIconButton
import com.chord_notes_app.ui.pages.my_notes.actions.getSongs
import com.chord_notes_app.ui.viewModels.SongsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyNotesPage(navController: NavController,
                SongsViewModel: SongsViewModel = hiltViewModel()
                ){

    val scrollState = rememberScrollState()

    val songsList = remember { mutableStateOf<List<SongsResponse>>(emptyList()) }



    LaunchedEffect(Unit) {
        getSongs(navController.context, SongsViewModel,
            onResult = {

                songsList.value = it!!



            }
            )

    }

    Scaffold(
        floatingActionButton = {
            CustomFloatingIconButton(
                icon = Icons.Filled.Add,
                onClick = {
                    navController.navigate("createeditnote")
                }
            )
        }
    ) {


        Box(modifier = Modifier.fillMaxSize()){

            if(songsList.value.isEmpty()){
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(vertical = 90.dp, horizontal = 16.dp)
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "No notes yet", style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.outline
                    ))
                }
            }else{

                Column(
                    Modifier
                        .verticalScroll(scrollState)
                        .fillMaxSize()
                        .padding(vertical = 90.dp, horizontal = 16.dp)
                    ,
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    songsList.value.forEach { song ->

                        Box(
                            modifier = Modifier.padding( vertical = 9.dp)
                        ) {
                            NotesCard(song = song , navController = navController,

{
    navController.navigate("createeditnote/${song.id}")

}

                                )

                        }
                    }



                }


            }


        }



    }

}
