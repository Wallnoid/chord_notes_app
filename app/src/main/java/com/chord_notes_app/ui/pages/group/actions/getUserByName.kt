package com.chord_notes_app.ui.pages.group.actions

import androidx.navigation.NavController
import com.chord_notes_app.data.Member
import com.chord_notes_app.data.username
import com.chord_notes_app.ui.viewModels.AuthViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun getUserByName(
    navController: NavController,
    authViewModel: AuthViewModel,
    username: username,
    onResult: (Member?) -> Unit,
)
{

    val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

    val token = "Token "+ sharedPreferencesManager.getData("token", "")

    authViewModel.getUserByName( token, username, onResult = { user ->
        if (user != null) {
            println(user)
            onResult(user)
        } else {
            println("Failed to get user")
            onResult(null)
        }
    })


}

