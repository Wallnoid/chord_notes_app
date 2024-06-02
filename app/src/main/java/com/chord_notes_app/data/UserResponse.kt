package com.chord_notes_app.data

import com.chord_notes_app.models.User

data class UserResponse(
    override val id: Int,
    override val username: String,
    override val email: String,
    override val first_name: String,
    override val last_name: String

): User
