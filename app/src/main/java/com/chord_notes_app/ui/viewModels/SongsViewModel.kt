package com.chord_notes_app.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chord_notes_app.data.SongsResponse
import com.chord_notes_app.repository.SongsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SongsViewModel @Inject constructor(
    private val songsRepository: SongsRepository
) : ViewModel() {

    fun getSongs(token: String, onResult:(List<SongsResponse>?) -> Unit ) {

        viewModelScope.launch {
            try {
                val songs = songsRepository.getSongs(token)

                println(songs)
                onResult(songs)

            } catch (e: Exception) {
                println(e)
                onResult(null)
            }
        }

    }

    fun getSong(token: String, id: Int) {
        viewModelScope.launch {
            try {
                val song = songsRepository.getSong(token, id)
                println(song)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun createSong(token: String, song: SongsResponse) {



    }

    fun updateSong(token: String, id: Int, song: SongsResponse) {
    }

    fun deleteSong(token: String, id: Int) {
        viewModelScope.launch {
            try {
                val song = songsRepository.deleteSong(token, id)
                println(song)
            } catch (e: Exception) {
                println(e)
            }
        }


    }
}