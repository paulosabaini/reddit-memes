package org.sabaini.redditmemes.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

/*Create a Retrofit object to make network requests*/

private const val BASE_URL = "https://www.reddit.com/r/"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface MemesService {
    @GET
    suspend fun getMemes(@Url endpoint: String): Listing
}

object Network {
    val retrofitService: MemesService by lazy { retrofit.create(MemesService::class.java) }
}