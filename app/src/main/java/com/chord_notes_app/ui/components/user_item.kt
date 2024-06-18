package com.chord_notes_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chord_notes_app.utils.cutString

@Composable
fun UserItem(
    userName: String,
    email: String
){

    Row(
        modifier = Modifier
            .padding(16.dp)
        ,
        verticalAlignment = Alignment.CenterVertically

    ) {

        Box(
            modifier = Modifier

                .size(40.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = CircleShape
                )

            ,
            contentAlignment = Alignment.Center

        ) {


            Icon(imageVector = Icons.Filled.Person, contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column {

            Text(
                text = cutString(userName, 15) , style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,

                )

            Text(
                text = cutString(email, 20), style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,

                )

        }

        Spacer(modifier = Modifier.weight(1f)
            )

        CustomIconButton(
            icon = Icons.Filled.Close,
            {}
        )






    }
}