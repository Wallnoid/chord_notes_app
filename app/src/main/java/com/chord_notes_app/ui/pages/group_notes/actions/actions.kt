package com.chord_notes_app.ui.pages.group_notes.actions

import android.content.Context
import com.chord_notes_app.data.GroupsResponse
import com.chord_notes_app.ui.viewModels.GroupViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun getGroups(
    context: Context,
    groupViewModel: GroupViewModel,
    onResult: (List<GroupsResponse>?) -> Unit
)
{

    val sharedPreferencesManager = SharedPreferencesManager.getInstance(context)

    val token = "Token "+ sharedPreferencesManager.getData("token", "")

    groupViewModel.getGroups(
        token, onResult = { groups ->
            if (groups != null) {
                println(groups)
                onResult(groups)
            } else {
                println("Failed to get groups")
                onResult(null)
            }
        }
    )



}