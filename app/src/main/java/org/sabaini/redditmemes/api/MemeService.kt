package org.sabaini.redditmemes.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

private const val BASE_URL = "https://www.reddit.com/r/"

enum class MemesFilter(val value: String) {
    HOT("hot"),
    NEW("new"),
    TOP("top"),
    RISING("rising"),
    CONTROVERSIAL("controversial")
}

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
    val retrofitService : MemesService by lazy { retrofit.create(MemesService::class.java) }
}