package com.chord_notes_app.routes

sealed class Routes (val screen: String) {
    data object  Home: Routes( "home")
    data object Notes: Routes("notes")
    data object Settings: Routes("settings")
    data object Profile: Routes("profile")
}