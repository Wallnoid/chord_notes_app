package com.chord_notes_app.schemas


fun songSchema(author: String, bpm: String, key: String, name:String ): String{
    if(author == ""){
        return "Author is empty"
    }
    if(bpm == ""){
        return "Bpm is empty"
    }
    if(key == ""){
        return "Key is empty"
    }
    if(name == ""){
        return "Name is empty"
    }
    return "OK"

}