package com.chord_notes_app.ui.pages.group

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AmpStories
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chord_notes_app.ui.components.CustomFloatingIconButton
import com.chord_notes_app.ui.components.CustomTextField
import com.chord_notes_app.ui.components.CustomTopAppBar
import com.chord_notes_app.ui.components.UserItem

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateEditGroupPage(
    navController: NavController
){
    val title = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }


    Scaffold(


        topBar = {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

            CustomTopAppBar(scrollBehavior = scrollBehavior, title ="Create Group", icon =
            Icons.Filled.ArrowBackIosNew,
                onNavigationIconClicked = {
                    navController.popBackStack()
                }
            )
        }



    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 90.dp, horizontal = 16.dp),


            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start


        ) {

            CustomTextField(
                value = title.value,
                onValueChange = { title.value = it },
                placeholder = "Group's Name",
                label = "Group's Name",
                modifier = Modifier
                    .fillMaxWidth(),
                icon = Icons.Filled.AmpStories
            )

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                CustomTextField(
                    value = username.value,
                    onValueChange = { username.value = it },
                    placeholder = "type the username",
                    label = "Search User",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp),
                    icon = Icons.Filled.PersonSearch
                )


                CustomFloatingIconButton(onClick = { /*TODO*/ }, icon = Icons.Filled.Search )
            }

            Spacer(modifier = Modifier.height(30.dp))



                Text(text = "Members", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurface)


            Spacer(modifier = Modifier.height(10.dp))

            UserItem(userName = "William", email ="wulli.mu28@gmail.com" )



            UserItem(userName = "Mateo", email ="asdasd.mu28@gmail.com" )


        }

    }

}