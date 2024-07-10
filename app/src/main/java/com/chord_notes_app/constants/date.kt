package com.chord_notes_app.constants

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date


val calendar = Calendar.getInstance().apply {
    time = Date() // Usa la fecha actual
}

val day = calendar.get(Calendar.DAY_OF_MONTH)
val month = calendar.get(Calendar.MONTH) + 1 // Los meses empiezan en 0, por lo que añadimos 1
val year = calendar.get(Calendar.YEAR)

val currentDate = "$day/$month/$year";

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(date: String): String {

    if(date == ""){
        return currentDate
    }

    // Parsear la fecha de entrada
    val offsetDateTime = OffsetDateTime.parse(date)

    // Definir el formateador de la fecha
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    // Formatear la fecha
    val formattedDate = offsetDateTime.format(formatter)

    return formattedDate
}