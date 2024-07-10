package com.chord_notes_app.ui.pages.group_notes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chord_notes_app.data.NameResponse
import com.chord_notes_app.data.SongGroup
import com.chord_notes_app.ui.components.CustomTopAppBar
import com.chord_notes_app.ui.components.DefaultDropdownMenu
import com.chord_notes_app.ui.pages.group_notes.actions.addSongTogroup
import com.chord_notes_app.ui.pages.group_notes.actions.getGroups
import com.chord_notes_app.ui.pages.my_notes.actions.getSongs
import com.chord_notes_app.ui.viewModels.GroupViewModel
import com.chord_notes_app.ui.viewModels.SongsViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSongToGroupPage(
    navController: NavController,
    groupsViewModel: GroupViewModel = hiltViewModel(),
    songsViewModel: SongsViewModel = hiltViewModel()
) {

    val groupsList = remember { mutableStateOf<List<NameResponse>>(emptyList()) }
    val songsList = remember { mutableStateOf<List<NameResponse>>(emptyList()) }
    val scrollState = rememberScrollState()

    val idSong = remember { mutableStateOf(0) }
    val idGroup = remember { mutableStateOf(0) }


    fun onSongSelected(id: Int){
        println("idSong: $id")
        idSong.value = id
    }

    fun onGroupSelected(id: Int){
        println("idGroup: $id")
        idGroup.value = id
    }




    LaunchedEffect(Unit) {
        getGroups(navController.context, groupsViewModel,
            onResult = {

                it!!.forEach {
                    groupsList.value += NameResponse(it.id, it.name)
                }

            }


        )

        getSongs(
            navController.context, songsViewModel,
            onResult = {
                it!!.forEach {
                    songsList.value += NameResponse(it.id!!, it.name)
                }
            }
        )

    }


    Scaffold(

        topBar = {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

            CustomTopAppBar(scrollBehavior = scrollBehavior, title ="Share Song", icon =
            Icons.Filled.ArrowBackIosNew,
                onNavigationIconClicked = {
                    navController.popBackStack()
                },


            )

        }



    ){

        Box(modifier = Modifier.fillMaxSize() ){

            Column(
                Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(vertical = 70.dp, horizontal = 16.dp),


                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){



                Row(
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Text(
                        "Group:",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight(600),
                            fontSize = 23.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    )
                }

                DefaultDropdownMenu(label = "Groups", options = groupsList.value, icon = Icons.Filled.Group ,  callback = ::onGroupSelected)

                Spacer(modifier = Modifier.padding(10.dp))

                Row(
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Text(
                        "Song:",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight(600),
                            fontSize = 23.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    )
                }

                DefaultDropdownMenu(label = "Songs", options = songsList.value, icon = Icons.Filled.Group ,  callback = ::onSongSelected)

                Spacer(modifier = Modifier.padding(20.dp))





                // Button to add song to group

                Button(onClick = {

                    val songGroup  = SongGroup(idSong.value, idGroup.value)

                    addSongTogroup(navController, groupsViewModel, songGroup,
                        onResult = {
                            if(it != null){
                                println(it)
                            }
                        }
                    )



                }) {


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center


                    ) {
                        Icon(Icons.Filled.Edit, null)
                        Text(text = "Save Song to Group")

                    }
                }



            }

        }


    }
}