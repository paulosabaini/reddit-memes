package org.sabaini.redditmemes.data.remote

import retrofit2.http.GET

interface RedditApi {

    @GET("memes.json")
    suspend fun getMemes(): Listing
}