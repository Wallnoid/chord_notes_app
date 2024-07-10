package com.chord_notes_app.ui.pages.notes.actions

import android.content.Context
import com.chord_notes_app.data.SongsResponse
import com.chord_notes_app.ui.viewModels.SongsViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun getSong(
    id : Int,
    context: Context,
    songsViewModel: SongsViewModel,
    onResult: (SongsResponse?) -> Unit
)
{

    val sharedPreferencesManager = SharedPreferencesManager.getInstance(context)

    val token = "Token "+ sharedPreferencesManager.getData("token", "")
    println(id)
    println(token)

    songsViewModel.getSong(token, id ,onResult = { song ->
        if (song != null) {
            println(song)
            onResult(song)
        } else {
            println("Failed to get songs")
            onResult(null)
        }
    })



}
