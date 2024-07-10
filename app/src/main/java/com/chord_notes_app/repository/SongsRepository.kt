package com.chord_notes_app.repository

import com.chord_notes_app.api.SongsApi
import com.chord_notes_app.data.SongsResponse
import retrofit2.Response
import javax.inject.Inject

class SongsRepository @Inject constructor(
    private val songsApi: SongsApi
){

    suspend fun getSongs( token: String): List<SongsResponse>{
        return songsApi.getSongs( token);
    }

    suspend fun getSong( token: String, id: Int): SongsResponse{
        return songsApi.getSong( token, id);
    }

    suspend fun createSong( token: String, song: SongsResponse): SongsResponse{
        return songsApi.createSong( token, song);
    }

    suspend fun updateSong( token: String, id: Int, song: SongsResponse): SongsResponse{
        return songsApi.updateSong( token, id, song);
    }

    suspend fun deleteSong( token: String, id: Int): Response<Unit?> {
        return songsApi.deleteSong( token, id);
    }


}