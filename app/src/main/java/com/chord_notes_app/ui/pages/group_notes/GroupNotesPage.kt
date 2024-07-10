package com.chord_notes_app.ui.pages.group_notes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.chord_notes_app.data.GroupsResponse
import com.chord_notes_app.ui.components.GroupsTargetContainer
import com.chord_notes_app.ui.components.TwoFloatingActionButtons
import com.chord_notes_app.ui.pages.group_notes.actions.getGroups
import com.chord_notes_app.ui.viewModels.GroupViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupNotesPage(navController: NavController,
                   groupsViewModel: GroupViewModel = hiltViewModel()
                   ){
    val scrollState = rememberScrollState()

    val groupsList = remember { mutableStateOf<List<GroupsResponse>>(emptyList()) }



    LaunchedEffect(Unit) {
        getGroups(navController.context, groupsViewModel,
            onResult = {
                groupsList.value = it!!

            }
        )

    }


    Scaffold(
        floatingActionButton = {
            TwoFloatingActionButtons(
                navController = navController
            )
        }
    ) {




        Box(modifier = Modifier.fillMaxSize()){

            if(groupsList.value.isEmpty()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Text(text = "No groups yet", style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.outline
                    ))
                }
            }else{

                Column(
                    Modifier
                        .verticalScroll(scrollState)
                        .fillMaxSize()
                        .padding(vertical = 70.dp, horizontal = 16.dp),


                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    groupsList.value.forEach { group ->

                        Box(
                            modifier = Modifier.padding( vertical = 40.dp)
                        ) {
                            GroupsTargetContainer(group = group, navController = navController)

                        }
                    }





                }


            }

        }



    }

}
