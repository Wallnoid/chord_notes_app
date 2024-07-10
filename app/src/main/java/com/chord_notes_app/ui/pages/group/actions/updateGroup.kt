package com.chord_notes_app.ui.pages.group.actions



import android.widget.Toast
import androidx.navigation.NavController
import com.chord_notes_app.data.GroupsRequest
import com.chord_notes_app.data.GroupsResponse
import com.chord_notes_app.ui.viewModels.GroupViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun updateGroup(
    navController: NavController,
    groupsViewModel: GroupViewModel,
    name: String,
    id_group: Int,
    onResult: (GroupsResponse?) -> Unit
)
{

    if(name.isEmpty()){
        Toast.makeText(navController.context, "Name is empty", Toast.LENGTH_SHORT).show()
        return
    }

    val group = GroupsRequest(id = id_group ,name = name, integrante_ids = emptyList())


    val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

    val token = "Token "+ sharedPreferencesManager.getData("token", "")

    groupsViewModel.updateGroup( token,group.id!! ,group, onResult = { group ->
        if (group != null) {
            println(group)
            onResult(group)
            Toast.makeText(navController.context, "Group updated", Toast.LENGTH_SHORT).show()

        } else {
            println("Failed to create group")
            Toast.makeText(navController.context, "Failed to update group", Toast.LENGTH_SHORT).show()
            onResult(null)
        }
    })



}