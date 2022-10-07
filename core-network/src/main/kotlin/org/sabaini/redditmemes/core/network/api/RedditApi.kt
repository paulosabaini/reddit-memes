package org.sabaini.redditmemes.core.network.api

import org.sabaini.redditmemes.core.network.model.Listing
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("memes.json")
    suspend fun getMemes(): Listing

    @GET("memes.json")
    suspend fun getMemesAfter(@Query("after") name: String): Listing
}
