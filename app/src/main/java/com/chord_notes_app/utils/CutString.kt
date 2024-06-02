package com.chord_notes_app.utils


fun cutString(string: String, length: Int): String {
    return if (string.length > length) {
        string.substring(0, length) + "..."
    } else {
        string
    }
}