package com.chord_notes_app.routes

sealed class Routes (val screen: String) {

    data object  GetStarted: Routes("getstarted")
    data object  LogIn: Routes("login")

    data object  LogUp: Routes("logup")

    data object  MyNotes: Routes( "mynotes")
    data object GroupNotes: Routes("groupnotes")
    data object Settings: Routes("settings")
    data object Profile: Routes("profile")
}