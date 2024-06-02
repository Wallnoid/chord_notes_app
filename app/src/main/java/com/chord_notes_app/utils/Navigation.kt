package com.chord_notes_app.utils

import androidx.navigation.NavController


fun navigate( navController: NavController, route: String){
    navController.navigate(route)
}