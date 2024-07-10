package com.chord_notes_app.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chord_notes_app.data.GroupsRequest
import com.chord_notes_app.data.GroupsResponse
import com.chord_notes_app.data.MemberCreate
import com.chord_notes_app.data.MemberResponse
import com.chord_notes_app.data.NameResponse
import com.chord_notes_app.data.SongGroup
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

    fun getGroupsName(token: String, onResult:(List<NameResponse>?) -> Unit) {
        viewModelScope.launch {
            try {
                val groups = groupRepository.getGroupsName(token)

                onResult(groups)
            } catch (e: Exception) {
                println(e)
                onResult(null)
            }
        }
    }

    fun getGroup(token: String, id: Int, onResult:(GroupsResponse?) -> Unit){
        viewModelScope.launch {
            try {
                val group = groupRepository.getGroup(token, id)

                onResult(group)
            } catch (e: Exception) {
                println(e)
                onResult(null)
            }
        }
    }

    fun createGroup(token: String, group: GroupsRequest, onResult:(GroupsResponse?) -> Unit) {

        viewModelScope.launch {
            try {
                val group = groupRepository.createGroup(token, group)

                onResult(group)

            } catch (e: Exception) {
                println(e)
                onResult(null)
            }
        }
    }

    fun updateGroup(token: String, id: Int, group: GroupsRequest, onResult:(GroupsResponse?) -> Unit) {

        viewModelScope.launch {
            try {
                val group = groupRepository.updateGroup(token, id, group)

                onResult(group)

            } catch (e: Exception) {
                println(e)
                onResult(null)
            }
        }
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

    fun addMember(token: String, member: MemberCreate, onResult:(MemberResponse?) -> Unit) {
        viewModelScope.launch {
            try {
                val member = groupRepository.addMember(token, member)

                onResult(member)

            } catch (e: Exception) {
                println(e)
                onResult(null)
            }
        }
    }

    fun deleteMember(token: String, id: Int, onResult: () -> Unit){
        viewModelScope.launch {
            try {
                groupRepository.deleteMember(token, id)
            } catch (e: Exception) {
                onResult()

                println(e)
            }
        }
    }

    fun addSongToGroup(token: String, songGroup: SongGroup, onResult:(SongGroup?) -> Unit) {
        viewModelScope.launch {
            try {
                val songGroup = groupRepository.addSongToGroup(token, songGroup)

                onResult(songGroup)

            } catch (e: Exception) {
                println(e)
                onResult(null)
            }
        }
    }

}