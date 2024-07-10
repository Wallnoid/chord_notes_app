package com.chord_notes_app.ui.pages.notes.actions

import android.widget.Toast
import androidx.navigation.NavController
import com.chord_notes_app.data.SongsResponse
import com.chord_notes_app.ui.viewModels.SongsViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun createSong(
    navController: NavController,
    songsViewModel: SongsViewModel,
    song: SongsResponse,
    onResult: (SongsResponse?) -> Unit
)
{

    println( song)

    val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

    val token = "Token "+ sharedPreferencesManager.getData("token", "")

    songsViewModel.createSong(token, song, onResult = { song ->
        if (song != null) {
            println(song)
            onResult(song)
            Toast.makeText(navController.context, "Song created", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        } else {
            println("Failed to create song")
            Toast.makeText(navController.context, "existing relationship", Toast.LENGTH_SHORT).show()
            onResult(null)
        }
    })

}