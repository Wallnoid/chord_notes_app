package com.chord_notes_app.ui.pages.group.actions



import androidx.navigation.NavController
import com.chord_notes_app.ui.viewModels.GroupViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun deleteGroup(
    navController: NavController,
    groupsViewModel: GroupViewModel,
    id_group: Int,
    onResult: () -> Unit
)
{



    val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

    val token = "Token "+ sharedPreferencesManager.getData("token", "")

    groupsViewModel.deleteGroup( token, id_group )

    onResult()



}