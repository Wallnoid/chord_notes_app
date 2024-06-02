package com.chord_notes_app.ui.pages.account.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordTextField(
    label: String,
    icon: ImageVector,
    isValid: Boolean = true,
    errorMessage: String = "",
    text: String,
    onChange: (String) -> Unit

    ) {
    var hidden by remember { mutableStateOf(true) } //1


    return TextField(

        value = text,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = "PasswordIcon") },
        shape = RoundedCornerShape(50),
        onValueChange = onChange,

        singleLine = true,
        isError = !isValid,
        supportingText = {
            if(!isValid){

                Text(
                    modifier =  Modifier.fillMaxWidth(),
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },



        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            errorLeadingIconColor = MaterialTheme.colorScheme.error
        ),



        visualTransformation =  if (hidden) PasswordVisualTransformation() else VisualTransformation.None,//3
        trailingIcon = {// 4
            IconButton(onClick = { hidden = !hidden }) {
                val vector =  if (hidden)  Icons.Outlined.Visibility
                else Icons.Outlined.VisibilityOff
                val description = if (hidden) "Ocultar contraseña" else "Revelar contraseña"
                //6
                Icon(imageVector = vector, contentDescription = description )

            }
        },


        label = { Text(text = "Password") },
        placeholder = { Text(text = "Enter your password") },

        )
}