package com.chord_notes_app.repository

import com.chord_notes_app.api.GroupsApi
import com.chord_notes_app.data.GroupsRequest
import com.chord_notes_app.data.GroupsResponse
import com.chord_notes_app.data.MemberCreate
import com.chord_notes_app.data.MemberResponse
import com.chord_notes_app.data.NameResponse
import com.chord_notes_app.data.SongGroup
import retrofit2.Response
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

    suspend fun getGroupsName( token: String): List<NameResponse>{
        return groupApi.getGroupsName( token);
    }

    suspend fun createGroup( token: String, group: GroupsRequest): GroupsResponse{
        return groupApi.createGroup( token, group);
    }

    suspend fun updateGroup( token: String, id: Int, group: GroupsRequest): GroupsResponse{
        return groupApi.updateGroup( token, id, group);
    }

    suspend fun deleteGroup( token: String, id: Int): Response<Unit?>{
        return groupApi.deleteGroup( token, id);
    }

    suspend fun addMember( token: String, member: MemberCreate): MemberResponse {
        return groupApi.addMember( token, member);
    }

    suspend fun deleteMember( token: String, id: Int): Response<Unit?>{
        return groupApi.deleteMember( token, id);
    }

    suspend fun addSongToGroup( token: String, songGroup: SongGroup): SongGroup{
        return groupApi.addSongToGroup( token, songGroup);
    }
}