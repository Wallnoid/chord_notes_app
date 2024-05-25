@file:OptIn(ExperimentalMaterial3Api::class)

package com.chord_notes_app.ui.pages.my_notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MyNotesPage(navController: NavController){
    
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            Text(text = "My Notes")

        }
    }

}
