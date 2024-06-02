package com.chord_notes_app.api

import com.chord_notes_app.data.AuthResponse
import com.chord_notes_app.data.LoginRequest
import com.chord_notes_app.data.RegisterRequest
import com.chord_notes_app.data.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login/")
    suspend fun login(@Body loginRequest: LoginRequest): AuthResponse

    @POST("auth/create/")
    suspend fun register(@Body registerRequest: RegisterRequest): UserResponse
}