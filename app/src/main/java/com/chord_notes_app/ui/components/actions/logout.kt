package com.chord_notes_app.ui.components.actions

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.chord_notes_app.ui.viewModels.AuthViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun logout(
    authViewModel: AuthViewModel,
    context: Context,
    navigationController: NavController
) {
    val sharedPreferencesManager = SharedPreferencesManager.getInstance(context)
    val token = "Token " + sharedPreferencesManager.getData("token", "") as String

    println(token)
    authViewModel.logout(token){ Boolean ->
        if (Boolean) {
            println("Logout successful")
            sharedPreferencesManager.clearData();
            Toast.makeText(context, "Logout successful", Toast.LENGTH_SHORT).show()
            navigationController.navigate("getstarted")


        } else {
            println("Logout failed")
            Toast.makeText(context, "fail to logout, restart the application", Toast.LENGTH_SHORT).show()

        }
    }


}