package com.chord_notes_app.constants

import java.util.Calendar
import java.util.Date


val calendar = Calendar.getInstance().apply {
    time = Date() // Usa la fecha actual
}

val day = calendar.get(Calendar.DAY_OF_MONTH)
val month = calendar.get(Calendar.MONTH) + 1 // Los meses empiezan en 0, por lo que añadimos 1
val year = calendar.get(Calendar.YEAR)

val currentDate = "$day/$month/$year";