package org.sabaini.redditmemes.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Listing(
    val kind: String,
    val data: ListingData
)

@JsonClass(generateAdapter = true)
data class ListingData(
    val children: List<Post>
)

@JsonClass(generateAdapter = true)
data class Post(
    val data: PostData
)

@JsonClass(generateAdapter = true)
data class PostData(
    val title: String,
    val author: String,
    @field:Json(name = "url_overridden_by_dest")
    val imgUrl: String,
    val stickied: Boolean,
    val id: String,
    @field:Json(name = "is_video")
    val isVideo: Boolean,
    val permalink: String,
    val score: Int,
    @field:Json(name = "created_utc")
    val createdUtc: Long,
    @field:Json(name = "num_comments")
    val numComments: Int,
    val subreddit: String,
    val name: String
)