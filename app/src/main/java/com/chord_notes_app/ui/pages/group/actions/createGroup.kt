package com.chord_notes_app.ui.pages.group.actions


import android.widget.Toast
import androidx.navigation.NavController
import com.chord_notes_app.data.GroupsRequest
import com.chord_notes_app.data.GroupsResponse
import com.chord_notes_app.ui.viewModels.GroupViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun createGroup(
    navController: NavController,
    groupsViewModel: GroupViewModel,
    name: String,
    onResult: (GroupsResponse?) -> Unit,
    callback: () -> Unit
)
{

    if(name.isEmpty()){
        Toast.makeText(navController.context, "Name is empty", Toast.LENGTH_SHORT).show()
        return
    }

    val group = GroupsRequest(id = null, name = name, integrante_ids = emptyList())


    val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

    val token = "Token "+ sharedPreferencesManager.getData("token", "")

    groupsViewModel.createGroup( token, group, onResult = { group ->
        if (group != null) {
            println(group)
            onResult(group)
            Toast.makeText(navController.context, "Group created", Toast.LENGTH_SHORT).show()
            callback()

        } else {
            println("Failed to create group")
            Toast.makeText(navController.context, "Failed to create group", Toast.LENGTH_SHORT).show()
            onResult(null)
        }
    })



}