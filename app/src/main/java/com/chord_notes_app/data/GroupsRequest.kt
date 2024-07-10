package com.chord_notes_app.data

data class GroupsRequest (
    val id: Int?,
    val name: String,
    val integrante_ids: List<Int>?,
)