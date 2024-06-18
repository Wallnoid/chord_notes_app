@file:OptIn(ExperimentalMaterial3Api::class)

package com.chord_notes_app.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.NoteAdd
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Groups2
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chord_notes_app.R
import com.chord_notes_app.routes.Routes
import com.chord_notes_app.ui.components.actions.logout
import com.chord_notes_app.ui.theme.AppTheme
import com.chord_notes_app.ui.viewModels.AuthViewModel
import com.chord_notes_app.utils.SharedPreferencesManager
import com.chord_notes_app.utils.cutString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GlobalSchema(content: @Composable () -> Unit,
                 coroutineScope: CoroutineScope,
                 drawerState: DrawerState,
                 navigationController: NavController,
                 titleState: MutableState<String>,
                 selected:  MutableState<Int>,
                 authViewModel: AuthViewModel = hiltViewModel(),
                 ){

    val context = LocalContext.current

    var username: MutableState<String> =  remember { mutableStateOf("") }
    val emailState =  remember { mutableStateOf("") }


    LaunchedEffect(Unit){

        val sharedPreferencesManager = SharedPreferencesManager.getInstance(context)

        val user = sharedPreferencesManager.getData("username", "")
        val email = sharedPreferencesManager.getData("email", "")
        username.value = user
        emailState.value = email

        println("USERNAME DENTR "+username)

    }

    println("USERNAME "+username)


    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,


        drawerContent = {
            ModalDrawerSheet {

                TopSideBar(
                    userName = username.value,
                    email = emailState.value
                )

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
                    icon = Icons.AutoMirrored.Outlined.NoteAdd,
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
                    icon = Icons.Outlined.Groups2 ,
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

                            logout(
                                context = navigationController.context,
                                authViewModel = authViewModel,
                                navigationController = navigationController
                            )


                        }
                    )
                }

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

                  },
                  icon = Icons.Filled.Menu
                  )
          }
        ) {

            content()



        }

    }
}


@Composable
fun TopSideBar(
    userName: String,
    email: String
){

    Box(modifier = Modifier.fillMaxWidth()
    ){

        Image(
            painter = painterResource(id = R.drawable.sidebar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()

        )

        Row(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomStart)
            ,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Box(
                modifier = Modifier

                    .size(70.dp)
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = CircleShape
                    )

                ,
                contentAlignment = Alignment.Center

            ) {


                Icon(imageVector = Icons.Filled.Person, contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                    )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {

                Text(
                    text = cutString(userName, 15) , style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,

                    )

                Text(
                    text = cutString(email, 20), style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,

                    )

            }





        }

    }

}

@Preview(showBackground = true)
@Composable
fun MyComposablePreview() {

    AppTheme {
        TopSideBar(
            userName = "WilliamPandilla",
            email = ""
        )

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