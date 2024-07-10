package com.chord_notes_app.ui.pages.notes.actions



import android.widget.Toast
import androidx.navigation.NavController
import com.chord_notes_app.data.SongsResponse
import com.chord_notes_app.ui.viewModels.SongsViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun deleteSong(
    navController: NavController,
    songsViewModel: SongsViewModel,
    song: SongsResponse,
    onResult: (String?) -> Unit
)
{


    val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

    val token = "Token "+ sharedPreferencesManager.getData("token", "")

    songsViewModel.deleteSong(token, song.id!!, onResult = { song ->
        if (song != null) {
            println(song)
            onResult(song)
            Toast.makeText(navController.context, "Song deleted", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        } else {
            println("Failed to delete song")
            onResult(null)
            Toast.makeText(navController.context, "Failed to delete Song", Toast.LENGTH_SHORT).show()

        }
    })

}