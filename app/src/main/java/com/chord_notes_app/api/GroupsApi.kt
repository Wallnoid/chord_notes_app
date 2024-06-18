package com.chord_notes_app.api

import com.chord_notes_app.data.GroupsResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface GroupsApi {

    @GET("groups/")
    suspend fun getGroups(@Header("Authorization") token: String): List<GroupsResponse>

    @GET("groups/{id}")
    suspend fun getGroup(@Header("Authorization") token: String, id: Int): GroupsResponse

    @POST("groups/")
    suspend fun createGroup(@Header("Authorization") token: String, group: GroupsResponse): GroupsResponse

    @PUT("groups/{id}")
    suspend fun updateGroup(@Header("Authorization") token: String, id: Int, group: GroupsResponse): GroupsResponse

    @DELETE("groups/{id}")
    suspend fun deleteGroup(@Header("Authorization") token: String, id: Int): String

}