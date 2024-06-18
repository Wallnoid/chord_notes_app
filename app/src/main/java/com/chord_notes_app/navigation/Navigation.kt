package com.chord_notes_app.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chord_notes_app.routes.Routes
import com.chord_notes_app.ui.components.GlobalSchema
import com.chord_notes_app.ui.pages.account.LoginPage
import com.chord_notes_app.ui.pages.account.RegisterPage
import com.chord_notes_app.ui.pages.get_start.GetStartedPage
import com.chord_notes_app.ui.pages.group.CreateEditGroupPage
import com.chord_notes_app.ui.pages.group_notes.GroupNotesPage
import com.chord_notes_app.ui.pages.my_notes.MyNotesPage
import com.chord_notes_app.ui.pages.notes.CreateEditNotePage
import com.chord_notes_app.ui.pages.profile.ProfilePage
import com.chord_notes_app.ui.pages.settings.SettingsPage
import com.chord_notes_app.utils.SharedPreferencesManager

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val titleState = remember { mutableStateOf("My Notes") }
    val selected = remember { mutableStateOf(0) }
    var startDestination = Routes.GetStarted.screen

    val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

    sharedPreferencesManager.getData("token","")?.let {

        if(it.equals("")){
            startDestination = Routes.GetStarted.screen
        }else {
            startDestination = Routes.MyNotes.screen
        }
    }


    NavHost(
        navController =navController,
        startDestination = startDestination

    ){
        composable(Routes.GetStarted.screen){ GetStartedPage(navController)}
        composable(Routes.LogIn.screen){ LoginPage(navController)}
        composable(Routes.LogUp.screen){ RegisterPage(navController)}
        composable(Routes.MyNotes.screen){
            GlobalSchema(navigationController = navController,
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                selected = selected,
                titleState = titleState,
                content = { MyNotesPage(navController) })
        }
        composable(Routes.GroupNotes.screen){
            GlobalSchema(navigationController = navController,
                titleState = titleState,
                selected = selected,
                drawerState = drawerState,
                coroutineScope = coroutineScope
                ,content = { GroupNotesPage(navController) })

        }
        composable(Routes.Profile.screen){
            GlobalSchema(navigationController = navController,
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                titleState = titleState,
                selected = selected,
                content = { ProfilePage(navController) })

        }
        composable(Routes.Settings.screen){
            GlobalSchema(navigationController = navController,
                selected = selected,
                titleState = titleState,
                drawerState = drawerState,
                coroutineScope = coroutineScope,
                content = { SettingsPage(navController) })

        }

        composable(Routes.CreateEditNote.screen){ CreateEditNotePage(navController) }
        composable(Routes.CreateEditGroup.screen){ CreateEditGroupPage(navController)}

    }
}

