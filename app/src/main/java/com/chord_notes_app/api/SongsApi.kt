package com.chord_notes_app.api

import com.chord_notes_app.data.SongsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SongsApi {

    @GET("songs/")
    suspend fun getSongs(@Header("Authorization") token: String): List<SongsResponse>;

    @GET("songs/{id}/")
    suspend fun getSong(@Header("Authorization") token: String, @Path("id") id: Int): SongsResponse;

    @POST("songs/")
    suspend fun createSong(@Header("Authorization") token: String, @Body song: SongsResponse): SongsResponse;

    @PUT("songs/{id}/")
    suspend fun updateSong(@Header("Authorization") token: String, @Path("id") id: Int, @Body song: SongsResponse): SongsResponse;

    @DELETE("songs/{id}/")
    suspend fun deleteSong(@Header("Authorization") token: String, @Path("id") id: Int): Response<Unit?>;
}