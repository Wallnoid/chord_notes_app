package com.chord_notes_app.ui.pages.group_notes.actions


import android.widget.Toast
import androidx.navigation.NavController
import com.chord_notes_app.data.SongGroup
import com.chord_notes_app.ui.viewModels.GroupViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun addSongTogroup(
    navController: NavController,
    groupViewModel: GroupViewModel,
    respond: SongGroup,
    onResult: (SongGroup?) -> Unit
)
{



    val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

    val token = "Token "+ sharedPreferencesManager.getData("token", "")

    groupViewModel.addSongToGroup(token, respond, onResult = { song ->
        if (song != null) {
            println(song)
            onResult(song)
            Toast.makeText(navController.context, "Relation created", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        } else {
            println("Failed to create song")
            Toast.makeText(navController.context, "Failed to create song", Toast.LENGTH_SHORT).show()
            onResult(null)
        }
    })

}