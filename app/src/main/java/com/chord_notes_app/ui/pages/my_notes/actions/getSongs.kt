package com.chord_notes_app.ui.pages.my_notes.actions

import android.content.Context
import com.chord_notes_app.data.SongsResponse
import com.chord_notes_app.ui.viewModels.SongsViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun getSongs(
    context: Context,
    songsViewModel: SongsViewModel,
    onResult: (List<SongsResponse>?) -> Unit
)
{

    val sharedPreferencesManager = SharedPreferencesManager.getInstance(context)

    val token = "Token "+ sharedPreferencesManager.getData("token", "")

    songsViewModel.getSongs(token, onResult = { songs ->
        if (songs != null) {
            println(songs)
            onResult(songs)
        } else {
            println("Failed to get songs")
            onResult(null)
        }
    })



}
