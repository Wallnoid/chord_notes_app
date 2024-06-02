package com.chord_notes_app.ui.pages.account.actions

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.chord_notes_app.routes.Routes
import com.chord_notes_app.ui.viewModels.AuthViewModel
import com.chord_notes_app.utils.SharedPreferencesManager
import com.chord_notes_app.utils.navigate

fun login(navController: NavController,
          authViewModel: AuthViewModel,
          usernameState: String,
          passwordState: String,
          context: Context
             ) {
            authViewModel.login(usernameState, passwordState) { authResponse ->

                if (authResponse == null) {
                    Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()

                    return@login
                }

                val sharedPreferencesManager = SharedPreferencesManager.getInstance(context)
                sharedPreferencesManager.saveData("username", authResponse.user.username)
                sharedPreferencesManager.saveData("email", authResponse.user.email)
                sharedPreferencesManager.saveData("id", authResponse.user.id.toString())
                sharedPreferencesManager.saveData("token", authResponse.token)
                navigate(navController, Routes.MyNotes.screen)

            }
    }


fun register(navController: NavController,
             authViewModel: AuthViewModel,
             usernameState: String,
             passwordState: String,
             emailState: String,
             context: Context
) {


    authViewModel.register(usernameState, passwordState, emailState) { userResponse ->

        if (userResponse == null) {
            Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
            return@register
        }

        login(
            navController,
            authViewModel,
            usernameState,
            passwordState,
            context
        )


    }
}



