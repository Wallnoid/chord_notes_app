package com.chord_notes_app.api

import com.chord_notes_app.data.AuthResponse
import com.chord_notes_app.data.LoginRequest
import com.chord_notes_app.data.Member
import com.chord_notes_app.data.RegisterRequest
import com.chord_notes_app.data.UserResponse
import com.chord_notes_app.data.username
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login/")
    suspend fun login(@Body loginRequest: LoginRequest): AuthResponse

    @POST("auth/create/")
    suspend fun register(@Body registerRequest: RegisterRequest): UserResponse

    @POST("auth/logout/")
    suspend fun logout(@Header("Authorization") token: String): Response<Void>;


    @POST("auth/logoutall/")
    suspend fun logoutAll();


    @GET("auth/profile/")
    suspend fun profile(): UserResponse

    @POST("auth/get-user-by-name/")
    suspend fun getUserByName(@Header("Authorization") token: String, @Body username: username): Member
}
