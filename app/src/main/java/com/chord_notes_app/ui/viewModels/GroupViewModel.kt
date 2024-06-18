package com.chord_notes_app.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chord_notes_app.data.GroupsResponse
import com.chord_notes_app.repository.GroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val groupRepository: GroupRepository
): ViewModel()
{

    fun getGroups(token: String, onResult:(List<GroupsResponse>?) -> Unit) {

        viewModelScope.launch {
            try {
                val groups = groupRepository.getGroups(token)

                onResult(groups)
            } catch (e: Exception) {
                println(e)
                onResult(null)
            }
        }

    }

    fun getGroup(token: String, id: Int) {
        viewModelScope.launch {
            try {
                groupRepository.getGroup(token, id)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun createGroup(token: String, group: GroupsResponse) {
    }

    fun updateGroup(token: String, id: Int, group: GroupsResponse) {
    }

    fun deleteGroup(token: String, id: Int) {
        viewModelScope.launch {
            try {
                groupRepository.deleteGroup(token, id)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

}