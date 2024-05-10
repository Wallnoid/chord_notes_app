@file:OptIn(ExperimentalMaterial3Api::class)

package com.chord_notes_app.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chord_notes_app.routes.Routes
import com.chord_notes_app.ui.pages.home.HomePage
import com.chord_notes_app.ui.pages.notes.NotesPage
import com.chord_notes_app.ui.pages.profile.ProfilePage
import com.chord_notes_app.ui.pages.settings.SettingsPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomSidebar(){
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current.applicationContext
    val titleState = remember { mutableStateOf("Home") }
    val selected = remember { mutableStateOf(0) }


    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {

                Text("Menu", modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge)
                Divider()

                Spacer(modifier = Modifier.weight(weight = 0.1f))

                Text("Browse", modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.outline)



                DrawerItem(
                    selected = selected ,
                    coroutineScope = coroutineScope ,
                    drawerState = drawerState,
                    navigationController = navigationController,
                    titleState = titleState,
                    selectedValue = 0,
                    title = "Home",
                    icon = Icons.Outlined.Home,
                    route = Routes.Home.screen
                )

                DrawerItem(
                    selected = selected ,
                    coroutineScope = coroutineScope ,
                    drawerState = drawerState,
                    navigationController = navigationController,
                    titleState = titleState,
                    selectedValue = 1,
                    title = "Notes",
                    icon = Icons.Outlined.Create ,
                    route = Routes.Notes.screen
                )

                DrawerItem(
                    selected = selected ,
                    coroutineScope = coroutineScope ,
                    drawerState = drawerState,
                    navigationController = navigationController,
                    titleState = titleState,
                    selectedValue = 2,
                    title = "Profile",
                    icon = Icons.Outlined.Person ,
                    route = Routes.Profile.screen
                )


                DrawerItem(
                    selected = selected ,
                    coroutineScope = coroutineScope ,
                    drawerState = drawerState,
                    navigationController = navigationController,
                    titleState = titleState,
                    selectedValue = 3,
                    title = "Settings",
                    icon = Icons.Outlined.Settings ,
                    route = Routes.Settings.screen
                )

                Spacer(modifier = Modifier.weight(1f))

                Column(
                    modifier = Modifier.padding(vertical = 20.dp)
                ) {
                    Divider(
                        modifier = Modifier.padding(horizontal = 10.dp,),
                        color = MaterialTheme.colorScheme.outline
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Log Out",
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 20.sp
                                )
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Info, contentDescription = "",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        },

                        selected = false,
                        onClick = {


                        }
                    )
                }
                // ...other drawer items
            }
        }
    ) {

        Scaffold(
          topBar = {
              val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

              CustomTopAppBar(scrollBehavior = scrollBehavior , title =titleState.value ,
                  onNavigationIconClicked = {

                      coroutineScope.launch {
                          drawerState.open()
                      }

                  })
          }
        ) {
            NavHost(navController = navigationController,
                startDestination = Routes.Home.screen ){
                composable(Routes.Home.screen){HomePage()}
                composable(Routes.Notes.screen){NotesPage()}
                composable(Routes.Profile.screen){ ProfilePage()}
                composable(Routes.Settings.screen){ SettingsPage()}

            }



        }

    }
}

@Composable
fun DrawerItem(selected: MutableState<Int>,
               coroutineScope: CoroutineScope,
               drawerState: DrawerState,
               navigationController: NavHostController,
               titleState: MutableState<String>,
               selectedValue: Int,
               title: String,
               icon: ImageVector,
               route: String,

               ){

    NavigationDrawerItem(
        label = { Text(text = title ,
            color = if(selected.value == selectedValue ) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 20.sp
            )) },
        icon = { Icon(imageVector = icon, contentDescription ="",
            tint= if(selected.value == selectedValue ) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground)},

        selected = false,
        onClick = {
            coroutineScope.launch {
                drawerState.close()
            }
            navigationController.navigate(route = route){
                popUpTo(0)
            }
            titleState.value = title
            selected.value = selectedValue
        }
    )

}