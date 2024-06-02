package com.chord_notes_app.models

interface Auth {
    val token: String
    val user: User
}