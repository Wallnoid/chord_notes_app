package com.chord_notes_app.data

data class GroupsResponse(
    val id: Int,
    val members: List<Member>,
    val name: String,
    val roles: List<Role>,
    val songs: List<SongsResponse>
)