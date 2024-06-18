package com.chord_notes_app.repository

import com.chord_notes_app.api.GroupsApi
import com.chord_notes_app.data.GroupsResponse
import javax.inject.Inject

class GroupRepository @Inject constructor(
  private val groupApi: GroupsApi
){

    suspend fun getGroups( token: String): List<GroupsResponse>{
        return groupApi.getGroups( token);
    }

    suspend fun getGroup( token: String, id: Int): GroupsResponse{
        return groupApi.getGroup( token, id);
    }

    suspend fun createGroup( token: String, group: GroupsResponse): GroupsResponse{
        return groupApi.createGroup( token, group);
    }

    suspend fun updateGroup( token: String, id: Int, group: GroupsResponse): GroupsResponse{
        return groupApi.updateGroup( token, id, group);
    }

    suspend fun deleteGroup( token: String, id: Int): String{
        return groupApi.deleteGroup( token, id);
    }
}