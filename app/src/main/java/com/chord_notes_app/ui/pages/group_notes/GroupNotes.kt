package com.chord_notes_app.ui.pages.group_notes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.chord_notes_app.ui.components.CustomFloatingIconButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupNotes(navController: NavController){
    Scaffold(
        floatingActionButton = {
            CustomFloatingIconButton(
                icon = Icons.Filled.Add,
                onClick = {}
            )
        }
    ) {


        Box(modifier = Modifier.fillMaxSize()){
            Column(
                Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "Group Notes")

            }
        }



    }

}
