package com.chord_notes_app.routes

sealed class Routes (val screen: String) {

    data object  GetStarted: Routes("getstarted")
    data object  LogIn: Routes("login")

    data object  LogUp: Routes("logup")

    data object  MyNotes: Routes( "mynotes")
    data object GroupNotes: Routes("groupnotes")
    data object Settings: Routes("settings")
    data object Profile: Routes("profile")

    data object EditNote: Routes("createeditnote/{noteId}")

    data object CreateNote: Routes("createeditnote")

    data object CreateGroup: Routes("createeditgroup")

    data object EditGroup: Routes("createeditgroup/{groupId}")

    data object AddSongToGroup: Routes("addsongtogroup")

    data object SeeSong: Routes("seeSong/{noteId}")

}