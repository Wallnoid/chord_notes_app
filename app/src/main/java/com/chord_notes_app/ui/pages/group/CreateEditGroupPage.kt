package com.chord_notes_app.ui.pages.group

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AmpStories
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chord_notes_app.data.Member
import com.chord_notes_app.data.username
import com.chord_notes_app.ui.components.CustomFloatingIconButton
import com.chord_notes_app.ui.components.CustomTextField
import com.chord_notes_app.ui.components.CustomTopAppBar
import com.chord_notes_app.ui.components.UserItem
import com.chord_notes_app.ui.pages.group.actions.addMember
import com.chord_notes_app.ui.pages.group.actions.createGroup
import com.chord_notes_app.ui.pages.group.actions.deleteGroup
import com.chord_notes_app.ui.pages.group.actions.deleteMember
import com.chord_notes_app.ui.pages.group.actions.getGroupById
import com.chord_notes_app.ui.pages.group.actions.getUserByName
import com.chord_notes_app.ui.pages.group.actions.updateGroup
import com.chord_notes_app.ui.viewModels.AuthViewModel
import com.chord_notes_app.ui.viewModels.GroupViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateEditGroupPage(
    groupId : String?,
    navController: NavController,
    groupsViewModel: GroupViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
){
    val scrollState = rememberScrollState()
    val title = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val isEdit = groupId != null
    val id_group = remember {
        mutableStateOf(
            if(isEdit){
                groupId
            }else{
                ""
            }
        )
    }
    val viewMembers = remember { mutableStateOf(false) }

    val members = remember { mutableStateOf(emptyList<Member>()) }

    fun getMembers (){
        getGroupById(navController, groupsViewModel, id_group.value!!.toInt(), onResult = { group ->
            if (group != null) {
                println(group)
                members.value = group.members
            } else {
                println("Failed to create group")
            }
        } )
    }





    LaunchedEffect(Unit) {

       if(isEdit){

           viewMembers.value = true

           getGroupById(
                navController = navController,
                groupsViewModel = groupsViewModel,
                id_group = groupId!!.toInt(),
                onResult = { group ->
                     if (group != null) {
                          title.value = group.name
                          id_group.value = group.id.toString()
                          members.value = group.members
                     } else {
                          println("Failed to get group")
                     }
                }
           )

       }


    }




    Scaffold(


        topBar = {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

            CustomTopAppBar(scrollBehavior = scrollBehavior, title ="Create Group", icon =
            Icons.Filled.ArrowBackIosNew,
                onNavigationIconClicked = {
                    navController.popBackStack()
                },

                action = {
                    if(isEdit){
                        IconButton(onClick = {
                            deleteGroup(
                                navController = navController,
                                groupsViewModel = groupsViewModel,
                                id_group = id_group.value!!.toInt(),
                                onResult = {
                                    Toast.makeText(navController.context, "Group deleted", Toast.LENGTH_SHORT).show()
                                    navController.popBackStack()
                                }


                            )
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "Localized description"
                            )

                        }
                    }
                }
            )

        }



    ) {

        Column(
            Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(vertical = 90.dp, horizontal = 16.dp),


            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start


        ) {

            CustomTextField(
                value = title.value,
                onValueChange = { title.value = it },
                placeholder = "Group's Name",
                label = "Group's Name",
                modifier = Modifier
                    .fillMaxWidth(),
                icon = Icons.Filled.AmpStories,
                enable = if(!isEdit) !viewMembers.value else true
            )



            if(isEdit){
                Spacer(modifier = Modifier.height(30.dp))
                Button(onClick = {

                    updateGroup(
                        navController = navController,
                        groupsViewModel = groupsViewModel,
                        name = title.value,
                        id_group = id_group.value!!.toInt(),
                        onResult = { group ->

                        },

                    )


                }) {


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center


                    ) {
                        Icon(Icons.Filled.Edit, null)
                        Text(text = "Update Group")

                    }
                }


            }


            if(!viewMembers.value && !isEdit){
                Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {

                createGroup(
                    navController = navController,
                    groupsViewModel = groupsViewModel,
                    name = title.value,
                    onResult = { group ->
                        if (group != null) {
                            println(group.id)

                            addMember(
                                navController = navController,
                                groupsViewModel = groupsViewModel,
                                id_group = group.id,
                                role = "admin",
                                onResult = { member ->
                                    if (member != null) {
                                        id_group.value = group.id.toString()
                                        getMembers()

                                    } else {
                                        println("Failed to create member")
                                    }
                                },

                            )

                        } else {
                            println("Failed to create group")
                        }
                    },
                    callback = {

                        viewMembers.value = !viewMembers.value
                    }
                )


            }) {


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center


                    ) {
                        Icon(Icons.Filled.Add, null)
                        Text(text = "Create Group")

                    }
                }

            }
            if(viewMembers.value){
                Spacer(modifier = Modifier.height(50.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    CustomTextField(
                        value = username.value,
                        onValueChange = { username.value = it },
                        placeholder = "type the username",
                        label = "Search User",
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 10.dp),
                        icon = Icons.Filled.PersonSearch
                    )


                    CustomFloatingIconButton(onClick = {

                        var userName = username(username = username.value)

                        getUserByName(
                            navController = navController,
                            authViewModel = authViewModel,
                            username = userName,
                            onResult = { user ->
                                if (user != null) {
                                    println(user)
                                    addMember(
                                        navController = navController,
                                        groupsViewModel = groupsViewModel,
                                        id_group = id_group.value!!.toInt(),
                                        memberResponse = user,
                                        role = "member",
                                        onResult = { member ->
                                            if (member != null) {
                                                getMembers()
                                                username.value = ""
                                            } else {
                                                println("Failed to create member")
                                            }
                                        }
                                    )
                                } else {
                                    Toast.makeText(navController.context, "User not found", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )

                        getMembers()
                    }, icon = Icons.Filled.Search)
                }

                Spacer(modifier = Modifier.height(30.dp))



                Text(
                    text = "Members",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )


                Spacer(modifier = Modifier.height(10.dp))

                members.value.forEach {
                    UserItem(userName = it.username, email = it.email,
                        onDelete = {
                            deleteMember(
                                navController = navController,
                                groupsViewModel = groupsViewModel,
                                id_member = it.id_member,
                                onResult = {
                                    getMembers()
                                }
                            )
                        }

                        )
                }


            }

        }

    }

}