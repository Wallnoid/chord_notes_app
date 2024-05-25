@file:OptIn(ExperimentalMaterial3Api::class)

package com.chord_notes_app.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chord_notes_app.routes.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GlobalSchema(content: @Composable () -> Unit ,
                 coroutineScope: CoroutineScope,
                 drawerState: DrawerState,
                 navigationController: NavController,
                 titleState: MutableState<String>,
                 selected:  MutableState<Int>,){


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
                    title = "My Notes",
                    icon = Icons.Outlined.Home,
                    route = Routes.MyNotes.screen
                )

                DrawerItem(
                    selected = selected ,
                    coroutineScope = coroutineScope ,
                    drawerState = drawerState,
                    navigationController = navigationController,
                    titleState = titleState,
                    selectedValue = 1,
                    title = "Groups Notes",
                    icon = Icons.Outlined.Create ,
                    route = Routes.GroupNotes.screen
                )

                Spacer(modifier = Modifier.weight(weight = 0.5f))


                Text("Configuration", modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.outline)

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
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "",
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

            content()



        }

    }
}

@Composable
fun DrawerItem(selected: MutableState<Int>,
               coroutineScope: CoroutineScope,
               drawerState: DrawerState,
               navigationController: NavController,
               titleState: MutableState<String>,
               selectedValue: Int,
               title: String,
               icon: ImageVector,
               route: String,

               ){

    NavigationDrawerItem(
        label = { Text(text = title ,
            color = if(selected.value == selectedValue ) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 20.sp
            )) },
        icon = { Icon(imageVector = icon, contentDescription ="",
            tint= if(selected.value == selectedValue ) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onBackground)},

        selected = false,
        onClick = {
            println("HOLA")
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