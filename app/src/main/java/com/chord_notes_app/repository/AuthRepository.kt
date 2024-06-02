package com.chord_notes_app.repository

import com.chord_notes_app.api.AuthApi
import com.chord_notes_app.data.AuthResponse
import com.chord_notes_app.data.LoginRequest
import com.chord_notes_app.data.RegisterRequest
import com.chord_notes_app.data.UserResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: AuthApi
)
{
    suspend fun login(username: String, password: String): AuthResponse{
        val loginRequest = LoginRequest(username, password)
        return authApi.login(loginRequest)
    }

    suspend fun register(username: String, password: String, email: String): UserResponse {
        val registerRequest = RegisterRequest(username, password, email)
        return authApi.register(registerRequest)
    }
}