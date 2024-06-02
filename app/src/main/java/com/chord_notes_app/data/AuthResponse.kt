package com.chord_notes_app.data

import com.chord_notes_app.models.Auth

data class AuthResponse(
    override val token: String,
    override val user: UserResponse
) : Auth // Implementa la interfaz Auth
