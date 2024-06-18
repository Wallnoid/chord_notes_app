package com.chord_notes_app.data

data class SongsResponse(
    val author: String,
    val bpm: Int,
    val chords: Chords,
    val id: Int,
    val id_prov: String,
    val key: String,
    val lyrics: Lyrics,
    val name: String
)