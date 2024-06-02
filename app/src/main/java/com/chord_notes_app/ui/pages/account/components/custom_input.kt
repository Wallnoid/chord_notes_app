@file:OptIn(ExperimentalMaterial3Api::class)

package com.chord_notes_app.ui.pages.account.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    label: String,
    placeholder: String,
    icon: ImageVector,
    isValid:Boolean = true,
    errorMessage: String ="",
    text: String,
    onChange: (String) -> Unit

) {

    return TextField(


        value = text,
        leadingIcon = { Icon(imageVector = icon, contentDescription = "emailIcon") },
        shape = RoundedCornerShape(10),
        onValueChange = onChange,
        singleLine = true,

        isError = !isValid,
        supportingText = {
                         if(!isValid){

                             Text(
                                 modifier =  Modifier.fillMaxWidth(),
                                 text = errorMessage,

                             )

                         }
        },
        trailingIcon = {
                       if (!isValid){
                           Icon(imageVector = Icons.Outlined.Error, contentDescription ="" )
                       }

        },

        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            errorSupportingTextColor = MaterialTheme.colorScheme.error,
            errorLeadingIconColor = MaterialTheme.colorScheme.error


        ),



        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },

    )
}