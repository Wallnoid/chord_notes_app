package com.chord_notes_app.api

import com.chord_notes_app.data.GroupsRequest
import com.chord_notes_app.data.GroupsResponse
import com.chord_notes_app.data.MemberCreate
import com.chord_notes_app.data.MemberResponse
import com.chord_notes_app.data.NameResponse
import com.chord_notes_app.data.SongGroup
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GroupsApi {

    @GET("groups/")
    suspend fun getGroups(@Header("Authorization") token: String): List<GroupsResponse>

    @GET("groups_name/")
    suspend fun getGroupsName(@Header("Authorization") token: String): List<NameResponse>

    @GET("groups/{id}/")
    suspend fun getGroup(@Header("Authorization") token: String, @Path("id") id: Int): GroupsResponse

    @POST("groups/")
    suspend fun createGroup(@Header("Authorization") token: String, @Body group: GroupsRequest): GroupsResponse

    @PUT("groups/{id}/")
    suspend fun updateGroup(@Header("Authorization") token: String, @Path("id") id: Int, @Body group: GroupsRequest): GroupsResponse

    @DELETE("groups/{id}/")
    suspend fun deleteGroup(@Header("Authorization") token: String, @Path("id") id: Int): Response<Unit?>

    @POST("members/")
    suspend fun addMember(@Header("Authorization") token: String, @Body member: MemberCreate): MemberResponse

    @DELETE("members/{id}/")
    suspend fun deleteMember(@Header("Authorization") token: String, @Path("id") id: Int): Response<Unit?>

    @POST("songs_group/")
    suspend fun addSongToGroup(@Header("Authorization") token: String, @Body songGroup: SongGroup): SongGroup

}