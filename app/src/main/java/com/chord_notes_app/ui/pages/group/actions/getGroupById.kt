package com.chord_notes_app.ui.pages.group.actions



import androidx.navigation.NavController
import com.chord_notes_app.data.GroupsResponse
import com.chord_notes_app.ui.viewModels.GroupViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun getGroupById(
    navController: NavController,
    groupsViewModel: GroupViewModel,
    id_group: Int,
    onResult: (GroupsResponse?) -> Unit,
)
{

    val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

    val token = "Token "+ sharedPreferencesManager.getData("token", "")



    groupsViewModel.getGroup( token, id_group, onResult = { group ->
        if (group != null) {
            println(group)
            onResult(group)


        } else {
            println("Failed to create group")
            onResult(null)
        }
    })



}