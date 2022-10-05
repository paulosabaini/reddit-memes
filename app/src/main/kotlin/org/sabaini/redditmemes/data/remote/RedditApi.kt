package org.sabaini.redditmemes.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("memes.json")
    suspend fun getMemes(): Listing

    @GET("memes.json")
    suspend fun getMemesAfter(@Query("after") name: String): Listing
}