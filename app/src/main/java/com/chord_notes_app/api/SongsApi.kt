package com.chord_notes_app.api

import com.chord_notes_app.data.SongsResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface SongsApi {

    @GET("songs/")
    suspend fun getSongs(@Header("Authorization") token: String): List<SongsResponse>;

    @GET("songs/{id}")
    suspend fun getSong(@Header("Authorization") token: String, id: Int): SongsResponse;

    @POST("songs/")
    suspend fun createSong(@Header("Authorization") token: String, song: SongsResponse): SongsResponse;

    @PUT("songs/{id}")
    suspend fun updateSong(@Header("Authorization") token: String, id: Int, song: SongsResponse): SongsResponse;

    @DELETE("api/songs/{id}")
    suspend fun deleteSong(@Header("Authorization") token: String, id: Int): String;
}