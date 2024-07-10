package com.chord_notes_app.ui.components.actions

import android.content.Context
import com.chord_notes_app.data.NameResponse
import com.chord_notes_app.ui.viewModels.GroupViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun getGroupsName(
    context: Context,
    groupViewModel: GroupViewModel,
    onResult: (List<NameResponse>?) -> Unit
){

    val sharedPreferencesManager = SharedPreferencesManager.getInstance(context)
    val token = "Token " + sharedPreferencesManager.getData("token", "") as String

    groupViewModel.getGroupsName(token){ groups ->
        if (groups != null) {
            println("Groups: $groups")
            onResult(groups)
        } else {
            println("Fail to get groups")
            onResult(null)
        }
    }

}