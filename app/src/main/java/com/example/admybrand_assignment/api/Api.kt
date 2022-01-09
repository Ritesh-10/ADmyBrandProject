package com.example.admybrand_assignment.api

import com.example.admybrand_assignment.data.DetailUserResponse
import com.example.admybrand_assignment.data.User
import com.example.admybrand_assignment.data.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q")query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username")username:String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username")username:String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username")username:String
    ): Call<ArrayList<User>>
}