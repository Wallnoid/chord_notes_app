package com.chord_notes_app.ui.pages.group.actions

import androidx.navigation.NavController
import com.chord_notes_app.data.Member
import com.chord_notes_app.data.MemberCreate
import com.chord_notes_app.data.MemberResponse
import com.chord_notes_app.ui.viewModels.GroupViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun addMember(
    navController: NavController,
    groupsViewModel: GroupViewModel,
    id_group: Int,
    role: String,
    onResult: (MemberResponse?) -> Unit,
    memberResponse: Member? = null
)
{
    var id_member: Int

    if (memberResponse != null) {
        id_member = memberResponse.id

    } else {
        val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

        id_member =  sharedPreferencesManager.getData("id", "").toInt()

    }

    val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

    val token = "Token "+ sharedPreferencesManager.getData("token", "")




    val member = MemberCreate(grupo = id_group, user = id_member, role = role)

    groupsViewModel.addMember( token, member, onResult = { group ->
        if (group != null) {
            println(group)
            onResult(group)


        } else {
            println("Failed to create group")
            onResult(null)
        }
    })



}