package com.chord_notes_app.ui.pages.group.actions



import android.widget.Toast
import androidx.navigation.NavController
import com.chord_notes_app.ui.viewModels.GroupViewModel
import com.chord_notes_app.utils.SharedPreferencesManager


fun deleteMember(
    navController: NavController,
    groupsViewModel: GroupViewModel,
    id_member: Int,
    onResult: () -> Unit

)
{



    val sharedPreferencesManager = SharedPreferencesManager.getInstance(navController.context)

    val token = "Token "+ sharedPreferencesManager.getData("token", "")


        groupsViewModel.deleteMember( token, id_member,

            onResult = {
                Toast.makeText(navController.context, "Failed to delete member", Toast.LENGTH_SHORT).show()
            }

            )


    onResult()



}